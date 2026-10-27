package todo.manager;
import todo.entity.Account;
import todo.repository.AccountRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountManager {
	private final AccountRepository repository;
	private final PasswordEncoder encoder;
	
	public AccountManager(AccountRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
	}
	
	public void makeAccount(String username, String password) {
		Account user = new Account();
		user.setAccount(username, encoder.encode(password));
		repository.save(user);
	}
	
	public boolean checkExistUsername(String username) {
		return repository.existsByUsername(username);
	}
	
	public Account getAccount(String username) {
		return repository.findByUsername(username).orElse(null);
	}
	
}
