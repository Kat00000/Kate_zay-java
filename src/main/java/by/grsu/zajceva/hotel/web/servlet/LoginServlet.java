package by.grsu.zajceva.hotel.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.base.Strings;

import by.grsu.zajceva.hotel.web.security.AuthenticationFilter;
import by.grsu.zajceva.hotel.web.security.UserL;



public class LoginServlet extends HttpServlet {

	private static final List<UserL> USERS = new ArrayList<>();
	static {
		USERS.add(new UserL("admin", "password1", "Ivan", "Ivanov"));
		USERS.add(new UserL("manager", "password2", "Petr", "Petrov"));
		USERS.add(new UserL("blockeduser", "password3", "Alexander", "Alexandrod").setBlocked(true)); // no permissions to view the site
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");

		if (Strings.isNullOrEmpty(login) || Strings.isNullOrEmpty(password)) {
			res.sendError(401, "missing login or password");
			return;
		}

		Optional<UserL> userByLogin = USERS.stream().filter((user) -> {
			return login.equals(user.getEmail());
		}).findFirst();

		if (userByLogin.isEmpty()) {
			res.sendError(401, "login is incorrect");
			return;
		}

		UserL user = userByLogin.get();
		if (!password.equals(user.getPassword())) {
			res.sendError(401, "password is incorrect");
			return;
		}

		req.getSession(true).setAttribute(AuthenticationFilter.USERL_ATTR_NAME, user);
		System.out.println("login executed by user: " + login);
		res.sendRedirect("/");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.removeAttribute(AuthenticationFilter.USERL_ATTR_NAME);
			System.out.println("logout executed");
		}
		req.getRequestDispatcher("login.jsp").forward(req, res);
	}
}