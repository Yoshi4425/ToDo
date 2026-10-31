package todo.manager;
import todo.entity.*;
import todo.repository.*;

import java.util.List;

import org.springframework.stereotype.Service;

//種別の管理
@Service //Springがインスタンスを作成し管理する
public class TaskTypeManager {
	
	//データベース
	private final TaskTypeRepository repository;
	
	//リポジトリのインスタンスはSpringが作成して管理する
	public TaskTypeManager(TaskTypeRepository repository) {
        this.repository = repository;
    }
	
	//アカウントに対応する種別をリストで返す
	public List<TaskType> getTaskTypes(Account user) {
		return repository.findByUser(user);
	}
	
	//idで指定された種別を返す
	public TaskType getType(Account user, int id) {
		return repository.findByUserAndId(user, id).orElse(null);
	}
	
	//種別を作成
	public void makeType(Account user, String name) {
		TaskType type = new TaskType(user, name);
		repository.save(type);
	}
	
	//種別を削除
	public void deleteType(Account user, int id) {
		repository.findByUserAndId(user, id).ifPresent(repository::delete);
	}
	
	//種別を編集
	public void editType(Account user, int id, String name) {
		repository.findByUserAndId(user, id).ifPresent(type -> {
            type.editName(name);
            repository.save(type);
        });
	}
}
