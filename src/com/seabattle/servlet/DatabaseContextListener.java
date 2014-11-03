package com.seabattle.servlet;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.seabattle.database.DBConectionManager;

@WebListener
public class DatabaseContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent servletContex) {
		Connection con = (Connection) servletContex.getServletContext().getAttribute("DBConnection");
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContex) {
		ServletContext ctx = servletContex.getServletContext();
		String user = ctx.getInitParameter("dbUser");
		String pass = ctx.getInitParameter("dbPassword");
		String url = ctx.getInitParameter("dbURL");
		try{
			DBConectionManager dbManager = new DBConectionManager(url, user, pass);
			ctx.setAttribute("DBConnection", dbManager.getConnection());
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}

}
