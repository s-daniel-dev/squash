package Sd.Sb_Squash_MVC.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Sd.Sb_Squash_MVC.db.Database;
import Sd.Sb_Squash_MVC.dto.LocationDto;
import Sd.Sb_Squash_MVC.dto.MatchDto;
import Sd.Sb_Squash_MVC.dto.MatchListDto;
import Sd.Sb_Squash_MVC.dto.UserDto;
import Sd.Sb_Squash_MVC.model.Location;
import Sd.Sb_Squash_MVC.model.Match;
import Sd.Sb_Squash_MVC.model.User;

@Service
public class AppService {
	
	private Database db;
	
	@Autowired
	public AppService(Database db) {
		super();
		this.db = db;
	}



	public UserDto loginUser(String uName, String uPwd) {

		UserDto userDto = null;
		
		User user = db.loginUser(uName, uPwd);
		
		if(user != null) {
			
			userDto = convertUserToDto(user);
			
		}
		
		
		return userDto;
	}



	public MatchListDto getMatchListDto() {

		
		List<User> userList = db.getUserList();
		List<UserDto> userDtoList = convertUserListToDtoList(userList);
		
		List<Location> locList = db.getLocationList();
		List<LocationDto> locationDtoList = convertLocationListToDtoList(locList);
		
		List<Match> matchList = db.getMatchList();
		List<MatchDto> matchDtoList = convertMatchListToDtoList(matchList, locList, userList);
		
		
		return (new MatchListDto(matchDtoList, userDtoList, locationDtoList));
	}



	private List<LocationDto> convertLocationListToDtoList(List<Location> locList) {

		List<LocationDto> dtoList = new ArrayList<>();
		
		for(int index = 0; index < locList.size(); index++) {
			
			Location loc = locList.get(index);
			LocationDto dto = new LocationDto(
						loc.getId(),
						loc.getName()
					);
			dtoList.add(dto);
			
		}
		
		return dtoList;
	}



	private List<UserDto> convertUserListToDtoList(List<User> userList) {

		List<UserDto> dtoList = new ArrayList<>();
		
		
		for(int index = 0; index < userList.size(); index++) {
			
			User user = userList.get(index);
			
			UserDto dto = convertUserToDto(user);
			dtoList.add(dto);
			
		}
		
		
		return dtoList;
	}



	private List<MatchDto> convertMatchListToDtoList(List<Match> matchList, List<Location> locList, List<User> userList) {
		
		List<MatchDto> matchDtoList = new ArrayList<>();
		
		for(int index = 0; index < matchList.size(); index++) {
			
			Match match = matchList.get(index);
			Location loc = getLocationById(match.getLocationId(), locList);
			
			MatchDto dto = new MatchDto(
						loc.getName(),
						match.getDate(),
						getUserById(match.getUserOneId(), userList),
						match.getUserOnePoints(),
						getUserById(match.getUserTwoId(), userList),
						match.getUserTwoPoints(),
						loc.getFee()
					);
			
			matchDtoList.add(dto);
			
		}
		
		
		return matchDtoList;
	}



	private Location getLocationById(int locationId, List<Location> locList) {

		Location loc = null;
		
		for(int index = 0; index < locList.size(); index++) {
			
			int currLocId = locList.get(index).getId();
			
			if(currLocId == locationId) {
				
				loc = locList.get(index);
				break;
			}
			
		}
		
		return loc;
	}



	private UserDto getUserById(int userId, List<User> userList) {

		UserDto dto = null;
		
		for(int index = 0; index < userList.size(); index++) {
			
			User user = userList.get(index);
			
			if(user.getId() == userId) {
				dto = convertUserToDto(user);
			}
			
		}
		
		return dto;
	}



	public UserDto changePassword(String uName, String oldPwd, String newPwd) {

		User user = db.firstPwdChange(uName, oldPwd, newPwd);
		
		UserDto userDto = convertUserToDto(user);
		
		return userDto;
	}



	private UserDto convertUserToDto(User user) {

		UserDto userDto = null;
		
		if(user != null) {
			
			userDto = new UserDto(
					user.getId(),
					user.getName(),
					user.getIsLoggedIn(),
					user.isAdmin()
				);
			
		}
			
		return userDto;
	}
	
	

}