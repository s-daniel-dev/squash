package Sd.Sb_Squash_MVC.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "pwd")
	private String pwd;
	
	@Column(name = "admin")
	private boolean isAdmin;
	
	@Column(name = "logged_in")
	private Boolean isLoggedIn;
	
	
	public User() {
		super();
	}

	public User(int id, String name, String pwd, boolean isAdmin, Boolean isLoggedIn) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.isAdmin = isAdmin;
		this.isLoggedIn = isLoggedIn;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public Boolean getIsLoggedIn() {
		return isLoggedIn;
	}
	
	public void setIsLoggedIn(Boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

}
