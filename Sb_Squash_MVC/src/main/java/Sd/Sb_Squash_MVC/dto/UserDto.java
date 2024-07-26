package Sd.Sb_Squash_MVC.dto;

public class UserDto {
	
	private int id;
	private String name;
	private Boolean isLoggedIn;
	private boolean isAdmin;
	
	
	public UserDto(int id, String name, Boolean isLoggedIn, boolean isAdmin) {
		super();
		this.id = id;
		this.name = name;
		this.isLoggedIn = isLoggedIn;
		this.isAdmin = isAdmin;
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

	public Boolean getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(Boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
