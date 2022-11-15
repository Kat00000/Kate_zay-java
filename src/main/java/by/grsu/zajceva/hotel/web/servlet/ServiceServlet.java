
package by.grsu.zajceva.hotel.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.grsu.zajceva.hotel.bd.dao.IDao;
import by.grsu.zajceva.hotel.bd.dao.impl.ServiceDaoImpl;
import by.grsu.zajceva.hotel.bd.model.Service;




public class ServiceServlet extends HttpServlet {
	private static final IDao<Integer, Service> serviceDao = ServiceDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer serviceId = Integer.parseInt(req.getParameter("id")); // read request parameter
		Service serviceById = serviceDao.getById(serviceId); // from DB

		res.setContentType("text/html");// setting the content type

		PrintWriter pw = res.getWriter();// get the stream to write the data

		// writing html in the stream
		pw.println("<html><body>");

		if (serviceById == null) {
			pw.println("no room by id=" + serviceById);
		} else {
			pw.println(serviceById.toString());
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
			String paramType = req.getParameter("type");
			Float paramPrice = Float.parseFloat(req.getParameter("price"));
			Long paramCreated = Long.parseLong(req.getParameter("created"));
			Long paramUpdated = Long.parseLong(req.getParameter("updated"));
			Service serviceEntity = new Service();
			serviceEntity.setType(paramType);
			serviceEntity.setPrice(paramPrice);
			serviceEntity.setCreated(new Timestamp(paramCreated));
			serviceEntity.setUpdated(new Timestamp(paramUpdated));
			serviceDao.insert(serviceEntity);
			pw.println("Saved:" + serviceEntity);

		} catch (Exception e) {
			pw.println("Error:" + e.toString());
		}

		pw.println("</body></html>");
		pw.close();
	}
}