package by.grsu.zajceva.hotel.bd.dao.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.zajceva.hotel.bd.dao.IDao;
import by.grsu.zajceva.hotel.bd.model.Order;
import by.grsu.zajceva.hotel.bd.model.Room;
import by.grsu.zajceva.hotel.bd.model.Service;
import by.grsu.zajceva.hotel.bd.model.User;




public class OrderDaoTest extends AbstractTest {
	private static final IDao<Integer, Order> dao = OrderDaoImpl.INSTANCE;
	private static final IDao<Integer, User> userDao = UserDaoImpl.INSTANCE;
	private static final IDao<Integer, Service> serviceDao = ServiceDaoImpl.INSTANCE;
	private static final IDao<Integer, Room> roomDao = RoomDaoImpl.INSTANCE;
	@Test
	public void testInsert() {
		Order entity = new Order();
		entity.setServiceId(saveService().getId());
		entity.setUserId(saveUser().getId());
		entity.setRoomId(saveRoom().getId());
		entity.setTimeStay(1);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Order entity = new Order();
		entity.setServiceId(saveService().getId());
		entity.setUserId(saveUser().getId());
		entity.setRoomId(saveRoom().getId());
		entity.setTimeStay(1);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);
        
		Integer newTimeStay =2;
		entity.setTimeStay(newTimeStay);
		entity.setUpdated(getCurrentTime());
		dao.update(entity);

		Order updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals( newTimeStay, updatedEntity.getTimeStay());
		Assertions.assertNotEquals(updatedEntity.getUpdated(), updatedEntity.getCreated());
	}

	@Test
	public void testDelete() {
		Order entity = new Order();
		entity.setServiceId(saveService().getId());
		entity.setUserId(saveUser().getId());
		entity.setRoomId(saveRoom().getId());
		entity.setTimeStay(1);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Order entity = new Order();
		entity.setServiceId(saveService().getId());
		entity.setUserId(saveUser().getId());
		entity.setRoomId(saveRoom().getId());
		entity.setTimeStay(1);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		Order selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getServiceId(), selectedEntity.getServiceId());
		Assertions.assertEquals(entity.getUserId(), selectedEntity.getUserId());
		Assertions.assertEquals(entity.getRoomId(), selectedEntity.getRoomId());
		Assertions.assertEquals(entity.getTimeStay(), selectedEntity.getTimeStay());
		Assertions.assertEquals(entity.getCreated(), selectedEntity.getCreated());
		Assertions.assertEquals(entity.getUpdated(), selectedEntity.getUpdated());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Order entity = new Order();
			entity.setServiceId(saveService().getId()); // generate some random meaningless name as it is just a test (the data can be unreal)
			entity.setServiceId(saveService().getId());
			entity.setRoomId(saveRoom().getId());
			entity.setTimeStay(1+i);
			entity.setCreated(getCurrentTime());
			entity.setUpdated(getCurrentTime());
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
	private User saveUser() {
		User entity = new User();
		entity.setName("VW");
		entity.setEmail("e");
		entity.setPassword("1111");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		userDao.insert(entity);
		return entity;
	}
	private Service saveService() {
		Service entity = new Service();
		entity.setType("clining");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		serviceDao.insert(entity);
		return entity;
	}
	private Room saveRoom() {
		Room entity = new Room();
		entity.setApartment("A");
		entity.setNumberBed(1);
		entity.setStatus(1);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		roomDao.insert(entity);
		return entity;
	}
}