package todo.repository;
import todo.entity.TaskType;
import todo.entity.Account;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

//インターフェースだが、Springが自動でインプリメント後の実装クラスを作成、interfaceの型でSpringから受け取る
public interface TaskTypeRepository extends JpaRepository<TaskType, Integer> {
	
	//メソッド名からJPAリポジトリが自動で処理を作成
	List<TaskType> findByUser(Account user);
	Optional<TaskType> findByUserAndId(Account user, int id);
}