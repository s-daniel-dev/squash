package Sd.Sb_Squash_MVC.XMLParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.stereotype.Repository;

import Sd.Sb_Squash_MVC.model.Location;
import Sd.Sb_Squash_MVC.model.Match;
import Sd.Sb_Squash_MVC.model.User;

@Repository
public class XMLParser {

	public void makeXML(List<User> userList, List<Location> locList, List<Match> matchList) throws IOException {

		FileWriter writer = new FileWriter("squash.xml");
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		
		Document doc = new Document();
		
		Element rootElement = new Element("Squash");
		doc.addContent(rootElement);
		
		Element usersElement = new Element("users");
		rootElement.addContent(usersElement);
		
		for(int index = 0; index < userList.size(); index++) {
			
			User user = userList.get(index);
			
			Element uElement = new Element("user");
			usersElement.addContent(uElement);
			
				Element idElement = new Element("id");
				idElement.setText( user.getId() + "" );
				uElement.addContent(idElement);
				
				Element nameElement = new Element("name");
				nameElement.setText( user.getName() );
				uElement.addContent(nameElement);
				
				Element pwdElement = new Element("pwd");
				pwdElement.setText( user.getPwd() );
				uElement.addContent(pwdElement);
				
				Element adminElement = new Element("admin");
				adminElement.setText( user.isAdmin() + "" );
				uElement.addContent(adminElement);
				
				Element loggedInElement = new Element("loggedin");
				loggedInElement.setText( user.getIsLoggedIn() + "" );
				uElement.addContent(loggedInElement);
			
			
		}
		
		
		
		Element locationsElement = new Element("locations");
		rootElement.addContent(locationsElement);
		
		for(int index = 0; index < locList.size(); index++) {
			
			Location loc = locList.get(index);
			
			Element locElement = new Element("location");
			locationsElement.addContent(locElement);
			
				Element idElement = new Element("id");
				idElement.setText( loc.getId() + "" );
				locElement.addContent(idElement);
				
				Element nameElement = new Element("name");
				nameElement.setText( loc.getName() );
				locElement.addContent(nameElement);
				
				Element addressElement = new Element("address");
				addressElement.setText( loc.getAddress() );
				locElement.addContent(addressElement);
				
				Element feeElement = new Element("fee");
				feeElement.setText( loc.getFee() + "" );
				locElement.addContent(feeElement);
			
			
		}
	
		
		
		Element matchesElement = new Element("matches");
		rootElement.addContent(matchesElement);
		
		for(int index = 0; index < matchList.size(); index++) {
			
			Match match = matchList.get(index);
			
			Element matchElement = new Element("match");
			matchesElement.addContent(matchElement);
			
			Element idElement = new Element("id");
			idElement.setText( match.getId() + "" );
			matchElement.addContent(idElement);
			
			Element playersElement = new Element("players");
			matchElement.addContent(playersElement);
			
				Element player1Element = new Element("player");
				playersElement.addContent(player1Element);
				
					Element p1IdElement = new Element("id");
					p1IdElement.setText(match.getUserOneId() + "");
					player1Element.addContent(p1IdElement);
					
					Element p1PointsElement = new Element("points");
					p1PointsElement.setText( match.getUserOnePoints() + "" );
					player1Element.addContent(p1PointsElement);
			
				Element player2Element = new Element("player");
				playersElement.addContent(player2Element);
				
					Element p2IdElement = new Element("id");
					p2IdElement.setText(match.getUserTwoId() + "");
					player2Element.addContent(p2IdElement);
					
					Element p2PointsElement = new Element("points");
					p2PointsElement.setText( match.getUserTwoPoints() + "" );
					player2Element.addContent(p2PointsElement);
					
			Element locIdElement = new Element("locationid");
			locIdElement.setText( match.getLocationId() + "" );
			matchElement.addContent(locIdElement);
			
			Element dateElement = new Element("date");
			dateElement.setText( match.getDate() + "" );
			matchElement.addContent(dateElement);
			
		}
		
		
		outputter.output(doc, writer);
		writer.close();
		
		
	}

