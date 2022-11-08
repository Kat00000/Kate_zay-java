package by.grsu.zajceva.hotel.bd.model;
import java.sql.Timestamp;
public class order {
private Integer id;
private Integer service_id;
private Integer user_id;
private Integer room_id;
private Integer time_stay;
private Timestamp created;
private Timestamp updated;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getService_id() {
	return service_id;
}
public void setService_id(Integer service_id) {
	this.service_id = service_id;
}
public Integer getUser_id() {
	return user_id;
}
public void setUser_id(Integer user_id) {
	this.user_id = user_id;
}
public Integer getRoom_id() {
	return room_id;
}
public void setRoom_id(Integer room_id) {
	this.room_id = room_id;
}
public Integer getTime_stay() {
	return time_stay;
}
public void setTime_stay(Integer time_stay) {
	this.time_stay = time_stay;
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
	return "order [id=" + id + ", service_id=" + service_id + ", user_id=" + user_id + ", room_id=" + room_id
			+ ", time_stay=" + time_stay + ", created=" + created + ", updated=" + updated + "]";
}
}
