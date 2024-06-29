package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TaskDBA;

/**
 * Servlet implementation class DeleteTaskServlet
 */
@WebServlet("/DeleteTaskServlet")
public class DeleteTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String taskId = request.getParameter("taskId");
		System.out.println("deleteservlet called:"+taskId);
		TaskDBA taskdba = new TaskDBA();
		boolean isDeleted = taskdba.deleteTask(taskId);

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(String.valueOf(isDeleted));
	}
}
