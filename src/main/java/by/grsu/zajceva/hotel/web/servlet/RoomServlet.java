package by.grsu.zajceva.hotel.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.grsu.zajceva.hotel.bd.dao.IDao;
import by.grsu.zajceva.hotel.bd.dao.impl.RoomDaoImpl;
import by.grsu.zajceva.hotel.bd.model.Room;



public class RoomServlet extends HttpServlet {
	private static final IDao<Integer, Room> roomDao = RoomDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer roomId = Integer.parseInt(req.getParameter("id")); // read request parameter
		Room roomById = roomDao.getById(roomId); // from DB

		res.setContentType("text/html");// setting the content type

		PrintWriter pw = res.getWriter();// get the stream to write the data

		// writing html in the stream
		pw.println("<html><body>");

		if (roomById == null) {
			pw.println("no room by id=" + roomById);
		} else {
			pw.println(roomById.toString());
		}

		pw.println("</body></html>");
		pw.close();// closing the stream
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();// get the stream to write the data
		pw.println("<html><body>");
		try {
			String paramName = req.getParameter("name");
			Integer paramNumber = Integer.parseInt(req.getParameter("number"));
			Float paramPrice = Float.parseFloat(req.getParameter("price"));
			Integer paramStatus = Integer.parseInt(req.getParameter("status"));
			Long paramCreated = Long.parseLong(req.getParameter("created"));
			Long paramUpdated = Long.parseLong(req.getParameter("updated"));
			Room roomEntity = new Room();
			roomEntity.setApartment(paramName);
			roomEntity.setNumberBed(paramNumber);
			roomEntity.setPrice(paramPrice);
			roomEntity.setStatus(paramStatus);
			roomEntity.setCreated(new Timestamp(paramCreated));
			roomEntity.setUpdated(new Timestamp(paramUpdated));
			roomDao.insert(roomEntity);
			pw.println("Saved:" + roomEntity);

		} catch (Exception e) {
			pw.println("Error:" + e.toString());
		}

		pw.println("</body></html>");
		pw.close();
	}
}