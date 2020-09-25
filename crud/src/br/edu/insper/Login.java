package br.edu.insper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dao;
		try {
			dao = new DAO();
			String name = request.getParameter("userName");
			String password = request.getParameter("userPassword");
			User userName = dao.login(name, password);
			
			if (userName == null) {
				request.setAttribute("information", "Hey, you are not able to enter in our website because you don't wrote you're name or password corretly. Please, try again.");
				RequestDispatcher rd = request.getRequestDispatcher("information.jsp");
				rd.forward(request, response);
			} else {
				//Downloading tasks
				request.setAttribute("name", userName);
				request.setAttribute("id", userName.getId());
				List<Task> tasks = dao.getList(userName.getId());
				request.setAttribute("tasks", tasks);
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			PrintWriter out = response.getWriter();
			out.print("<body>");
			out.print("<h1>Hey, you are not able to enter in out website because you aren't register.</h1>");
			out.print("</body>");
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
