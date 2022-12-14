
package by.grsu.zajceva.hotel.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import by.grsu.zajceva.hotel.bd.dao.impl.ServiceDaoImpl;
import by.grsu.zajceva.hotel.bd.model.Order;
import by.grsu.zajceva.hotel.bd.model.Room;
import by.grsu.zajceva.hotel.bd.model.Service;
import by.grsu.zajceva.hotel.web.ValidationUtils;
import by.grsu.zajceva.hotel.web.dto.ServiceDto;
import by.grsu.zajceva.hotel.web.dto.TableStateDto;





public class ServiceServlet extends AbstractListServlet {
	private static final IDao<Integer, Service> serviceDao = ServiceDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		/*String paramId = req.getParameter("id");

		// validation
		if (!ValidationUtils.isInteger(paramId)) {
			res.sendError(400); // send HTTP status 400 and close response
			return;
		}

		Integer serviceId = Integer.parseInt(paramId); // read request parameter
		Service ServiceById = serviceDao.getById(serviceId); // from DB
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
		int totalServices = serviceDao.count(); // get count of ALL items

		final TableStateDto tableStateDto = resolveTableStateDto(req, totalServices); // init TableStateDto for specific
																					// Servlet and saves it in current
																					// request using key
																					// "currentPageTableState" to be
																					// used by 'paging' component

		List<Service> service = serviceDao.find(tableStateDto); // get data using paging and sorting params
		//List<Service> service = serviceDao.getAll(); // get data

		List<ServiceDto> dtos = service.stream().map((entity) -> {
			ServiceDto dto = new ServiceDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setType(entity.getType());
			dto.setPrice(entity.getPrice());
			dto.setCreated(entity.getCreated());
			dto.setUpdated(entity.getUpdated());

			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("service.jsp").forward(req, res); // delegate request processing to JSP
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String serviceIdStr = req.getParameter("id");
		ServiceDto dto = new ServiceDto();
		if (!Strings.isNullOrEmpty(serviceIdStr)) {
			// object edit
			String paramId = req.getParameter("id");

			// validation
			if (!ValidationUtils.isInteger(paramId)) {
				res.sendError(400); // send HTTP status 400 and close response
				return;
			}

			Integer serviceId = Integer.parseInt(paramId); // read request parameter
			Service ServiceById = serviceDao.getById(serviceId); // from DB
			 serviceId = Integer.parseInt(serviceIdStr);
			Service entity = serviceDao.getById(serviceId);
			dto.setId(entity.getId());
			dto.setType(entity.getType());
			dto.setPrice(entity.getPrice());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("service-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Service service = new Service();
		String serviceIdStr = req.getParameter("id");
		
		service.setType(req.getParameter("type"));
		service.setPrice(Float.parseFloat(req.getParameter("price")));
		service.setUpdated(new Timestamp(new Date().getTime()));
		if (Strings.isNullOrEmpty(serviceIdStr)) {
			// new entity
			service.setCreated(new Timestamp(new Date().getTime()));
			serviceDao.insert(service);
		} else {
			// updated entity
			service.setId(Integer.parseInt(serviceIdStr));
			serviceDao.update(service);
		}
		res.sendRedirect("/service"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		serviceDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}