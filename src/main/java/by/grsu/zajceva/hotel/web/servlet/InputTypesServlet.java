package by.grsu.zajceva.hotel.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;

public class InputTypesServlet extends HttpServlet {
	private DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	private DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();// get the stream to write the data
		pw.println("<html><body>");
		try {
			if (!Strings.isNullOrEmpty(req.getParameter("date"))) {
				Date sqlDate = new Date(DATE_FORMAT.parse(req.getParameter("date")).getTime());
				pw.println("sqlDate: " + sqlDate.toString() + "<br/>");
			}

			if (!Strings.isNullOrEmpty(req.getParameter("datetime-local"))) {
				Timestamp sqlTimestamp = Timestamp
						.valueOf(LocalDateTime.parse(req.getParameter("datetime-local"), TIMESTAMP_FORMAT));
				pw.println("sqlTimestamp: " + sqlTimestamp.toString() + "<br/>");
			}

			if (!Strings.isNullOrEmpty(req.getParameter("email"))) {
				pw.println("email: " + req.getParameter("email") + "<br/>");
			}
			if (!Strings.isNullOrEmpty(req.getParameter("password"))) {
				pw.println("password: " + req.getParameter("password") + "<br/>");
			}
			if (!Strings.isNullOrEmpty(req.getParameter("tel"))) {
				pw.println("tel: " + req.getParameter("tel") + "<br/>");
			}

			if (!Strings.isNullOrEmpty(req.getParameter("number"))) {
				pw.println("number: " + Integer.parseInt(req.getParameter("number")) + "<br/>");
			}

		} catch (ParseException e) {
			pw.println("Error:" + e.toString());
		}

		pw.println("</body></html>");
		pw.close();
	}
}