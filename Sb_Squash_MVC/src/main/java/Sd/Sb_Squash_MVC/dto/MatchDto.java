package Sd.Sb_Squash_MVC.dto;

import java.time.LocalDate;



public class MatchDto {
	
	private String locationName;
	private LocalDate date;
	private UserDto user1;
	private int user1Points;
	private UserDto user2;
	private int user2Points;
	private int locFeeHUF;
	private Double locFeeEUR;
	
	
	



	

	public MatchDto(String locationName, LocalDate date, UserDto user1, int user1Points, UserDto user2, int user2Points,
			int locFeeHUF, Double eurFee) {
		super();
		this.locationName = locationName;
		this.date = date;
		this.user1 = user1;
		this.user1Points = user1Points;
		this.user2 = user2;
		this.user2Points = user2Points;
		this.locFeeHUF = locFeeHUF;
		this.locFeeEUR = eurFee;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public UserDto getUser1() {
		return user1;
	}

	public void setUser1(UserDto user1) {
		this.user1 = user1;
	}

	public int getUser1Points() {
		return user1Points;
	}

	public void setUser1Points(int user1Points) {
		this.user1Points = user1Points;
	}

	public UserDto getUser2() {
		return user2;
	}

	public void setUser2(UserDto user2) {
		this.user2 = user2;
	}

	public int getUser2Points() {
		return user2Points;
	}

	public void setUser2Points(int user2Points) {
		this.user2Points = user2Points;
	}

	public int getLocFeeHUF() {
		return locFeeHUF;
	}

	public void setLocFeeHUF(int locFeeHUF) {
		this.locFeeHUF = locFeeHUF;
	}

	public Double getLocFeeEUR() {
		return locFeeEUR;
	}

	public void setLocFeeEUR(Double locFeeEUR) {
		this.locFeeEUR = locFeeEUR;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	

}
