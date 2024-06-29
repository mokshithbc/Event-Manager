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
/**
 * Servlet implementation class VerifyEmailServlet
 */
@WebServlet("/VerifyEmailServlet")
public class VerifyEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String otp = String.valueOf( session.getAttribute("otp"));
        String username = (String) session.getAttribute("username");
	     String userOtp = request.getParameter("userOtp");
	     if(otp != null && otp.equals(userOtp))
	     {
	    	 UserDBA userDba = new UserDBA();
	    	 userDba.setVerify(username);
	    	 RequestDispatcher dispatcher =request.getRequestDispatcher("/view/login.jsp");
	    	 dispatcher.forward(request, response);
	     }
	     else
	     {
	    	 request.setAttribute("errorMessage", "Invalid otp");
	         RequestDispatcher dispatcher = request.getRequestDispatcher("/view/verifyEmail.jsp");
	         dispatcher.forward(request, response);
	     }
	}
	
}
