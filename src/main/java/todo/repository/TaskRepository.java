package todo.repository;
import todo.entity.Task;
import todo.entity.Account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	boolean existsByType_UserAndId(Account user, int typeId);
	List<Task> findByUser(Account user);
	Optional<Task> findByUserAndId(Account user, int id);
}