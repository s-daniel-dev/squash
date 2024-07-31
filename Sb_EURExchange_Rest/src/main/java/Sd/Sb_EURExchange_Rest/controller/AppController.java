package Sd.Sb_EURExchange_Rest.controller;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Sd.Sb_EURExchange_Rest.dto.ExchangeDto;
import Sd.Sb_EURExchange_Rest.service.AppService;

@RestController
public class AppController {
	
	private AppService service;

	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/exchange")
	public ExchangeDto exchangeHUFtoEUR(
			@RequestParam("huf") int huf
			) throws JDOMException, IOException {
		
		ExchangeDto exchangeDto = service.getExchangeDto(huf);
		
		
		return exchangeDto;
	}

}
