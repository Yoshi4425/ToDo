package todo.manager;
import todo.entity.*;
import todo.repository.*;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class TaskManager {
	
	private final TaskRepository repository;
	
	public TaskManager(TaskRepository repository) {
        this.repository = repository;
    }
	
	//タスクの管理-------------------------------------------------------------------------
	public void makeTask(Account user, String title, TaskType type, LocalDateTime deadline, String memo) {
		Task task = new Task(user, title, type, deadline, memo);
		repository.save(task);
	}
	
	public void completeTask(Account user, int id) {
		repository.findByUserAndId(user, id).ifPresent(task -> {
            task.changeComplete();
            repository.save(task);
        });
	}
	
	public void editTask(Account user, int id, String title, TaskType type, LocalDateTime deadline, String memo) {
		repository.findByUserAndId(user, id).ifPresent(task -> {
            task.editTask(title, type, deadline, memo);
            repository.save(task);
        });
	}
	
	public void deleteTask(Account user, int id) {
		repository.findByUserAndId(user, id).ifPresent(repository::delete);
	}
	
	public boolean checkUsedTaskType(Account user, int typeId) {
		return repository.existsByType_UserAndId(user, typeId);
	}
	
	public List<Task> getTasks(Account user) {
		return repository.findByUser(user);
	}
}
