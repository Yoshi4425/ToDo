package todo.entity;

import jakarta.persistence.*;

@Entity
public class Account {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    
    public Account() {
    }
    
    public void setAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
	//get method ----------------------------------------------------------

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
