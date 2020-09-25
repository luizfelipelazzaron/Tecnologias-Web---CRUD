package br.edu.insper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private Connection connection = null;
	
	public DAO() throws SQLException, ClassNotFoundException {//little
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tecweb","root","123654516");
	}
	
	public User login(String name, String password) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE name = ? AND password = ?;");
		
		ps.setString(1, name);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			User user = new User(); //create user class
			user.setName(rs.getString("name")); // set name 
			user.setPassword(rs.getString("password")); // set password
			user.setId(rs.getInt("id")); // set id
			return user; //return the user created
		}
		return null;// if the login isn't register, the class returned is null
		
	}
	
	public void register(String name, String password) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT user (name,password) values (?,?);");
		ps.setString(1, name);
		ps.setString(2, password);
		ps.execute();
		ps.close();
	}
	
	public User getUser(int userId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE id = ?;");
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			User user = new User(); //create user class
			user.setName(rs.getString("name")); // set name 
			user.setPassword(rs.getString("password")); // set password
			user.setId(rs.getInt("id")); // set id
			return user; //return the user created
		}
		return null;
	}
	
	public List<Task> getList(int userId) throws SQLException{
		List<Task> tasks = new ArrayList<Task>();
		PreparedStatement stmt;
		stmt = connection.prepareStatement("SELECT * FROM task WHERE userId = ?;");
		stmt.setInt(1,userId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			//Class
			Task task = new Task();
			//Attributes
			task.setId(rs.getInt("id"));//id
			task.setUserId(rs.getInt("userId"));//userId
			task.setTitle(rs.getNString("title")); //title
			task.setType(rs.getNString("type"));//type 
			task.setDate(rs.getTimestamp("date"));//date
			task.setDeadline(rs.getTimestamp("deadline"));//deadline
			//Add
			tasks.add(task);
		}
		rs.close();
		stmt.close();
		return tasks;
	}
	
	public List<Task> filterTasks(int userId, String type) throws SQLException{
		List<Task> filteredTasks = new ArrayList<Task>();
		PreparedStatement stmt;
		stmt = connection.prepareStatement("SELECT * FROM task WHERE userId = ? and type = ?;");
		stmt.setInt(1,userId);
		stmt.setString(2, type);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			//Class
			Task task = new Task();
			//Attributes
			task.setId(rs.getInt("id"));//id
			task.setUserId(rs.getInt("userId"));//userId
			task.setTitle(rs.getNString("title")); //title
			task.setType(rs.getNString("type"));//type 
			task.setDate(rs.getTimestamp("date"));//date
			task.setDeadline(rs.getTimestamp("deadline"));//deadline
			filteredTasks.add(task);//finally, I must add in filteredTasks array 
		}
		rs.close();
		stmt.close();
		return filteredTasks;
		
	}
	
	public List<Task> searchTasks(int userId, String title) throws SQLException{
		List<Task> searchedTasks = new ArrayList<Task>();
		PreparedStatement stmt;
		stmt = connection.prepareStatement("SELECT * FROM task WHERE userId = ? and title = ?;");
		stmt.setInt(1,userId);
		stmt.setString(2, title);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			//Class
			Task task = new Task();
			//Attributes
			task.setId(rs.getInt("id"));//id
			task.setUserId(rs.getInt("userId"));//userId
			task.setTitle(rs.getNString("title")); //title
			task.setType(rs.getNString("type"));//type 
			task.setDate(rs.getTimestamp("date"));//date
			task.setDeadline(rs.getTimestamp("deadline"));//deadline
			searchedTasks.add(task);//finally, I must add in filteredTasks array 
		}
		rs.close();
		stmt.close();
		return searchedTasks;
	}
	
	public List<Task> orderTasks(int userId) throws SQLException{
		List<Task> orderedTasks = new ArrayList<Task>();
		PreparedStatement stmt;
		stmt = connection.prepareStatement("SELECT * FROM task WHERE userId = ? ORDER BY title ASC;");
		stmt.setInt(1, userId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			//Class
			Task task = new Task();
			//Attributes
			task.setId(rs.getInt("id"));//id
			task.setUserId(rs.getInt("userId"));//userId
			task.setTitle(rs.getNString("title")); //title
			task.setType(rs.getNString("type"));//type 
			task.setDate(rs.getTimestamp("date"));//date
			task.setDeadline(rs.getTimestamp("deadline"));//deadline
			orderedTasks.add(task);//finally, I must add in filteredTasks array 
		}
		rs.close();
		stmt.close();
		return orderedTasks;
	}
	
	public void create(int userId, String title, String type, Timestamp deadline) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO task (userId,title,type,deadline) VALUES (?,?,?,?);");
		ps.setInt(1, userId);
		ps.setString(2, title);
		ps.setString(3, type);
		ps.setTimestamp(4, deadline);
		ps.execute();
		ps.close();
	}
	
	public Task getTask(String id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM task WHERE id = ?;");
		ps.setInt(1,Integer.parseInt(id));
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Task task = new Task();
			task.setId(rs.getInt("id"));//id
			task.setUserId(rs.getInt("userId"));//userId
			task.setTitle(rs.getNString("title")); //title
			task.setType(rs.getNString("type"));//type 
			task.setDate(rs.getTimestamp("date"));//date
			task.setDeadline(rs.getTimestamp("deadline"));//deadline
			return task;
		}
		return null;
		
	}
	
	public void editTask(int id, String title, String type, Timestamp deadline) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE task SET title = ? ,type = ?, deadline = ? WHERE id = ?;");
		ps.setString(1,title);
		ps.setString(2,type);
		ps.setTimestamp(3, deadline);
		ps.setInt(4, id);
		ps.execute();
		ps.close();
		
	}
	
	public void deleteTask(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("DELETE FROM task WHERE id = ?;");
		ps.setInt(1, id);
		ps.execute();
		ps.close();
	}
	
	public void close() throws SQLException {
		connection.close();
	}
	
}


