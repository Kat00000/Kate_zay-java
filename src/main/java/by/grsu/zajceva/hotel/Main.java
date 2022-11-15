package by.grsu.zajceva.hotel;
import java.sql.Timestamp;
import java.util.Date;

import by.grsu.zajceva.hotel.bd.model.Order;
import by.grsu.zajceva.hotel.bd.model.Room;
import by.grsu.zajceva.hotel.bd.model.Service;
import by.grsu.zajceva.hotel.bd.model.User;
public class Main {

	public static void main(String[] args) {
		Order order = new Order();
		User user = new User();
		Service service = new Service();
		Room room = new Room();

		order.setCreated(new Timestamp(new Date().getTime()));
		order.setId(1);
		order.setRoomId(100);
		order.setServiceId(1);
		order.setTimeStay(3);
		order.setUpdated(new Timestamp(new Date().getTime()));
		order.setUserId(1);
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
		room.setNumberBed(1);
		room.setPrice(10);
		room.setStatus(2);
		room.setUpdated(new Timestamp(new Date().getTime()));
		System.out.println(room.toString());
	}


}
