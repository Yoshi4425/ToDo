package todo.main;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class TaskType {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id; //JPAによる自動採番
	
	private String name;
	
	//JPA用の空コンストラクタ
	public TaskType() {
	}
	
	public TaskType(String name) {
		this.name = name;
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