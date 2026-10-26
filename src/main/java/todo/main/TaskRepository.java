package todo.main;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	boolean existsByType_Id(Integer typeId);
}