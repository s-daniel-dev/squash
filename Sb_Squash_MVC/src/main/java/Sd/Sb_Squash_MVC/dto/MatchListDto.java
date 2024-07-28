package Sd.Sb_Squash_MVC.dto;

import java.time.LocalDate;
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
		sortMatches();
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
	
	private void sortMatches() {
		
		for(int index = 0; index < (matches.size() - 1); index++) {
			
			LocalDate match1 = matches.get(index).getDate();
			LocalDate match2 = matches.get(index + 1).getDate();
			MatchDto dto1 = matches.get(index);
			MatchDto dto2 = matches.get(index + 1);
			
			
			if(match1.isBefore(match2)) {
				matches.set(index, dto2);
				matches.set(index + 1, dto1);
				index = -1;
			}
				
			
		}
	}
	
}
