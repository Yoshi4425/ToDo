package todo.repository;
import todo.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

//インターフェースだが、Springが自動でインプリメント後の実装クラスを作成、interfaceの型でSpringから受け取る
public interface AccountRepository extends JpaRepository<Account, Integer> {

	//メソッド名からJPAリポジトリが自動で処理を作成
    Optional<Account> findByUsername(String username);
    boolean existsByUsername(String username);
}