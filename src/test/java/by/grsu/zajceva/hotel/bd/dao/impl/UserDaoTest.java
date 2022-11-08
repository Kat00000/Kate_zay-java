package by.grsu.zajceva.hotel.bd.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.zajceva.hotel.bd.dao.IDao;
import by.grsu.zajceva.hotel.bd.model.User;



public class UserDaoTest extends AbstractTest {
	private static final IDao<Integer, User> dao = UserDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		User entity = new User();
		entity.setName("VW");
		entity.setEmail("e");
		entity.setPassword("1111");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		User entity = new User();
		entity.setName("VW");
		entity.setEmail("e");
		entity.setPassword("1111");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		String newName = "VW_NEW";
		String newEmail = "e_new";
		String newPassword = "2222";
		entity.setName(newName);
		entity.setEmail(newEmail);
		entity.setPassword(newPassword);
		entity.setUpdated(getCurrentTime());
		dao.update(entity);

		User updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals( newName, updatedEntity.getName());
		Assertions.assertEquals( newEmail, updatedEntity.getEmail());
		Assertions.assertEquals( newPassword, updatedEntity.getPassword());
		Assertions.assertNotEquals(updatedEntity.getUpdated(), updatedEntity.getCreated());
	}

	@Test
	public void testDelete() {
		User entity = new User();
		entity.setName("VW");
		entity.setEmail("e");
		entity.setPassword("1111");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		User entity = new User();
		entity.setName("VW");
		entity.setEmail("e");
		entity.setPassword("1111");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		User selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
		Assertions.assertEquals(entity.getEmail(), selectedEntity.getEmail());
		Assertions.assertEquals(entity.getPassword(), selectedEntity.getPassword());
		Assertions.assertEquals(entity.getCreated(), selectedEntity.getCreated());
		Assertions.assertEquals(entity.getUpdated(), selectedEntity.getUpdated());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			User entity = new User();
			entity.setName("VW" + i); // generate some random meaningless name as it is just a test (the data can be unreal)
			entity.setEmail("e"+i);
			entity.setPassword("1111"+i);
			entity.setCreated(getCurrentTime());
			entity.setUpdated(getCurrentTime());
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}