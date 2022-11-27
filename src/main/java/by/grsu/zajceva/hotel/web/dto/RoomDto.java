package by.grsu.zajceva.hotel.web.dto;

import java.sql.Timestamp;

public class RoomDto {
	private Integer id;

	private String number;
	
	private String apartment;
	
	private Integer numberBed;
	
	private float price;
	
	private Integer status;
	
	private Timestamp created;
	
	private Timestamp updated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	


	public Integer getNumberBed() {
		return numberBed;
	}

	public void setNumberBed(Integer numberBed) {
		this.numberBed = numberBed;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}}

	
