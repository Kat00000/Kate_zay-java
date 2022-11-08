package by.grsu.zajceva.hotel.bd.model;
import java.sql.Timestamp;
public class Order {
private Integer id;
private Integer serviceId;
private Integer userId;
private Integer roomId;
private Integer timeStay;
private Timestamp created;
private Timestamp updated;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getServiceId() {
	return serviceId;
}
public void setServiceId(Integer serviceId) {
	this.serviceId = serviceId;
}
public Integer getUserId() {
	return userId;
}
public void setUserId(Integer userId) {
	this.userId = userId;
}
public Integer getRoomId() {
	return roomId;
}
public void setRoomId(Integer roomId) {
	this.roomId = roomId;
}
public Integer getTimeStay() {
	return timeStay;
}
public void setTimeStay(Integer timeStay) {
	this.timeStay = timeStay;
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
	return "Order [id=" + id + ", serviceId=" + serviceId + ", userId=" + userId + ", roomId=" + roomId + ", timeStay="
			+ timeStay + ", created=" + created + ", updated=" + updated + "]";
}

}
