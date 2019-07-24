package com.wisdom.demo.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wisdom.demo.manager.LoginManager;
import com.wisdom.logindemo.pojo.Login;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirectpath = "";
		boolean success = false;
		try {
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			System.out.println("--User Name----->" + userName);
			System.out.println("--Password----->" + password);

			if (userName != null && !userName.isEmpty() && password != null && !password.isEmpty()) {

				LoginManager manager = new LoginManager();
				Login login = new Login();
				login.setUserName(userName.trim());//
				login.setPassword(password.trim());//

				success = manager.doAuth(login);
				System.out.println("---Success---->" + success);
			}

			if (success) {///
				request.setAttribute("success", userName);
				redirectpath = "/jsp/success.jsp";
			} else {
				redirectpath = "/jsp/fail.jsp";
			}

		} catch (Exception e) {
			redirectpath = "/jsp/fail.jsp";
		}

		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(redirectpath);
		requestDispatcher.forward(request, response);

	}
}