	public List<User> getUserList() throws JDOMException, IOException {

		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(new File("squash.xml"));
		
		Element rootElement = doc.getRootElement();
		
		Element usersElement = rootElement.getChild("users");
		
		List<User> users = new ArrayList<>();
		
		List<Element> userElementList = usersElement.getChildren("user");
		
		for(int index = 0; index < userElementList.size(); index++) {
			
			Element uE = userElementList.get(index);
			
			Element idElement = uE.getChild("id");
			int id = Integer.parseInt(idElement.getValue());
			
			Element nameElement = uE.getChild("name");
			String name = nameElement.getValue();
			
			Element pwdElement = uE.getChild("pwd");
			String pwd = pwdElement.getValue();
			
			Element adminElement = uE.getChild("admin");
			boolean admin = Boolean.parseBoolean(adminElement.getValue());
			
			Element loggedInElement = uE.getChild("loggedin");
			Boolean loggedIn = Boolean.parseBoolean(loggedInElement.getValue());
			
			
			User user = new User(id, name, pwd, admin, loggedIn);
			users.add(user);
		}
		
		
		
		return users;
	}

	public List<Location> getLocationList() throws JDOMException, IOException {

		SAXBuilder sb = new SAXBuilder();
		
		Document doc = sb.build(new File("squash.xml"));
		
		Element rootElement = doc.getRootElement();
		
		Element locationsElement = rootElement.getChild("locations");
		
		List<Element> locElementList = locationsElement.getChildren("location");
		
		List<Location> locationList = new ArrayList<>();
		
		for(int index = 0; index < locElementList.size(); index++) {
			
			Element locationElement = locElementList.get(index);
			
			Element idElement = locationElement.getChild("id");
			int id = Integer.parseInt(idElement.getValue());
			
			Element nameElement = locationElement.getChild("name");
			String name = nameElement.getValue();
			
			Element addressElement = locationElement.getChild("address");
			String address = addressElement.getValue();
			
			Element feeElement = locationElement.getChild("fee");
			int fee = Integer.parseInt(feeElement.getValue());
			
			Location loc = new Location(id, name, address, fee);
			locationList.add(loc);
			
		}
		return locationList;
	}
	

	public List<Match> getMatchList() throws JDOMException, IOException {

		List<Match> matchList = new ArrayList<>();
		
		SAXBuilder sb = new SAXBuilder();
		
		Document doc = sb.build(new File("squash.xml"));
		
		Element rootElement = doc.getRootElement();
		
		Element matchesElement = rootElement.getChild("matches");
		
		List<Element> matchElementList = matchesElement.getChildren("match");
		
		for(int index = 0; index < matchElementList.size(); index++) {
			
			Match match = new Match();
			
			Element matchElement = matchElementList.get(index);
			
			Element idElement = matchElement.getChild("id");
			int id = Integer.parseInt(idElement.getValue());
			match.setId(id);
			
			Element playersElement = matchElement.getChild("players");
			
			List<Element> playerElementList = playersElement.getChildren("player");
			
			
			for(int playerIndex = 0; playerIndex < playerElementList.size(); playerIndex++) {
				
				Element playerElement = playerElementList.get(playerIndex);
				
				Element playerIdElement = playerElement.getChild("id");
				int playerId = Integer.parseInt(playerIdElement.getValue());
				
				Element playerPointsElement = playerElement.getChild("points");
				int playerPoints = Integer.parseInt(playerPointsElement.getValue());
				
				if(playerIndex == 0) {
					match.setUserOneId(playerId);
					match.setUserOnePoints(playerPoints);
				}
				else if(playerIndex == 1) {
					match.setUserTwoId(playerId);
					match.setUserTwoPoints(playerPoints);
				}
				
			}
			
			Element locIdElement = matchElement.getChild("locationid");
			int locId = Integer.parseInt(locIdElement.getValue());
			match.setLocationId(locId);
			
			Element dateElement = matchElement.getChild("date");
			LocalDate date = LocalDate.parse(dateElement.getValue());
			match.setDate(date);
			
			
			
			matchList.add(match);
			
		}
		
		
		return matchList;
	}

	
	
	
	
	

}
