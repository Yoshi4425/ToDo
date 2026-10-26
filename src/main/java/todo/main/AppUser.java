package todo.main;

import jakarta.persistence.*;

@Entity
public class AppUser {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    
    public AppUser() {
    }
    
    public void setUser(String username, String password) {
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
