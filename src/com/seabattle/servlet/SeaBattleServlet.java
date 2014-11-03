package com.seabattle.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

import com.seabattle.field.Paper;
import com.seabattle.model.Water;

@WebServlet(name = "Simple servlet", description = "This is simple servlet", urlPatterns = "/doServlet")
public class SeaBattleServlet extends HttpServlet{
	Paper paper;
	public void init() throws ServletException{
		paper = new Paper();
		 
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		Map<Integer, Water> gameField = paper.getGameField();
		List<Water> field = mapToList(gameField);
		request.setAttribute("gameField", field);
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/seabattle.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		Map<Integer, Water> gameField = paper.getGameField();
		request.setAttribute("gameField", gameField);
		response.sendRedirect("test.jsp");
	}
	
	public List<Water> mapToList(Map<Integer, Water> map){
		List<Water> water = new ArrayList<Water>();
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				water.add(i * 10 + j, map.get(i * 10 + j));
			}
		}
		return water;
	}
	
	public List<Integer> ShipsCoordinats(Map<Integer, Water> gameField){
		Set<Entry<Integer, Water>> entries = gameField.entrySet();
		List<Integer> shipCoordinates = new ArrayList<Integer>();
		Iterator<Entry<Integer, Water>> iter = entries.iterator();
		while(iter.hasNext()){
			Entry<Integer, Water> entry = iter.next();
			if(entry.getValue().getDangerWater() == 1)
				shipCoordinates.add(entry.getKey());
		}
		return shipCoordinates;
	}
}




