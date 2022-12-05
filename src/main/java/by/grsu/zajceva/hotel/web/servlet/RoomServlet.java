package by.grsu.zajceva.hotel.web.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;

import by.grsu.zajceva.hotel.bd.dao.IDao;
import by.grsu.zajceva.hotel.bd.dao.impl.RoomDaoImpl;
import by.grsu.zajceva.hotel.bd.model.Order;
import by.grsu.zajceva.hotel.bd.model.Room;
import by.grsu.zajceva.hotel.web.ValidationUtils;
import by.grsu.zajceva.hotel.web.dto.RoomDto;
import by.grsu.zajceva.hotel.web.dto.TableStateDto;



public class RoomServlet extends AbstractListServlet {
	private static final IDao<Integer, Room> roomDao = RoomDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		/*(String paramId = req.getParameter("id");

		// validation
		if (!ValidationUtils.isInteger(paramId)) {
			res.sendError(400); // send HTTP status 400 and close response
			return;
		}

		Integer roomId = Integer.parseInt(paramId); // read request parameter
		Room roomById = roomDao.getById(roomId); // from DB
		*/
		System.out.println("doGet");
		String viewParam = req.getParameter("view");
		if ("edit".equals(viewParam)) {
			handleEditView(req, res);
		} else {
			handleListView(req, res);
		}
	}

	private void handleListView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int totalRooms = roomDao.count(); // get count of ALL items

		final TableStateDto tableStateDto = resolveTableStateDto(req, totalRooms); // init TableStateDto for specific
																					// Servlet and saves it in current
																					// request using key
																					// "currentPageTableState" to be
																					// used by 'paging' component

		List<Room> rooms = roomDao.find(tableStateDto); // get data using paging and sorting params
		//List<Room> orders = roomDao.getAll(); // get data

		List<RoomDto> dtos = rooms.stream().map((entity) -> {
			RoomDto dto = new RoomDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setApartment(entity.getApartment());
			dto.setNumber(entity.getNumber());
			dto.setNumberBed(entity.getNumberBed());
			dto.setPrice(entity.getPrice());
			dto.setStatus(entity.getStatus());
			dto.setCreated(entity.getCreated());
			dto.setUpdated(entity.getUpdated());

			
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("room.jsp").forward(req, res); // delegate request processing to JSP
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String roomIdStr = req.getParameter("id");
		RoomDto dto = new RoomDto();
		if (!Strings.isNullOrEmpty(roomIdStr)) {
			// object edit
			String paramId = req.getParameter("id");

			// validation
			if (!ValidationUtils.isInteger(paramId)) {
				res.sendError(400); // send HTTP status 400 and close response
				return;
			}

			Integer roomId = Integer.parseInt(paramId); // read request parameter
			Room roomById = roomDao.getById(roomId); // from DB
			 roomId = Integer.parseInt(roomIdStr);
			Room entity = roomDao.getById(roomId);
			dto.setId(entity.getId());
			dto.setApartment(entity.getApartment());
			dto.setNumber(entity.getNumber());
			dto.setNumberBed(entity.getNumberBed());
			dto.setPrice(entity.getPrice());
			dto.setStatus(entity.getStatus());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("room-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Room room = new Room();
		String roomIdStr = req.getParameter("id");
		

		room.setApartment(req.getParameter("apartment"));
		room.setNumber(req.getParameter("number"));
		room.setNumberBed(Integer.parseInt(req.getParameter("numberBed")));
		room.setPrice(Float.parseFloat(req.getParameter("price")));
		room.setStatus(Integer.parseInt(req.getParameter("status")));
		room.setUpdated(new Timestamp(new Date().getTime()));
		if (Strings.isNullOrEmpty(roomIdStr)) {
			// new entity
			room.setCreated(new Timestamp(new Date().getTime()));
			roomDao.insert(room);
		} else {
			// updated entity
			room.setId(Integer.parseInt(roomIdStr));
			roomDao.update(room);
		}
		res.sendRedirect("/room"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		roomDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}