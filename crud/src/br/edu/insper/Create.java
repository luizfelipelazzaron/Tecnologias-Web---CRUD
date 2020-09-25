package br.edu.insper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Create
 */
@WebServlet("/Create")
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Create() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String id = request.getParameter("id");
		request.setAttribute("userId", id);
		RequestDispatcher rd = request.getRequestDispatcher("/create/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dao;
		try {
			dao = new DAO();
			int userId = Integer.parseInt(request.getParameter("userId"));
			String taskTitle = request.getParameter("taskTitle");
			String taskType = request.getParameter("taskType");
			//Take deadline
			String taskDeadline = request.getParameter("taskDeadline");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //you are not able to put "hh:mm:ss.SSS" yet. If you do it, you will run into problems. To understand it you can look up in https://www.guj.com.br/t/java-text-parseexception-unparseable-date/58269/2
			Date parsedDate = dateFormat.parse(taskDeadline);
			Timestamp timestamp = new Timestamp(parsedDate.getTime());
			dao.create(userId, taskTitle, taskType, timestamp);
			
			User user = dao.getUser(userId);
			
			if (user == null) {
				request.setAttribute("information", "Hey, you're running into problems friend. Please, can you check whether Title, Type and Deadline was wrote corretly?");
				RequestDispatcher rd = request.getRequestDispatcher("information.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("name", user);
				request.setAttribute("id", user.getId());
				List<Task> tasks = dao.getList(user.getId());
				request.setAttribute("tasks", tasks);
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.setAttribute("information", "Hey, you're running into problems friend. Please, can you check whether Title, Type and Deadline was wrote corretly?");
			RequestDispatcher rd = request.getRequestDispatcher("information.jsp");
			rd.forward(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			request.setAttribute("information", "Hey, you're running into problems friend. Please, can you check whether Title, Type and Deadline was wrote corretly?");
			RequestDispatcher rd = request.getRequestDispatcher("information.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			request.setAttribute("information", "Hey, you're running into problems friend. Please, can you check whether Title, Type and Deadline was wrote corretly?");
			RequestDispatcher rd = request.getRequestDispatcher("information.jsp");
			rd.forward(request, response);
		}
		
		
	 
	}

}
