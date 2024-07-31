package Sd.Sb_EURExchange_Rest.xml;



import java.io.IOException;
import java.io.StringReader;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Repository;


@Repository
public class XMLParser {

	public Double getExchangeRate(String xml) throws JDOMException, IOException {

		
		
		SAXBuilder builder = new SAXBuilder();
		
		StringReader sr = new StringReader(xml);
		
		Document doc = builder.build(sr);
		
		Element rootElement = doc.getRootElement();
		
		Element valutaElement = rootElement.getChild("valuta");
		
		Element itemElement = valutaElement.getChild("item");
		
		Element vetelElement = itemElement.getChild("vetel");
		Double vetel = Double.parseDouble(vetelElement.getValue());
		
		Element eladasElement = itemElement.getChild("eladas");
		Double eladas = Double.parseDouble(eladasElement.getValue());
		
		Double result = (vetel + eladas) / 2;
		
		return result;
	}

	
	
	
	

}
