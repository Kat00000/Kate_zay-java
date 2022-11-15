package by.grsu.zajceva.hotel.web.dto;


import java.sql.Timestamp;

public class OrderDto {

	private Integer id;
	
	private Integer serviceId;
	
	private String serviceName;
	
	private Integer userId;
	
	private String userName;
	
	private Integer roomId;
	
	private String roomName;
	
	private Integer timeStay;
	
	//private String timeStayName;
	
	private Timestamp created;

	private Timestamp updated;

	public Integer getId() {
		return id;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Integer getTimeStay() {
		return timeStay;
	}

	public void setTimeStay(Integer timeStay) {
		this.timeStay = timeStay;
	}

	/*public String getTimeStayName() {
		return timeStayName;
	}

	public void setTimeStayName(String timeStayName) {
		this.timeStayName = timeStayName;
	}*/

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

	public void setId(Integer id) {
		this.id = id;
	}
	
	

	





}