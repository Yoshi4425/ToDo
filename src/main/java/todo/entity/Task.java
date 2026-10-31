package todo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

//データベースに登録されるタスクデータ
@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id; //JPAによる自動採番
	
	//権限を持つユーザー
	@ManyToOne //複数タスクからの指定が可能
	@JoinColumn(name = "account_id", nullable = false)
	private Account user;
	
	//タスク名
	private String title;
	
	//種別
	@ManyToOne
	@JoinColumn(name = "task_type_id")
	private TaskType type;
	
	//締め切り
	private LocalDateTime deadline;
	
	//完了、未完了
	private boolean complete;
	
	//メモ
	private String memo;
	
	//JPA用の空コンストラクタ
	public Task() {
	}
	
	public Task(Account user, String title, TaskType type, LocalDateTime deadline, String memo) {
		editTask(title, type, deadline, memo);
		this.user = user;
		complete = false;
	}
	
	//完了処理　状態を入れ替える
	public void changeComplete() {
		complete = !complete;
	}
	
	//タスクの編集、追加にも使用する
	public void editTask(String title, TaskType type, LocalDateTime deadline, String memo) {
		this.title = title;
		this.type = type;
		this.deadline = deadline;
		this.memo = memo;
	}
	
	//get method ----------------------------------------------------------
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public TaskType getType() {
		return type;
	}
	
	public LocalDateTime getDeadline() {
		return deadline;
	}
	
	public boolean getComplete() {
		return complete;
	}
	
	public String getMemo() {
		return memo;
	}
}
