package Sd.Sb_Squash_MVC.dto;

public class ResultDto {
	
	private boolean error;
	private int userReg;
	private int locationReg;
	private int matchReg;


	public ResultDto() {
		this.error = true;
		this.userReg = 0;
		this.locationReg = 0;
		this.matchReg = 0;
	}

	
	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public int isUserReg() {
		return userReg;
	}

	public void setUserReg(int userReg) {
		this.userReg = userReg;
	}

	public int isLocationReg() {
		return locationReg;
	}

	public void setLocationReg(int locationReg) {
		this.locationReg = locationReg;
	}

	public int isMatchReg() {
		return matchReg;
	}

	public void setMatchReg(int matchReg) {
		this.matchReg = matchReg;
	}
	
	
	
	

}
