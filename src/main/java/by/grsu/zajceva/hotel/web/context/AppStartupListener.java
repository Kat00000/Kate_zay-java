package by.grsu.zajceva.hotel.web.context;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.grsu.zajceva.hotel.bd.dao.AbstractDao;
import by.grsu.zajceva.hotel.bd.dao.IDao;
import by.grsu.zajceva.hotel.bd.dao.impl.OrderDaoImpl;
import by.grsu.zajceva.hotel.bd.dao.impl.RoomDaoImpl;
import by.grsu.zajceva.hotel.bd.dao.impl.ServiceDaoImpl;
import by.grsu.zajceva.hotel.bd.dao.impl.UserDaoImpl;
import by.grsu.zajceva.hotel.bd.model.Order;
import by.grsu.zajceva.hotel.bd.model.Room;
import by.grsu.zajceva.hotel.bd.model.Service;
import by.grsu.zajceva.hotel.bd.model.User;



public class AppStartupListener implements ServletContextListener {
	private static final IDao<Integer, Order> orderDao = OrderDaoImpl.INSTANCE;
	private static final IDao<Integer, Room> roomDao = RoomDaoImpl.INSTANCE;
	private static final IDao<Integer, Service> serviceDao = ServiceDaoImpl.INSTANCE;
	private static final IDao<Integer, User> userDao = UserDaoImpl.INSTANCE;

	private static final String DB_NAME = "production-db";

	private void initDb() throws SQLException {
		AbstractDao.init(DB_NAME);
		if (!AbstractDao.isDbExist()) {
			System.out.println(String.format("DB '%s' doesn't exist and will be created", DB_NAME));
			AbstractDao.createDbSchema();
			loadInitialData();
		} else {
			System.out.println(String.format("DB '%s' exists", DB_NAME));
		}
	}

	private void loadInitialData() {
		

		Room roomEntity = new Room();
		roomEntity.setApartment("A");
		roomEntity.setNumberBed(1);
		roomEntity.setPrice(10);
		roomEntity.setStatus(1);
		roomEntity.setCreated(getCurrentTime());
		roomEntity.setUpdated(getCurrentTime());
		roomDao.insert(roomEntity);
		System.out.println("created: " + roomEntity);
		
		User userEntity = new User();
		userEntity.setName("Vasya");		
		userEntity.setEmail("e");
		userEntity.setPassword("1111");
		userEntity.setCreated(getCurrentTime());
		userEntity.setUpdated(getCurrentTime());
		userDao.insert(userEntity);
		System.out.println("created: " + userEntity);

		Service serviceEntity = new Service();
		serviceEntity.setType("clining");	
		serviceEntity.setPrice(2);
		serviceEntity.setCreated(getCurrentTime());
		serviceEntity.setUpdated(getCurrentTime());
		serviceDao.insert(serviceEntity);
		System.out.println("created: " + serviceEntity);
		System.out.println("initial data created");
		
		Order orderEntity = new Order();
		orderEntity.setRoomId(roomEntity.getId());
		orderEntity.setServiceId(serviceEntity.getId());
		orderEntity.setUserId(userEntity.getId());
		orderEntity.setTimeStay(1);
		orderEntity.setCreated(getCurrentTime());
		orderEntity.setUpdated(getCurrentTime());
		orderDao.insert(orderEntity);
		System.out.println("created: " + orderEntity);
	}

	private Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized");
		try {
			initDb();
		} catch (SQLException e) {
			throw new RuntimeException("can't init DB", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed");
	}
}