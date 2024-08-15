package Sd.Sb_Squash_MVC.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.SelectionQuery;
import org.springframework.stereotype.Repository;


import Sd.Sb_Squash_MVC.model.Location;
import Sd.Sb_Squash_MVC.model.Match;
import Sd.Sb_Squash_MVC.model.SearchBy;
import Sd.Sb_Squash_MVC.model.User;

@Repository
public class Database {
	
	private SessionFactory sessionFactory;

	
	public Database() {

		Configuration cfg = new Configuration();
		cfg.configure();
		sessionFactory = cfg.buildSessionFactory();
		
	}


	public User loginUser(String uName, String uPwd) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<User> query = 
				session.createSelectionQuery(
				"SELECT u FROM User u WHERE name=?1 AND pwd=?2",
				User.class);
		query.setParameter(1, uName);
		query.setParameter(2, uPwd);
		
		 
		List<User> users = query.getResultList();
		User user = null;
		
		if(users.size() > 0) {
			user = users.get(0);
		}
		
		tx.commit();
		session.close();
		
		return user;
	}


	public List<Match> getMatchList(SearchBy choice, Integer id) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		List<Match> matches = null;
		
		
		if(choice == SearchBy.ALL) {
			
			SelectionQuery<Match> query = session.createSelectionQuery(
					"SELECT m FROM Match m",
					Match.class
				);
			matches = query.getResultList(); 
			
		}
		else if(choice == SearchBy.PLAYER && id != null) {
			
			SelectionQuery<Match> query = session.createSelectionQuery(
					"SELECT m FROM Match m WHERE userOneId=?1 OR userTwoId=?1",
					Match.class
				);
			query.setParameter(1, id);
			
			matches = query.getResultList();
			
		}
		else if(choice == SearchBy.LOCATION && id != null) {
			
			SelectionQuery<Match> query = session.createSelectionQuery(
					"SELECT m FROM Match m WHERE locationId=?1",
					Match.class
				);
			query.setParameter(1, id);
			
			matches = query.getResultList(); 
			
		}
		
		tx.commit();
		session.close();
		
		return matches;
	}


	public List<Location> getLocationList() {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Location> query = session.createSelectionQuery(
					"SELECT l FROM Location l",
					Location.class
				);
		
		List<Location> locations = query.getResultList(); 
		
		tx.commit();
		session.close();
		
		return locations;
	}


	public List<User> getUserList() {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<User> query = session.createSelectionQuery(
					"SELECT u FROM User u",
					User.class
				);
		
		List<User> users = query.getResultList(); 
		
		tx.commit();
		session.close();
		
		return users;
	}


	public User firstPwdChange(String uName, String oldPwd, String newPwd) {

		User user = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<User> query = session.createSelectionQuery(
					"SELECT u FROM User u WHERE name=?1 AND pwd=?2",
					User.class
				);
		query.setParameter(1, uName);
		query.setParameter(2, oldPwd);
		
		List<User> users = query.getResultList();
		
		if(users.size() > 0) {
			user = users.get(0);
			user.setPwd(newPwd);
			user.setIsLoggedIn(true);
			session.merge(user);
		}
		
		
		tx.commit();
		session.close();
		
		return user;
	}


	public User getUserById(int userId) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		User user = session.get(User.class, userId);
		
		tx.commit();
		session.close();
		
		
		return user;
	}


	public int regUserInDb(User user) {

		int result = 1;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<User> query = session.createSelectionQuery(
					"SELECT u FROM User u WHERE name=?1",
					User.class
				);
		query.setParameter(1, user.getName());
		
		List<User> users = query.getResultList();
		
		if(users.size() == 0) {
			
			session.persist(user);
			result = 2;
			
		}
		
		tx.commit();
		session.close();
		
		return result;
	}


	public int persistLocation(String name, String address, int fee) {

		int result = 1;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Location> query = session.createSelectionQuery(
					"SELECT l FROM Location l WHERE name=?1",
					Location.class
				);
		query.setParameter(1, name);
		
		List<Location> locations = query.getResultList();
		
		if(locations.size() == 0) {
			
			result = 2;
			
			Location loc = new Location();
			loc.setName(name);
			loc.setId(0);
			loc.setAddress(address);
			loc.setFee(fee);
			
			session.persist(loc);
			
		}
		
		tx.commit();
		session.close();
		
		
		return result;
	}


	public void persistMatch(Match match) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(match);
		
		tx.commit();
		session.close();
		
	}


	
	
	
	

}
