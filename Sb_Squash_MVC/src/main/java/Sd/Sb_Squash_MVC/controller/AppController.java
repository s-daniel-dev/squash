package Sd.Sb_Squash_MVC.controller;


import java.io.IOException;
import java.time.LocalDate;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Sd.Sb_Squash_MVC.dto.AdminDto;
import Sd.Sb_Squash_MVC.dto.MatchListDto;
import Sd.Sb_Squash_MVC.dto.ResultDto;
import Sd.Sb_Squash_MVC.dto.UserDto;
import Sd.Sb_Squash_MVC.model.SearchBy;
import Sd.Sb_Squash_MVC.service.AppService;

@Controller
public class AppController {
	
	
	private AppService service;

	
	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;
	}
	
	
	@GetMapping("/")
	public String loginPage() {
		return "login.html";
	}
	
	@PostMapping("/login")
	public String login(
			Model model,
			@RequestParam("uname") String uName,
			@RequestParam("upwd") String uPwd
			) {
		
		String returnValue = "login.html";
		UserDto userDto = service.loginUser(uName, uPwd);
		
		if(userDto == null) {
			
			ResultDto resultDto = new ResultDto();
			model.addAttribute("resultDto", resultDto);
			
		} 
		else if(userDto != null) {
			
			if(userDto.getIsLoggedIn() != null) {
				
				returnValue = "index.html";
				MatchListDto matchListDto = service.getMatchListDto(SearchBy.ALL, null);
				model.addAttribute("matchListDto", matchListDto);
				
			}
			
			model.addAttribute("userDto", userDto);
		}
		
		
		return returnValue;
	}
	
	@PostMapping("/login/changepwd")
	public String newUserPwdChangeOrShowMatches(
			Model model,
			@RequestParam("uname") String uName,
			@RequestParam("oldpwd") String oldPwd,
			@RequestParam("newpwd") String newPwd
			) {
		
		String returnValue = "login.html";
		
		UserDto userDto = service.changePassword(uName, oldPwd, newPwd);
		ResultDto resultDto = new ResultDto();
		
		if(userDto != null) {
			
			resultDto.setError(false);
			MatchListDto matchListDto = service.getMatchListDto(SearchBy.ALL, null);
			model.addAttribute("matchListDto", matchListDto);
			model.addAttribute("userDto", userDto);
			returnValue = "index.html";
			
		}
		model.addAttribute("resultDto", resultDto);
		
		return returnValue;
	}
	
	@GetMapping("/matches/search/user")
	public String searchAmoungMatchesByPlayer(
				Model model,
				@RequestParam("uid") int userId,
				@RequestParam("playerid") int playerId
			) {
		
		UserDto userDto = service.getUserDtoById(userId);
		
		MatchListDto matchListDto = service.getMatchListDto(SearchBy.PLAYER, playerId);
		
		model.addAttribute("userDto", userDto);
		model.addAttribute("matchListDto", matchListDto);
		
		
		return "index.html";
	}
	
	@GetMapping("/matches/search/location")
	public String searchAmoungMatchesByLocation(
				Model model,
				@RequestParam("uid") int userId,
				@RequestParam("locationid") int locId
			) {
		
		UserDto userDto = service.getUserDtoById(userId);
		
		MatchListDto matchListDto = service.getMatchListDto(SearchBy.LOCATION, locId);
		
		model.addAttribute("userDto", userDto);
		model.addAttribute("matchListDto", matchListDto);
		
		
		return "index.html";
	}
	
	@GetMapping("/admin")
	public String adminIndex(
				Model model,
				@RequestParam("uid") int userId
			) {
		
		UserDto userDto = service.getUserDtoById(userId);
		String result = "login.html";
		AdminDto adminDto = null;
		
		if(userDto != null && userDto.getIsLoggedIn() == true && userDto.isAdmin() == true) {
			
			result = "admin.html";
			adminDto = service.getAdminDto();
			
		}
		
		model.addAttribute("adminDto", adminDto);
		
		
		return result;
	}
	
	@PostMapping("/admin/reg/user")
	public String registrateUser(
				Model model,
				@RequestParam("uname") String newUserName
			) {
		
		
		ResultDto resultDto = new ResultDto();
		
		int result = service.regUser(newUserName);
		AdminDto adminDto = service.getAdminDto();
		resultDto.setUserReg(result);
		
		
		model.addAttribute("adminDto", adminDto);
		model.addAttribute("resultDto", resultDto);
		
		
		return "admin.html";
	}
	
	@PostMapping("/admin/reg/loc")
	public String registrateLocation(
				Model model,
				@RequestParam("name") String name,
				@RequestParam("address") String address,
				@RequestParam("fee") int fee
			) {
		
		
		ResultDto resultDto = new ResultDto();
		
		int result = service.regLocation(name, address, fee);
		AdminDto adminDto = service.getAdminDto();
		
		resultDto.setLocationReg(result);
		
		model.addAttribute("adminDto", adminDto);
		model.addAttribute("resultDto", resultDto);
		
		return "admin.html";
	}
	
	
	@PostMapping("/admin/reg/match")
	public String registrateMatch(
				Model model,
				@RequestParam("user1id") int user1Id,
				@RequestParam("user1points") int user1Points,
				@RequestParam("user2id") int user2Id,
				@RequestParam("user2points") int user2Points,
				@RequestParam("locationid") int locId,
				@RequestParam("date") LocalDate date
			) {
		
		
		ResultDto resultDto = new ResultDto();
		
		int result = service.regMatch(
					user1Id, user1Points,
					user2Id, user2Points,
					locId, date
				);
		
		resultDto.setMatchReg(result);
		AdminDto adminDto = service.getAdminDto();
		
		model.addAttribute("adminDto", adminDto);
		model.addAttribute("resultDto", resultDto);
		
		return "admin.html";
	}
	
	@GetMapping("/xml/create")
	public String makeXML(Model model) {
		
		boolean result = service.getXMLFile();
		
		model.addAttribute("result", result);
		
		return "result.html";
	}
	
	@GetMapping("/xml/read")
	public String showMatchListFromXML(
				Model model,
				@RequestParam("uid") int userId
			) throws JDOMException, IOException {
		
		UserDto userDto = service.getUserDtoById(userId);
		MatchListDto matchListDto = service.getMatchListDtoFromXML();
		
		model.addAttribute("userDto", userDto);
		model.addAttribute("matchListDto", matchListDto);
		
		
		
		return "index.html";
	}

}
