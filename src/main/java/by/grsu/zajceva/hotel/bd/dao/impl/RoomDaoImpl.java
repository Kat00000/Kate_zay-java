package by.grsu.zajceva.hotel.bd.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.zajceva.hotel.bd.dao.AbstractDao;
import by.grsu.zajceva.hotel.bd.dao.IDao;
import by.grsu.zajceva.hotel.bd.model.Room;
import by.grsu.zajceva.hotel.web.dto.SortDto;
import by.grsu.zajceva.hotel.web.dto.TableStateDto;




public class RoomDaoImpl extends AbstractDao implements IDao<Integer, Room> {

	// single instance of this class to be used by the all consumers
	public static final RoomDaoImpl INSTANCE = new RoomDaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private RoomDaoImpl() {
		super();
	}

	@Override
	public void insert(Room entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into room(apartment,number,number_bed,price,status, created, updated) values(?,?,?,?,?,?,?)");
			pstmt.setString(1, entity.getApartment());
			pstmt.setString(2, entity.getNumber());
			pstmt.setInt(3, entity.getNumberBed());
			pstmt.setFloat(4,entity.getPrice());
			pstmt.setInt(5, entity.getStatus());
			pstmt.setTimestamp(6, entity.getCreated());
			pstmt.setTimestamp(7, entity.getUpdated());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "room"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Room entity", e);
		}
	}

	@Override
	public void update(Room entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update room set apartment=?,number=?,number_bed=?,price=?,status=?, updated=? where id=?");
			pstmt.setString(1, entity.getApartment());
			pstmt.setString(2, entity.getNumber());
			pstmt.setInt(3, entity.getNumberBed());
			pstmt.setFloat(4,entity.getPrice());
			pstmt.setInt(5, entity.getStatus());
			pstmt.setTimestamp(6, entity.getUpdated());
			pstmt.setInt(7, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Room entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from room where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Room entity", e);
		}

	}

	@Override
	public Room getById(Integer id) {
		Room entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from room where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Room entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Room> getAll() {
		List<Room> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from room");
			while (rs.next()) {
				Room entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Room entities", e);
		}

		return entitiesList;
	}

	private Room rowToEntity(ResultSet rs) throws SQLException {
		Room entity = new Room();
		entity.setId(rs.getInt("id"));
		entity.setApartment(rs.getString("apartment"));
		entity.setNumber(rs.getString("number"));
		entity.setNumberBed(rs.getInt("number_bed"));
		entity.setPrice(rs.getFloat("price"));
		entity.setStatus(rs.getInt("status"));
		entity.setCreated(rs.getTimestamp("created"));
		entity.setUpdated(rs.getTimestamp("updated"));
		return entity;
	}
	@Override
	public List<Room> find(TableStateDto tableStateDto) {
		List<Room> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			StringBuilder sql = new StringBuilder("select * from room");

			final SortDto sortDto = tableStateDto.getSort();
			if (sortDto != null) {
				sql.append(String.format(" order by %s %s", sortDto.getColumn(), resolveSortOrder(sortDto)));
			}

			sql.append(" limit " + tableStateDto.getItemsPerPage());
			sql.append(" offset " + resolveOffset(tableStateDto));

			System.out.println("searching Rooms using SQL: " + sql);
			ResultSet rs = c.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Room entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Room entities", e);
		}
		return entitiesList;
	}

	@Override
	public int count() {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select count(*) as c from room");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("c");
		} catch (SQLException e) {
			throw new RuntimeException("can't get rooms count", e);
		}
	}
}