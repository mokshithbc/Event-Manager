package model;
import java.sql.*;


public class UserDBA {
	public int validate(String username, String password) {
		 int userId = -1;
        try (
			Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM users WHERE username = ? AND password = ?");)
        {
        	preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("id");
            }
        } 
		catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }
	public boolean usernameExists(String username)
	{
		boolean exists = false;
		String sql = "SELECT 1 FROM users WHERE username = ?";

        try (Connection connection =DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    exists = true;
                }
                resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
	}
	public boolean accountExists(String email)
	{
		boolean exists = false;
		String sql = "SELECT 1 FROM users WHERE email = ?";

        try (Connection connection =DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    exists = true;
                }
                resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
	}
	
    public boolean createUser(String username, String password,String email) {
        boolean isCreated = false;

        String sql = "INSERT INTO users (username, password,email) VALUES (?, ?,?)";

        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                isCreated = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isCreated;
    }
   public void setVerify(String username)
   {
	   String sql = "UPDATE users"
	   		+ " SET verified = 'true' "
	   		+ " WHERE username = ?";
	   try (Connection connection = DataSource.getConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, username);
	            statement.executeUpdate();
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
   }
   public boolean isVerified(String username)
   {
	   return false;
   }
}
