package todo.repository;
import todo.entity.TaskType;
import todo.entity.Account;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TaskTypeRepository extends JpaRepository<TaskType, Integer> {
	List<TaskType> findByUser(Account user);
	Optional<TaskType> findByUserAndId(Account user, int id);
}