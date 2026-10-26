package todo.manager;
import todo.entity.*;
import todo.repository.*;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class TaskManager {
	
	private final TaskRepository taskRepository;
	
	public TaskManager(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
	
	//タスクの管理-------------------------------------------------------------------------
	public void makeTask(String title, TaskType type, LocalDateTime deadline, String memo) {
		Task task = new Task(title, type, deadline, memo);
		taskRepository.save(task);
	}
	
	public void completeTask(int id) {
		taskRepository.findById(id).ifPresent(task -> {
            task.changeComplete();
            taskRepository.save(task);
        });
	}
	
	public void editTask(int id, String title, TaskType type, LocalDateTime deadline, String memo) {
		taskRepository.findById(id).ifPresent(task -> {
            task.editTask(title, type, deadline, memo);
            taskRepository.save(task);
        });
	}
	
	public void deleteTask(int id) {
		taskRepository.deleteById(id);
	}
	
	public boolean checkUsedTaskType(int typeId) {
		return taskRepository.existsByType_Id(typeId);
	}
	
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}
}
