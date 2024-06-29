package model;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class TaskDBA {
	public List<Task> getTasks(String userId, String date) {
	    List<Task> tasks = new ArrayList<>();
	    String sql = "SELECT t.id, t.time_range, t.title, t.description FROM tasks t WHERE t.user_id = ? AND t.task_date = ?";

	    try (
	        Connection connection = DataSource.getConnection();
	        PreparedStatement statement = connection.prepareStatement(sql);
	    ) {
	        statement.setString(1, userId);
	        statement.setDate(2, java.sql.Date.valueOf(date));
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Task task = new Task();
	            System.out.println(resultSet.getString("title"));
	            String taskId =resultSet.getInt("id")+"";
	            task.setTaskId(taskId);
	            task.setTimeRange(resultSet.getString("time_range"));
	            task.setTitle(resultSet.getString("title"));
	            task.setDescription(resultSet.getString("description"));
	            tasks.add(task);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return tasks;
	}
    public boolean addTask(String userId, String date, String timeRange, String title, String description) {
    	   String checkDateSQL = "SELECT 1 FROM user_dates WHERE user_id = ? AND task_date = ?";
    	    String insertDateSQL = "INSERT INTO user_dates (user_id, task_date) VALUES (?, ?)";
    	    String insertTaskSQL = "INSERT INTO tasks (user_id, task_date, time_range, title, description) VALUES (?, ?, ?, ?, ?)";
    	    
    	    try (
    	        Connection connection = DataSource.getConnection();
    	        PreparedStatement checkDateStmt = connection.prepareStatement(checkDateSQL);
    	        PreparedStatement insertDateStmt = connection.prepareStatement(insertDateSQL);
    	        PreparedStatement insertTaskStmt = connection.prepareStatement(insertTaskSQL);
    	    ) {
    	    	  connection.setAutoCommit(false);// Start transaction

    	        // Check if the date exists
    	        checkDateStmt.setString(1, userId);
    	        checkDateStmt.setDate(2, java.sql.Date.valueOf(date));
    	        ResultSet rs = checkDateStmt.executeQuery();
    	        
    	        if (!rs.next()) {
    	            // Date does not exist, insert into user_dates
    	            insertDateStmt.setString(1, userId);
    	            insertDateStmt.setDate(2, java.sql.Date.valueOf(date));
    	            insertDateStmt.executeUpdate();
    	        }

    	        // Insert task
    	        insertTaskStmt.setString(1, userId);
    	        insertTaskStmt.setDate(2, java.sql.Date.valueOf(date));
    	        insertTaskStmt.setString(3, timeRange);
    	        insertTaskStmt.setString(4, title);
    	        insertTaskStmt.setString(5, description);

    	        int rowsAffectedTasks = insertTaskStmt.executeUpdate();
    	        
    	        connection.commit();// Commit transaction
    	        return rowsAffectedTasks > 0;

    	    } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteTask(String taskId) {
        String deleteSql = "DELETE FROM tasks WHERE id = ?";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement deleteTask = connection.prepareStatement(deleteSql);
        ) {
        	System.out.println(taskId);
            deleteTask.setString(1, taskId);
            int rowAffected = deleteTask.executeUpdate();
            return rowAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}