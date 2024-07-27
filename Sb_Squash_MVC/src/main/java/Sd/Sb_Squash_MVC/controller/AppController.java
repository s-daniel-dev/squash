package Sd.Sb_Squash_MVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}
