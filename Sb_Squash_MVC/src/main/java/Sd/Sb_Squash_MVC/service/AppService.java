package Sd.Sb_Squash_MVC.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import Sd.Sb_Squash_MVC.db.Database;
import Sd.Sb_Squash_MVC.dto.AdminDto;
import Sd.Sb_Squash_MVC.dto.LocationDto;
import Sd.Sb_Squash_MVC.dto.MatchDto;
import Sd.Sb_Squash_MVC.dto.MatchListDto;
import Sd.Sb_Squash_MVC.dto.UserDto;
import Sd.Sb_Squash_MVC.model.Exchange;
import Sd.Sb_Squash_MVC.model.Location;
import Sd.Sb_Squash_MVC.model.Match;
import Sd.Sb_Squash_MVC.model.SearchBy;
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



	public MatchListDto getMatchListDto(SearchBy choice, Integer id) {

		
		List<User> userList = db.getUserList();
		List<UserDto> userDtoList = convertUserListToDtoList(userList);
		
		List<Location> locList = db.getLocationList();
		List<LocationDto> locationDtoList = convertLocationListToDtoList(locList);
		
		List<Match> matchList = db.getMatchList(choice, id);
		List<MatchDto> matchDtoList = convertMatchListToDtoList(matchList, locList, userList);
		
		MatchListDto matchListDto = new MatchListDto(matchDtoList, userDtoList, locationDtoList);
		
		return matchListDto;
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
		
		if(matchList != null) {
			for(int index = 0; index < matchList.size(); index++) {
				
				Match match = matchList.get(index);
				Location loc = getLocationById(match.getLocationId(), locList);
				
				RestTemplate rt = new RestTemplate();
				
				Exchange eurFee = rt.getForObject("http://localhost:8081/exchange?huf=" + loc.getFee(), Exchange.class);
				
				MatchDto dto = new MatchDto(
							loc.getName(),
							match.getDate(),
							getUserById(match.getUserOneId(), userList),
							match.getUserOnePoints(),
							getUserById(match.getUserTwoId(), userList),
							match.getUserTwoPoints(),
							loc.getFee(),
							eurFee.getEur()
						);
				
				matchDtoList.add(dto);
				
			}
		}
		else {
			matchDtoList = null;
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



	public UserDto getUserDtoById(int userId) {

		
		User user = db.getUserById(userId);
		
		UserDto userDto = convertUserToDto(user);
		
		
		return userDto;
	}



	public AdminDto getAdminDto() {

		List<User> userList = db.getUserList();
		List<UserDto> userDtoList = convertUserListToDtoList(userList);
		
		List<Location> locList = db.getLocationList();
		List<LocationDto> locationDtoList = convertLocationListToDtoList(locList);
		
		AdminDto adminDto = new AdminDto(userDtoList, locationDtoList);
		
		return adminDto;
	}



	public int regUser(String newUserName) {

		int result = db.regUserInDb(newUserName);
		
		return result;
	}



	public int regLocation(String name, String address, int fee) {

		int result = db.persistLocation(name, address, fee);
		
		return result;
	}



	public int regMatch(int user1Id, int user1Points, int user2Id, int user2Points, int locId, LocalDate date) {

		int result = 1;
		int diff = Math.abs(user1Points - user2Points);
		
		
		if(user1Id != user2Id && diff > 1 && (user1Points > 8 || user2Points > 8)) {
			result = 2;
			Match match = new Match();
			match.setId(0);
			match.setUserOneId(user1Id);
			match.setUserOnePoints(user1Points);
			match.setUserTwoId(user2Id);
			match.setUserTwoPoints(user2Points);
			match.setLocationId(locId);
			match.setDate(date);
			
			db.persistMatch(match);
		}
		
		return result;
	}



	
	

}
