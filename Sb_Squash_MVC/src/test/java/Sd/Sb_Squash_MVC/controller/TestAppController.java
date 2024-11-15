package Sd.Sb_Squash_MVC.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import Sd.Sb_Squash_MVC.dto.ResultDto;
import Sd.Sb_Squash_MVC.service.AppService;

@WebMvcTest(AppController.class)
public class TestAppController {
	
	@Autowired
	@MockBean
	private AppService service;
	
	@Autowired
	private MockMvc mocito;
	
	@Test
	public void testLoginPage() throws Exception {
		
		mocito.perform(MockMvcRequestBuilders.get("/"))
			.andExpect(MockMvcResultMatchers.view().name("login.html"));
		
	}
	
	@Test
	public void loginWithUserDtoNullTest() throws Exception {
		
		final String name = "name";
		final String pwd = "pwd";
		
		BDDMockito.given(service.loginUser(name, pwd)).willReturn(null);
		
		mocito.perform(MockMvcRequestBuilders.post("/login").param("uname", name).param("upwd", pwd))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("login.html"))
			.andExpect(MockMvcResultMatchers.model().attribute("resultDto", Matchers.hasProperty("error", Matchers.is(true))));
			
			
		
	}
}
