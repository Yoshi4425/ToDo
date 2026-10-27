package todo.manager;
import todo.entity.*;
import todo.repository.*;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaskTypeManager {
	
	private final TaskTypeRepository repository;
	
	public TaskTypeManager(TaskTypeRepository repository) {
        this.repository = repository;
    }
	
	//種別の管理-------------------------------------------------------------------------
	public List<TaskType> getTaskTypes() {
		return repository.findAll();
	}
	
	public TaskType getType(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public void makeType(String name) {
		TaskType type = new TaskType(name);
		repository.save(type);
	}
	
	public void deleteType(int id) {
		repository.deleteById(id);
	}
	
	public void editType(int id, String name) {
		repository.findById(id).ifPresent(type -> {
            type.editName(name);
            repository.save(type);
        });
	}
}
