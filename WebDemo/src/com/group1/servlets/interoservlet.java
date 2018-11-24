package com.group1.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group1.data.DbMgr;

/**
 * Servlet implementation class interoservlet
 */
@WebServlet("/interoservlet")
public class interoservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public interoservlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ServletContext ctx = getServletContext();
		DbMgr myDbMgr = (DbMgr)ctx.getAttribute("DbMgr");
		System.out.println("connected!!");
		
		Connection conn = myDbMgr.getConnection();
		PreparedStatement stmnt =null;
		ResultSet results = null;
		String userName="";
		String email="";
		try {
			stmnt  = conn.prepareStatement("select user_name, email from users where user_id=?");
		    stmnt.setString(1, "1");
		    results = stmnt.executeQuery();
		    
		    while(results!= null && results.next()){
		    	userName = results.getString("user_name");
		    	email = results.getString("email");
		    }
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter()
	       .append("Welcome  ")
	       .append(userName)
	       .append("!! We have your email - ").append(email);
	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
