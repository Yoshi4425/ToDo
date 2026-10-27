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
	public void makeTask(String title, TaskType type, LocalDateTime deadline, String memo) {
		Task task = new Task(title, type, deadline, memo);
		repository.save(task);
	}
	
	public void completeTask(int id) {
		repository.findById(id).ifPresent(task -> {
            task.changeComplete();
            repository.save(task);
        });
	}
	
	public void editTask(int id, String title, TaskType type, LocalDateTime deadline, String memo) {
		repository.findById(id).ifPresent(task -> {
            task.editTask(title, type, deadline, memo);
            repository.save(task);
        });
	}
	
	public void deleteTask(int id) {
		repository.deleteById(id);
	}
	
	public boolean checkUsedTaskType(int typeId) {
		return repository.existsByType_Id(typeId);
	}
	
	public List<Task> getTasks() {
		return repository.findAll();
	}
}
