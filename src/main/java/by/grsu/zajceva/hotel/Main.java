package by.grsu.zajceva.hotel;
import java.sql.Timestamp;

import java.util.Date;
import by.grsu.zajceva.hotel.bd.model.*;
public class Main {

	public static void main(String[] args) {
		order order = new order();
		user user = new user();
		service service = new service();
		room room = new room();
		
		order.setCreated(new Timestamp(new Date().getTime()));
		order.setId(1);
		order.setRoom_id(100);
		order.setService_id(1);
		order.setTime_stay(3);
		order.setUpdated(new Timestamp(new Date().getTime()));
		order.setUser_id(1);
		System.out.println(order.toString());
		
		user.setCreated(new Timestamp(new Date().getTime()));
		user.setEmail("user@us.com");
		user.setId(1);
		user.setName("Vasa");
		user.setPassword("1111");
		user.setUpdated(new Timestamp(new Date().getTime()));
		System.out.println(user.toString());
		
		
		service.setCreated(new Timestamp(new Date().getTime()));
		service.setId(1);
		service.setPrice(5);
		service.setType("cleaning");
		service.setUpdated(new Timestamp(new Date().getTime()));
		System.out.println(service.toString());
		
		
		room.setCreated(new Timestamp(new Date().getTime()));
		room.setApartment("A");
		room.setId(1);
		room.setNumber_beds(1);
		room.setPrice(10);
		room.setStatus(2);
		room.setUpdated(new Timestamp(new Date().getTime()));
		System.out.println(room.toString());
	}
	

}
