package com.seabattle.servlet;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seabattle.database.CoordinataJDBC;
import com.seabattle.database.Coordinats;
import com.seabattle.field.Paper;
import com.seabattle.model.Water;

@WebServlet(name = "Simple servlet", description = "This is simple servlet", urlPatterns = "/doServlet/*")
public class SeaBattleServlet extends HttpServlet{
	Paper paper;
	Connection con;
	CoordinataJDBC queries;
	public void init() throws ServletException{
		paper = new Paper();
		con = (Connection) getServletContext().getAttribute("DBConnection");
		queries = new CoordinataJDBC();
		queries.setDataSource(con);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		String url = request.getRequestURL().toString();
		String[] arr = url.split("/");
		int id = 0;
		Map<Integer, Water> gameField = null;
		List<Water> field = null;
		List<Coordinats> ships = null;
		
		try{
			id = Integer.parseInt(arr[arr.length-1]);
		}catch(NumberFormatException e){}
		try {
			if(id == 0){
				gameField = paper.getGameField();
				field = paper.mapToList(gameField);
				ships = paper.ShipsCoordinats(gameField);
				queries.create(ships);
			}else{
					ships = queries.getCoordinats(id);
					Map<Integer, Water> fieldMap = paper.placeShips(ships);
					field = paper.mapToList(fieldMap);
				}
			} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("gameField", field);
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/seabattle.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
	}
	
}




