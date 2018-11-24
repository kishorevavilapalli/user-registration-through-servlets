package com.group1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("password");
		String selectQuery = "select user_id from users "
							+ "where user_name=? and user_password=?";
		
		ServletContext ctx = getServletContext();
		DbMgr dbMgr = (DbMgr) ctx.getAttribute("DbMgr");

		Connection con = dbMgr.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(selectQuery);
			ps.setString(1, userName);
			ps.setString(2, userPassword);
			ResultSet results = ps.executeQuery();
			String userId="";
			while(results!=null && results.next()){
				userId = results.getString("user_id");
			}
			if(userId!=""){
				RequestDispatcher rd = ctx.getRequestDispatcher("/profile.html");
				PrintWriter out = response.getWriter();
				out.println("<font color=green> User Found! </font>");
				rd.include(request, response);
			}else{
				RequestDispatcher rd = ctx.getRequestDispatcher("/error.html");
				PrintWriter out = response.getWriter();
				out.println("<font color=green> User Not Found! </font>");
				rd.include(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
