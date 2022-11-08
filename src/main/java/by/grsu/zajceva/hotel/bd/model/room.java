package by.grsu.zajceva.hotel.bd.model;

import java.sql.Timestamp;
public class room {
	private Integer id;
	private String apartment;
	private Integer number_beds;
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
	public Integer getNumber_beds() {
		return number_beds;
	}
	public void setNumber_beds(Integer number_beds) {
		this.number_beds = number_beds;
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
		return "room [id=" + id + ", apartment=" + apartment + ", number_beds=" + number_beds + ", price=" + price
				+ ", status=" + status + ", created=" + created + ", updated=" + updated + "]";
	}
}
