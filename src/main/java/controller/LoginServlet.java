package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserDBA;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDBA userDBA = new UserDBA();
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String username = request.getParameter("txtName");
			String password = request.getParameter("txtPwd");
			int userId=userDBA.validate(username, password);
			if(userId != -1)
			{
				HttpSession session = request.getSession();
	            session.setAttribute("userId", userId);
				RequestDispatcher dispatcher =request.getRequestDispatcher("/view/welcome.jsp");
				dispatcher.forward(request, response);
			}
			else
			{
				request.setAttribute("errorMessage", "Invalid Username/Password");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/login.jsp");
	            dispatcher.forward(request, response);
			}
	}

}
