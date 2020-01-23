package test.projekat.dto;

import test.projekat.model.Ad;

public class AdDTO {

	
	private Long id;
	
	private String title;
	
	private String description;
	
	private double price;
	
	private String location;
	
	private String readme;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getReadme() {
		return readme;
	}

	public void setReadme(String readme) {
		this.readme = readme;
	}

	public AdDTO(Long id, String title, String description, double price, String location, String readme) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.location = location;
		this.readme = readme;
	}
	public AdDTO(Ad ad) {
		super();
		this.id = ad.getId();
		this.title = ad.getTitle();
		this.description = ad.getDescription();
		this.price = ad.getPrice();
		this.location = ad.getLocation();
		this.readme = ad.getReadme();
	}
	
	public AdDTO() {
		
	}
	
}
