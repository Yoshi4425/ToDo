package todo.config;
import todo.entity.Account;
import todo.repository.AccountRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	//パスワード暗号化
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //ログイン操作
    @Bean
    public UserDetailsService userDetailsService(
            AccountRepository repository) {

        return username -> {
            Account user = repository.findByUsername(username)
                    .orElseThrow(() ->
                        new UsernameNotFoundException("ユーザーが見つかりません"));

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles("USER")
                    .build();
        };
    }

    //セキュリティの設定
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
        	//ログインなしでアクセスできるリンク
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/register", "/login", "/error", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
            )
            //ログイン用のページとログイン後に飛ぶページを指定
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            //ログアウト時のURL
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            //コンソールへのアクセスを許可
	        .csrf(csrf -> csrf
	            .ignoringRequestMatchers("/h2-console/**")
	        )
	        //コンソール表示のための許可
	        .headers(headers -> headers
	            .frameOptions(frame -> frame.sameOrigin())
	        );

        return http.build();
    }
}