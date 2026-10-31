package todo.repository;
import todo.entity.Task;
import todo.entity.Account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

//インターフェースだが、Springが自動でインプリメント後の実装クラスを作成、interfaceの型でSpringから受け取る
public interface TaskRepository extends JpaRepository<Task, Integer> {
	
	//メソッド名からJPAリポジトリが自動で処理を作成
	boolean existsByType_UserAndId(Account user, int typeId);
	List<Task> findByUser(Account user);
	Optional<Task> findByUserAndId(Account user, int id);
}