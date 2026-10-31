package todo.manager;
import todo.entity.*;
import todo.repository.*;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

//タスクの管理
@Service //Springがインスタンスを作成し管理する
public class TaskManager {
	
	//データベース
	private final TaskRepository repository;
	
	//リポジトリのインスタンスはSpringが作成して管理する
	public TaskManager(TaskRepository repository) {
        this.repository = repository;
    }
	
	//タスクの作成
	public void makeTask(Account user, String title, TaskType type, LocalDateTime deadline, String memo) {
		Task task = new Task(user, title, type, deadline, memo);
		repository.save(task);
	}
	
	//タスクの完了処理
	public void completeTask(Account user, int id) {
		repository.findByUserAndId(user, id).ifPresent(task -> {
            task.changeComplete();
            repository.save(task);
        });
	}
	
	//タスクの編集
	public void editTask(Account user, int id, String title, TaskType type, LocalDateTime deadline, String memo) {
		repository.findByUserAndId(user, id).ifPresent(task -> {
            task.editTask(title, type, deadline, memo);
            repository.save(task);
        });
	}
	
	//タスクの削除
	public void deleteTask(Account user, int id) {
		repository.findByUserAndId(user, id).ifPresent(repository::delete);
	}
	
	//指定されたidの種別がタスクで使用されているかを確認
	public boolean checkUsedTaskType(Account user, int typeId) {
		return repository.existsByType_UserAndId(user, typeId);
	}
	
	//アカウントに対応するタスクをリストで返す
	public List<Task> getTasks(Account user) {
		return repository.findByUser(user);
	}
}
