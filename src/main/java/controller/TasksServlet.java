package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Task;
import model.TaskDBA;

/**
 * Servlet implementation class TasksServlet
 */
@WebServlet("/TasksServlet")
public class TasksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TaskDBA taskDBA = new TaskDBA();
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	System.out.println("task servlet called");
	        String userId = request.getParameter("userId");
	        String year = request.getParameter("year");
	        String month = request.getParameter("month");
	        String day = request.getParameter("day");
	        System.out.println(userId);
	        System.out.println(year);
	        System.out.println(year);
	        System.out.println(month);
	        System.out.println(day);
	        String date = year + "-" + month + "-" + day;
	        System.out.println(date);
	        List<Task> tasks = taskDBA.getTasks(userId, date);

	        response.setContentType("application/json");
	        PrintWriter out = response.getWriter();
	        out.print(tasksToJson(tasks));
	        out.flush();
	    }

	 private String tasksToJson(List<Task> tasks) {
		    StringBuilder json = new StringBuilder("[");
		    for (int i = 0; i < tasks.size(); i++) {
		        Task task = tasks.get(i);
		        json.append("{")
		            .append("\"timeRange\":\"").append(task.getTimeRange()).append("\",")
		            .append("\"title\":\"").append(task.getTitle()).append("\",")
		            .append("\"description\":\"").append(task.getDescription()).append("\",")
		            .append("\"id\":\"").append(task.getTaskId()).append("\"")  // Add the id field
		            .append("}");
		        if (i < tasks.size() - 1) {
		            json.append(",");
		        }
		    }
		    json.append("]");
		    return json.toString();
		}

}
