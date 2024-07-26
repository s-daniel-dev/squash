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


	public List<Match> getMatchList() {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Match> query = session.createSelectionQuery(
					"SELECT m FROM Match m",
					Match.class
				);
		
		List<Match> matches = query.getResultList(); 
		
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


	
	
	
	

}
