package Sd.Sb_EURExchange_Rest.dto;

public class ExchangeDto {
	
	
	private double eur;

	
	public ExchangeDto(double eur) {
		super();
		this.eur = eur;
	}
	

	public double getEur() {
		return eur;
	}

	public void setEur(double eur) {
		this.eur = eur;
	}


	@Override
	public String toString() {
		return "ExchangeDto [eur=" + eur + "]";
	}
	
	

}
