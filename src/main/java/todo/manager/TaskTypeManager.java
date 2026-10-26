package todo.manager;
import todo.entity.*;
import todo.repository.*;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaskTypeManager {
	
	private final TaskTypeRepository taskTypeRepository;
	
	public TaskTypeManager(TaskTypeRepository taskTypeRepository) {
        this.taskTypeRepository = taskTypeRepository;
    }
	
	//種別の管理-------------------------------------------------------------------------
	public List<TaskType> getTaskTypes() {
		return taskTypeRepository.findAll();
	}
	
	public TaskType getType(int id) {
		return taskTypeRepository.findById(id).orElse(null);
	}
	
	public void makeType(String name) {
		TaskType type = new TaskType(name);
		taskTypeRepository.save(type);
	}
	
	public void deleteType(int id) {
		taskTypeRepository.deleteById(id);
	}
	
	public void editType(int id, String name) {
		taskTypeRepository.findById(id).ifPresent(type -> {
            type.editName(name);
            taskTypeRepository.save(type);
        });
	}
}
