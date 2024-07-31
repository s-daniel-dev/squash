package Sd.Sb_EURExchange_Rest.service;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import Sd.Sb_EURExchange_Rest.dto.ExchangeDto;
import Sd.Sb_EURExchange_Rest.xml.XMLParser;

@Service
public class AppService {
	
	private XMLParser parser;
	private RestTemplate rt;
	
	@Autowired
	public AppService(XMLParser parser) {
		super();
		this.parser = parser;
		this.rt = new RestTemplate();
	}




	public ExchangeDto getExchangeDto(int huf) throws JDOMException, IOException {

		ExchangeDto dto = null;
		
		Double exchangeRate = getExchangeRate();
		
		if(exchangeRate != null) {
			
			double result = huf / exchangeRate;
			
			dto = new ExchangeDto(result);
			
		}
		
		return dto;
	}




	private Double getExchangeRate() throws JDOMException, IOException {

		Double rate = null;
		
		String xml = rt.getForObject(
					"http://api.napiarfolyam.hu/?valuta=eur&bank=erste&valutanem=valuta",
					String.class
				);

		rate = parser.getExchangeRate(xml);
		
		return rate;
	}

}
