package by.grsu.zajceva.hotel.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.grsu.zajceva.hotel.bd.dao.IDao;
import by.grsu.zajceva.hotel.bd.dao.impl.UserDaoImpl;
import by.grsu.zajceva.hotel.bd.model.User;



public class UserServlet extends HttpServlet {
	private static final IDao<Integer, User> roomDao = UserDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer userId = Integer.parseInt(req.getParameter("id")); // read request parameter
		User roomById = roomDao.getById(userId); // from DB

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
			String paramEmail = req.getParameter("email");
			String paramPassword = req.getParameter("password");
			Long paramCreated = Long.parseLong(req.getParameter("created"));
			Long paramUpdated = Long.parseLong(req.getParameter("updated"));
			User userEntity = new User();
			userEntity.setName(paramName);
			userEntity.setEmail(paramEmail);
			userEntity.setPassword(paramPassword);
			userEntity.setCreated(new Timestamp(paramCreated));
			userEntity.setUpdated(new Timestamp(paramUpdated));
			roomDao.insert(userEntity);
			pw.println("Saved:" + userEntity);

		} catch (Exception e) {
			pw.println("Error:" + e.toString());
		}

		pw.println("</body></html>");
		pw.close();
	}
}