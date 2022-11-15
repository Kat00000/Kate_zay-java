package by.grsu.zajceva.hotel.bd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.zajceva.hotel.bd.dao.AbstractDao;
import by.grsu.zajceva.hotel.bd.dao.IDao;
import by.grsu.zajceva.hotel.bd.model.Order;




public class OrderDaoImpl extends AbstractDao implements IDao<Integer, Order> {

	// single instance of this class to be used by the all consumers
	public static final OrderDaoImpl INSTANCE = new OrderDaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private OrderDaoImpl() {
		super();
	}

	@Override
	public void insert(Order entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into orderObject(service_id,user_id,room_id,time_stay, created, updated) values(?,?,?,?,?,?)");
			pstmt.setInt(1, entity.getServiceId());
			pstmt.setObject(2, entity.getUserId());
			pstmt.setInt(3, entity.getRoomId());
			pstmt.setInt(4, entity.getTimeStay());
			pstmt.setTimestamp(5, entity.getCreated());
			pstmt.setTimestamp(6, entity.getUpdated());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "orderObject"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Order entity", e);
		}
	}

	@Override
	public void update(Order entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update orderObject set service_id=?,user_id=?,room_id=?,time_stay=?, updated=? where id=?");
			pstmt.setInt(1, entity.getServiceId());
			pstmt.setObject(2, entity.getUserId());
			pstmt.setInt(3, entity.getRoomId());
			pstmt.setInt(4, entity.getTimeStay());
			pstmt.setTimestamp(5, entity.getUpdated());
			pstmt.setInt(6, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Order entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from orderObject where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Order entity", e);
		}

	}

	@Override
	public Order getById(Integer id) {
		Order entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from orderObject where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Order entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Order> getAll() {
		List<Order> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from orderObject");
			while (rs.next()) {
				Order entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Order entities", e);
		}

		return entitiesList;
	}

	private Order rowToEntity(ResultSet rs) throws SQLException {
		Order entity = new Order();
		entity.setId(rs.getInt("id"));
		entity.setServiceId(rs.getInt("service_id"));
		entity.setUserId(rs.getInt("user_id"));
		entity.setRoomId(rs.getInt("room_id"));
		entity.setTimeStay(rs.getInt("time_stay"));
		entity.setCreated(rs.getTimestamp("created"));
		entity.setUpdated(rs.getTimestamp("updated"));
		return entity;
	}

}