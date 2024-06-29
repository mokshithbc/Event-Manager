package controller;

import model.TaskDBA;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 System.out.println("Add task servlet called");

	        String userId = request.getParameter("userId");
	        String date = request.getParameter("date");
	        String timeRange = request.getParameter("timeRange");
	        String title = request.getParameter("title");
	        String description = request.getParameter("description");

	        System.out.println(timeRange);
	        System.out.println(title);
	        System.out.println(description);
	        System.out.println(date);
	        System.out.println(userId);

	        TaskDBA taskDBA = new TaskDBA();
	        boolean success = taskDBA.addTask(userId, date, timeRange, title, description);

	        response.setContentType("text/plain");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(String.valueOf(success));
    }
}
