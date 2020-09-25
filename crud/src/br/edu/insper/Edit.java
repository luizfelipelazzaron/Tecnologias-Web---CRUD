package br.edu.insper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dao;
		try {
			dao = new DAO();
			String id = request.getParameter("taskId");
			
			Task task = dao.getTask(id);
			if (task == null) {
				request.setAttribute("information", "you're running into problems my friend, I am sorry.");
				RequestDispatcher rd = request.getRequestDispatcher("information.jsp");
				rd.forward(request, response);
				
			} else {
				request.setAttribute("task", task);
				//request.setAttribute("deadline", deadline);
				request.setAttribute("id", task.getId());
				//request.setAttribute("title", task.getTitle());
				//request.setAttribute("type", task.getType());
				//request.setAttribute("deadline", task.getDeadline());

				RequestDispatcher rd = request.getRequestDispatcher("/edit/index.jsp");
				rd.forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dao;
		try {
			dao = new DAO();
			int taskId = Integer.parseInt(request.getParameter("taskId"));
			int userId = Integer.parseInt(request.getParameter("userId"));
			String taskTitle = request.getParameter("taskTitle");
			String taskType = request.getParameter("taskType");
			//Take deadline
			String taskDeadline = request.getParameter("taskDeadline");
			if (taskTitle == "" | taskType == "" | taskDeadline == "") {
				request.setAttribute("information", "Hey, could you verify if you informed all information?");
				RequestDispatcher rd = request.getRequestDispatcher("information.jsp");
				rd.forward(request, response);
			} else {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //you are not able to put "hh:mm:ss.SSS" yet. If you do it, you will run into problems. To understand it you can look up in https://www.guj.com.br/t/java-text-parseexception-unparseable-date/58269/2
				Date parsedDate = dateFormat.parse(taskDeadline);
				Timestamp timestamp = new Timestamp(parsedDate.getTime());
				dao.editTask(taskId, taskTitle, taskType, timestamp);
				List<Task> tasks = dao.getList(userId);
				request.setAttribute("tasks", tasks);
				User user = dao.getUser(userId);
				request.setAttribute("name", user);
				request.setAttribute("id", user.getId());
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
