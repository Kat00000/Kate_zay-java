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
import by.grsu.zajceva.hotel.bd.dao.impl.OrderDaoImpl;
import by.grsu.zajceva.hotel.bd.dao.impl.RoomDaoImpl;
import by.grsu.zajceva.hotel.bd.dao.impl.ServiceDaoImpl;
import by.grsu.zajceva.hotel.bd.dao.impl.UserDaoImpl;
import by.grsu.zajceva.hotel.bd.model.Order;
import by.grsu.zajceva.hotel.bd.model.Room;
import by.grsu.zajceva.hotel.bd.model.Service;
import by.grsu.zajceva.hotel.bd.model.User;
import by.grsu.zajceva.hotel.web.dto.OrderDto;



public class OrderServlet extends HttpServlet {
	private static final IDao<Integer, Order> orderDao = OrderDaoImpl.INSTANCE;
	private static final IDao<Integer, Room> roomDao = RoomDaoImpl.INSTANCE;
	private static final IDao<Integer, User> userDao = UserDaoImpl.INSTANCE;
	private static final IDao<Integer, Service> serviceDao = ServiceDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doGet");
		String viewParam = req.getParameter("view");
		if ("edit".equals(viewParam)) {
			handleEditView(req, res);
		} else {
			handleListView(req, res);
		}
	}

	private void handleListView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Order> order = orderDao.getAll(); // get data

		List<OrderDto> dtos = order.stream().map((entity) -> {
			OrderDto dto = new OrderDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setTimeStay(entity.getTimeStay());
			dto.setCreated(entity.getCreated());
			dto.setUpdated(entity.getUpdated());

			// build data for complex fields
			Service service = serviceDao.getById(entity.getServiceId());
			dto.setServiceName(service.getType());

			User user = userDao.getById(entity.getUserId());
			dto.setUserName(user.getName());
			
			Room room = roomDao.getById(entity.getRoomId());
			dto.setRoomName(room.getApartment());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("index-list.jsp").forward(req, res); // delegate request processing to JSP
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String orderIdStr = req.getParameter("id");
		OrderDto dto = new OrderDto();
		if (!Strings.isNullOrEmpty(orderIdStr)) {
			// object edit
			Integer orderId = Integer.parseInt(orderIdStr);
			Order entity = orderDao.getById(orderId);
			dto.setId(entity.getId());
			dto.setTimeStay(entity.getTimeStay());
			dto.setRoomId(entity.getRoomId());
			dto.setServiceId(entity.getServiceId());
			dto.setUserId(entity.getUserId());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Order order = new Order();
		String orderIdStr = req.getParameter("id");
		String roomIdStr = req.getParameter("roomlId");
		String userIdStr = req.getParameter("userId");
		String serviceIdStr = req.getParameter("serviceId");

		order.setTimeStay(Integer.parseInt(req.getParameter("timeStay")));
		order.setRoomId(roomIdStr == null ? null : Integer.parseInt(roomIdStr));
		order.setServiceId(serviceIdStr == null ? null : Integer.parseInt(serviceIdStr));
		order.setUserId(userIdStr == null ? null : Integer.parseInt(userIdStr));
		order.setUpdated(new Timestamp(new Date().getTime()));
		if (Strings.isNullOrEmpty(orderIdStr)) {
			// new entity
			order.setCreated(new Timestamp(new Date().getTime()));
			orderDao.insert(order);
		} else {
			// updated entity
			order.setId(Integer.parseInt(orderIdStr));
			orderDao.update(order);
		}
		res.sendRedirect("/order"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		orderDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}