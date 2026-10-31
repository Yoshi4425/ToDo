package todo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

//データベースに登録される種別データ
@Entity
public class TaskType {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id; //JPAによる自動採番
	
	//権限を持つアカウント
	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private Account user;
	
	//種別名
	private String name;
	
	//JPA用の空コンストラクタ
	public TaskType() {
	}
	
	public TaskType(Account user, String name) {
		this.user = user;
		editName(name);
	}
	
	//種別の編集、追加にも使用
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