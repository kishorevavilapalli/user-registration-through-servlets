package com.group1.listeners;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.group1.data.DbMgr;

/**
 * Application Lifecycle Listener implementation class MyServletContextListener
 *
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MyServletContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         ServletContext ctx = arg0.getServletContext();
         String user = ctx.getInitParameter("user");
         String password = ctx.getInitParameter("password");
         String url = ctx.getInitParameter("url");
         String className = ctx.getInitParameter("classname");
         //create db Manager
         DbMgr myDbMgr = null;
         try {
        	 myDbMgr   = new DbMgr(user, password, url, className);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        ctx.setAttribute("DbMgr", myDbMgr);
    }
	
}
