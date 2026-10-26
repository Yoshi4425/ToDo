package todo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id; //JPAによる自動採番
	
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "task_type_id")
	private TaskType type;
	
	private LocalDateTime deadline;
	private boolean complete;
	private String memo;
	
	//JPA用の空コンストラクタ
	public Task() {
	}
	
	public Task(String title, TaskType type, LocalDateTime deadline, String memo) {
		this.title = title;
		this.type = type;
		this.deadline = deadline;
		complete = false;
		this.memo = memo;
	}
	
	public void changeComplete() {
		complete = !complete;
	}
	
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
