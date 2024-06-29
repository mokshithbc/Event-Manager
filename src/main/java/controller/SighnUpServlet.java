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
 * Servlet implementation class SighnUpServlet
 */
@WebServlet("/SighnUpServlet")
public class SighnUpServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L; 
	private UserDBA userDBA = new UserDBA();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("txtName");
        String password = request.getParameter("txtPwd");
        String email = request.getParameter("txtMail");
        String confirmPassword = request.getParameter("txtConfirmPwd");
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/sighnUp.jsp");
            dispatcher.forward(request, response);
            return;
        }
        if(userDBA.usernameExists(username))
        {
	        if (userDBA.accountExists(email)) {
	            request.setAttribute("accountExist", "Account already exists!!");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/sighnUp.jsp");
	            dispatcher.forward(request, response);
	            return;
	        }
	        request.setAttribute("errorMessage", "Username taken!!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/sighnUp.jsp");
            dispatcher.forward(request, response);
            return;
        }
        if (userDBA.createUser(username, password,email)) {
        	int otp = SendMail.getOTP();
        	SendMail.sendMail("mokshithbc123@gmail.com",otp);
        	
        	request.setAttribute("username", username);
        	
        	HttpSession session = request.getSession();
        	session.setAttribute("otp", otp);
        	session.setAttribute("username", username);
        	session.setAttribute("email", email);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/verifyEmail.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Account Creation Failed!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/sighnUp.jsp");
            dispatcher.forward(request, response);
        }
	}
	
	
}
