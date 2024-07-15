package com.jmv.jdbc.rca;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/signup.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String role = request.getParameter("role");

		System.out.println("Received data: username=" + username + ", email=" + email + ", password=" + password);

		if (RegistrationValidator.validateUsername(username) &&
				RegistrationValidator.validateEmail(email) &&
				RegistrationValidator.validatePassword(password)) {

			System.out.println("All validations passed!");

			User user = new User(username, email, password, role);

			UserDao userDao = new UserDao();
			userDao.insert(user);

			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} else {
			System.out.println("All validations failed!");
			response.sendRedirect("registration-failure.jsp");
		}
	}

}
