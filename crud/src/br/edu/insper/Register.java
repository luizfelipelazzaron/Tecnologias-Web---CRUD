package br.edu.insper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
			String confirmPassword = request.getParameter("confirmPassword");
			if (name == "") {
				request.setAttribute("information", "Hey, you don't wrote your name corretly, don't you?");
				RequestDispatcher rd = request.getRequestDispatcher("information.jsp");
				rd.forward(request, response);
			} else if (password == "") {
				request.setAttribute("information", "Hey, you don't wrote your password, don't you?");
				RequestDispatcher rd = request.getRequestDispatcher("information.jsp");
				rd.forward(request, response); 
			} else if (password.contentEquals(confirmPassword)){
				dao.register(name, password);
				request.setAttribute("information", "congratulations! You're already register.");
				RequestDispatcher rd = request.getRequestDispatcher("information.jsp");
				rd.forward(request, response);
				
			} else {
				request.setAttribute("information", "Hey, the password isn't confirmed yet! Please, try again.");
				RequestDispatcher rd = request.getRequestDispatcher("information.jsp");
				rd.forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			PrintWriter out = response.getWriter();
			out.print("<body>");
			out.print("<h1>Hey, you are not able to register.</h1>");
			out.print("</body>");
			e.printStackTrace();
		}
	}
}

