package com.group1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group1.data.DbMgr;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet(name = "Registration", urlPatterns = { "/Registration" })
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String userEmail = request.getParameter("userEmail");
		String userCountry = request.getParameter("userCountry");
		// create connection with database
		ServletContext ctx = getServletContext();
		DbMgr dbMgr = (DbMgr) ctx.getAttribute("DbMgr");

		Connection con = dbMgr.getConnection();
		String insrtQuery = "INSERT INTO USERS(user_id, user_name, user_password, email, country) "
				+ " values (?,?,?,?,?)";
		boolean success = false;
		try {
			PreparedStatement ps = con.prepareStatement(insrtQuery);
			ps.setString(1, userName);
			ps.setString(2, userName);
			ps.setString(3, userPassword);
			ps.setString(4, userEmail);
			ps.setString(5, userCountry);
			success = ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		RequestDispatcher rd = ctx.getRequestDispatcher("/login.html");
		PrintWriter out = response.getWriter();
		out.println("<font color=green> Registration complete! " + "please login</font>");
		rd.include(request, response);
		

	}

}
