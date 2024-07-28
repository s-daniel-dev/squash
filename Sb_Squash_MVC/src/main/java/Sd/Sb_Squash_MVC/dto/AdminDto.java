package Sd.Sb_Squash_MVC.dto;

import java.util.List;

public class AdminDto {
	
	
	private List<UserDto> userList;
	private List<LocationDto> locList;
	
	
	public AdminDto(List<UserDto> userList, List<LocationDto> locList) {
		
		this.userList = userList;
		this.locList = locList;
	}


	public List<UserDto> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDto> userList) {
		this.userList = userList;
	}

	public List<LocationDto> getLocList() {
		return locList;
	}

	public void setLocList(List<LocationDto> locList) {
		this.locList = locList;
	}
		

}
