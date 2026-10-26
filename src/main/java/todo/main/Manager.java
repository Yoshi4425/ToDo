package todo.main;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class Manager {
	
	private final TaskRepository taskRepository;
	private final TaskTypeRepository taskTypeRepository;
	
	public Manager(TaskRepository taskRepository, TaskTypeRepository taskTypeRepository) {
        this.taskRepository = taskRepository;
        this.taskTypeRepository = taskTypeRepository;
    }
	
	//タスクの管理-------------------------------------------------------------------------
	public void makeTask(String title, int  typeId, LocalDateTime deadline, String memo) {
		TaskType type = taskTypeRepository.findById(typeId).orElseThrow();
		Task task = new Task(title, type, deadline, memo);
		taskRepository.save(task);
	}
	
	public void completeTask(int id) {
		taskRepository.findById(id).ifPresent(task -> {
            task.changeComplete();
            taskRepository.save(task);
        });
	}
	
	public void editTask(int id, String title, int typeId, LocalDateTime deadline, String memo) {
		TaskType type = taskTypeRepository.findById(typeId).orElseThrow();
		taskRepository.findById(id).ifPresent(task -> {
            task.editTask(title, type, deadline, memo);
            taskRepository.save(task);
        });
	}
	
	public void deleteTask(int id) {
		taskRepository.deleteById(id);
	}
	
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}
	
	//種別の管理-------------------------------------------------------------------------
	public List<TaskType> getTaskTypes() {
		return taskTypeRepository.findAll();
	}
	
	public void makeType(String name) {
		TaskType type = new TaskType(name);
		taskTypeRepository.save(type);
	}
	
	public boolean deleteType(int id) {
		if(taskRepository.existsByType_Id(id)) {
			return false;
		}
		taskTypeRepository.deleteById(id);
		return true;
		
	}
	
	public void editType(int id, String name) {
		taskTypeRepository.findById(id).ifPresent(type -> {
            type.editName(name);
            taskTypeRepository.save(type);
        });
	}
}
