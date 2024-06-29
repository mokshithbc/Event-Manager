package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ResendEmailServlet
 */
@WebServlet("/ResendEmailServlet")
public class ResendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer	otp =(Integer) session.getAttribute("otp");
        System.out.println(otp);
        SendMail.sendMail("mokshithbc123@gmail.com",otp);
        session.setAttribute("emailResent", true);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/verifyEmail.jsp");
        dispatcher.forward(request, response);
	}

}
