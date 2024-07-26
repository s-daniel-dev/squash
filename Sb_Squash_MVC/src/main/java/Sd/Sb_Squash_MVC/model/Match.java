package Sd.Sb_Squash_MVC.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "matches")
public class Match {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "user1_id")
	private int userOneId;
	
	@Column(name = "user1_points")
	private int userOnePoints;
	
	@Column(name = "user2_id")
	private int userTwoId;
	
	@Column(name = "user2_points")
	private int userTwoPoints;
	
	@Column(name = "location_id")
	private int locationId;
	
	@Column(name = "date")
	private LocalDate date;
	
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserOneId() {
		return userOneId;
	}
	
	public void setUserOneId(int userOneId) {
		this.userOneId = userOneId;
	}
	
	public int getUserOnePoints() {
		return userOnePoints;
	}
	
	public void setUserOnePoints(int userOnePoints) {
		this.userOnePoints = userOnePoints;
	}
	
	public int getUserTwoId() {
		return userTwoId;
	}
	
	public void setUserTwoId(int userTwoId) {
		this.userTwoId = userTwoId;
	}
	
	public int getUserTwoPoints() {
		return userTwoPoints;
	}
	
	public void setUserTwoPoints(int userTwoPoints) {
		this.userTwoPoints = userTwoPoints;
	}
	
	public int getLocationId() {
		return locationId;
	}
	
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	

}
