package todo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class TaskType {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id; //JPAによる自動採番
	
	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private Account user;
	
	private String name;
	
	//JPA用の空コンストラクタ
	public TaskType() {
	}
	
	public TaskType(Account user, String name) {
		this.user = user;
		editName(name);
	}
	
	public void editName(String name) {
		this.name = name;
	}
	
	//get method ----------------------------------------------------------
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}