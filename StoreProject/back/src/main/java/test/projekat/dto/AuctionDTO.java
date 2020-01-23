package test.projekat.dto;

import test.projekat.model.Auction;

public class AuctionDTO {

	private Long id;
	private String text;
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	private String user;
	private double amount;
	private AdDTO adDTO;
	
	public AuctionDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public AdDTO getAdDTO() {
		return adDTO;
	}

	public void setAdDTO(AdDTO adDTO) {
		this.adDTO = adDTO;
	}

	public AuctionDTO(Long id, String user, double amount, AdDTO adDTO, String text) {
		super();
		this.id = id;
		this.user = user;
		this.amount = amount;
		this.adDTO = adDTO;
		this.text = text;
	}
	public AuctionDTO(Auction auction) {
		super();
		this.id = auction.getId();
		this.user = auction.getUser();
		this.amount = auction.getAmount();
		this.adDTO = new AdDTO(auction.getAd());
		this.text = auction.getText();
	}
	
	
}
