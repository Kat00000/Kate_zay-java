package by.grsu.zajceva.hotel.bd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.zajceva.hotel.bd.dao.AbstractDao;
import by.grsu.zajceva.hotel.bd.dao.IDao;
import by.grsu.zajceva.hotel.bd.model.Service;




public class ServiceDaoImpl extends AbstractDao implements IDao<Integer, Service> {

	// single instance of this class to be used by the all consumers
	public static final ServiceDaoImpl INSTANCE = new ServiceDaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private ServiceDaoImpl() {
		super();
	}

	@Override
	public void insert(Service entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into service(type,price, created, updated) values(?,?,?,?)");
			pstmt.setString(1, entity.getType());
			pstmt.setFloat(2,entity.getPrice());
			pstmt.setTimestamp(3, entity.getCreated());
			pstmt.setTimestamp(4, entity.getUpdated());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "service"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Service entity", e);
		}
	}

	@Override
	public void update(Service entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update service set type=?,price=?, updated=? where id=?");
			pstmt.setString(1, entity.getType());
			pstmt.setFloat(2,entity.getPrice());
			pstmt.setTimestamp(3, entity.getUpdated());
			pstmt.setInt(4, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Service entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from service where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Service entity", e);
		}

	}

	@Override
	public Service getById(Integer id) {
		Service entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from service where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Service entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Service> getAll() {
		List<Service> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from service");
			while (rs.next()) {
				Service entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Service entities", e);
		}

		return entitiesList;
	}

	private Service rowToEntity(ResultSet rs) throws SQLException {
		Service entity = new Service();
		entity.setId(rs.getInt("id"));
		entity.setType(rs.getString("type"));
		entity.setPrice(rs.getFloat("price"));
		entity.setCreated(rs.getTimestamp("created"));
		entity.setUpdated(rs.getTimestamp("updated"));
		return entity;
	}

}