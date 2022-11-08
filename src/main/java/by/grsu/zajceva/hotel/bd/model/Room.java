package by.grsu.zajceva.hotel.bd.model;

import java.sql.Timestamp;
public class Room {
	private Integer id;
	private String apartment;
	private Integer numberBeds;
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
	public Integer getNumberBeds() {
		return numberBeds;
	}
	public void setNumberBeds(Integer numberBeds) {
		this.numberBeds = numberBeds;
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
	}
	@Override
	public String toString() {
		return "room [id=" + id + ", apartment=" + apartment + ", numberBeds=" + numberBeds + ", price=" + price
				+ ", status=" + status + ", created=" + created + ", updated=" + updated + "]";
	}
}
