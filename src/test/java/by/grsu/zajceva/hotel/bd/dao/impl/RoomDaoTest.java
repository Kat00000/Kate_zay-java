package by.grsu.zajceva.hotel.bd.dao.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.zajceva.hotel.bd.dao.IDao;
import by.grsu.zajceva.hotel.bd.model.Room;



public class RoomDaoTest extends AbstractTest {
	private static final IDao<Integer, Room> dao = RoomDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Room entity = new Room();
		entity.setApartment("A");
		entity.setNumberBeds(1);
		entity.setPrice(10);
		entity.setStatus(1);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Room entity = new Room();
		entity.setApartment("A");
		entity.setNumberBeds(1);
		entity.setPrice(10);
		entity.setStatus(1);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		String newApartment = "B";
		Integer newNumberBeds = 2;
		Float newPrice = (float)20;
		Integer newStatus = 0;
		entity.setApartment(newApartment);
		entity.setNumberBeds(newNumberBeds);
		entity.setPrice(newPrice);
		entity.setStatus(newStatus);
		entity.setUpdated(getCurrentTime());
		dao.update(entity);

		Room updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals( newApartment, updatedEntity.getApartment());
		Assertions.assertEquals( newNumberBeds, updatedEntity.getNumberBeds());
		Assertions.assertEquals( newPrice, updatedEntity.getPrice());
		Assertions.assertEquals( newStatus, updatedEntity.getStatus());
		Assertions.assertNotEquals(updatedEntity.getUpdated(), updatedEntity.getCreated());
	}

	@Test
	public void testDelete() {
		Room entity = new Room();
		entity.setApartment("A");
		entity.setNumberBeds(1);
		entity.setPrice(10);
		entity.setStatus(1);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Room entity = new Room();
		entity.setApartment("A");
		entity.setNumberBeds(1);
		entity.setPrice(10);
		entity.setStatus(1);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		Room selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getApartment(), selectedEntity.getApartment());
		Assertions.assertEquals(entity.getNumberBeds(), selectedEntity.getNumberBeds());
		Assertions.assertEquals(entity.getPrice(), selectedEntity.getPrice());
		Assertions.assertEquals(entity.getStatus(), selectedEntity.getStatus());
		Assertions.assertEquals(entity.getCreated(), selectedEntity.getCreated());
		Assertions.assertEquals(entity.getUpdated(), selectedEntity.getUpdated());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Room entity = new Room();
			entity.setApartment("A" + i); // generate some random meaningless name as it is just a test (the data can be unreal)
			entity.setNumberBeds(1+i);
			entity.setPrice(10+i);
			entity.setStatus(0+i);
			entity.setCreated(getCurrentTime());
			entity.setUpdated(getCurrentTime());
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}