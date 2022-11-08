package by.grsu.zajceva.hotel.bd.dao.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.zajceva.hotel.bd.dao.IDao;
import by.grsu.zajceva.hotel.bd.model.Service;




public class ServiceDaoTest extends AbstractTest {
	private static final IDao<Integer, Service> dao = ServiceDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Service entity = new Service();
		entity.setType("clining");
		entity.setPrice(10);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Service entity = new Service();
		entity.setType("clining");
		entity.setPrice(10);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		String newType = "food";
		Float newPrice = (float) 20;
		entity.setType(newType);
		entity.setPrice(newPrice);
		entity.setUpdated(getCurrentTime());
		dao.update(entity);

		Service updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals( newType, updatedEntity.getType());
		Assertions.assertEquals( newPrice, updatedEntity.getPrice());
		Assertions.assertNotEquals(updatedEntity.getUpdated(), updatedEntity.getCreated());
	}

	@Test
	public void testDelete() {
		Service entity = new Service();
		entity.setType("A");
		entity.setPrice(10);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Service entity = new Service();
		entity.setType("clining");
		entity.setPrice(10);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		Service selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getType(), selectedEntity.getType());
		Assertions.assertEquals(entity.getPrice(), selectedEntity.getPrice());
		Assertions.assertEquals(entity.getCreated(), selectedEntity.getCreated());
		Assertions.assertEquals(entity.getUpdated(), selectedEntity.getUpdated());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Service entity = new Service();
			entity.setType("clining" + i); // generate some random meaningless name as it is just a test (the data can be unreal)
			entity.setPrice(10+i);
			entity.setCreated(getCurrentTime());
			entity.setUpdated(getCurrentTime());
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}