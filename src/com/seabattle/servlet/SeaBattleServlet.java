package com.seabattle.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seabattle.field.Paper;

@WebServlet(name = "Simple servlet", description = "This is simple servlet", urlPatterns = "/doServlet")
public class SeaBattleServlet extends HttpServlet{
	public void init() throws ServletException{
		Paper paper = new Paper();
		//System.out.println(paper.toString());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		response.sendRedirect("seabattle.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		System.out.println("Name: " +request.getParameter("name"));
		System.out.println("Gender: " +request.getParameter("gender"));
		System.out.println("Email: " +request.getParameter("email"));
		System.out.println("Phone: " +request.getParameter("phone"));
		System.out.println("City: " +request.getParameter("city"));
		request.getRequestDispatcher("details.jsp");
	}
}
