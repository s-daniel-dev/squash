package Sd.Sb_Squash_MVC.dto;

import java.util.List;

public class MatchListDto {
	
	private List<MatchDto> matches;
	private List<UserDto> users;
	private List<LocationDto> locations;
	
	
	public MatchListDto(List<MatchDto> matches, List<UserDto> users, List<LocationDto> locations) {
		super();
		this.matches = matches;
		this.users = users;
		this.locations = locations;
	}


	public List<MatchDto> getMatches() {
		return matches;
	}

	public void setMatches(List<MatchDto> matches) {
		this.matches = matches;
	}

	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}

	public List<LocationDto> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationDto> locations) {
		this.locations = locations;
	}
	
}
