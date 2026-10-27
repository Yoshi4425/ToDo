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
	public List<TaskType> getTaskTypes(Account user) {
		return repository.findByUser(user);
	}
	
	public TaskType getType(Account user, int id) {
		return repository.findByUserAndId(user, id).orElse(null);
	}
	
	public void makeType(Account user, String name) {
		TaskType type = new TaskType(user, name);
		repository.save(type);
	}
	
	public void deleteType(Account user, int id) {
		repository.findByUserAndId(user, id).ifPresent(repository::delete);
	}
	
	public void editType(Account user, int id, String name) {
		repository.findByUserAndId(user, id).ifPresent(type -> {
            type.editName(name);
            repository.save(type);
        });
	}
}
