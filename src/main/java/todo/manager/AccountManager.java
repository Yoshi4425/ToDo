package todo.manager;
import todo.entity.Account;
import todo.repository.AccountRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//アカウントの管理
@Service //Springがインスタンスを作成し管理する
public class AccountManager {
	
	//データベース
	private final AccountRepository repository;
	private final PasswordEncoder encoder;
	
	//リポジトリのインスタンスはSpringが作成して管理する
	public AccountManager(AccountRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
	}
	
	//アカウントの作成
	public void makeAccount(String username, String password) {
		Account user = new Account();
		user.setAccount(username, encoder.encode(password));
		repository.save(user);
	}
	
	//同じユーザー名が存在するか確認
	public boolean checkExistUsername(String username) {
		return repository.existsByUsername(username);
	}
	
	//アカウント名で指定されたアカウントを返す
	public Account getAccount(String username) {
		return repository.findByUsername(username).orElse(null);
	}
	
}
