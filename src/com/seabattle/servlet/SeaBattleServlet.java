package com.seabattle.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seabattle.database.CoordinataJDBC;
import com.seabattle.database.Coordinats;
import com.seabattle.logic.GameField;
import com.seabattle.model.Water;
/***
 * 
 * @author Alexey Konyshev
 * Сервлет, обрабатывает запросы от браузера, если url заканчивается цифрой, то 
 * url парсится и происходит обращение к базе данных, откуда по id ранее сгенерированного
 * сета координат выводится поле. 
 */
@WebServlet(name = "Simple servlet", description = "This is simple servlet", urlPatterns = "/doServlet/*")
public class SeaBattleServlet extends HttpServlet{
	GameField paper;
	Connection con;
	CoordinataJDBC queries;
	public void init() throws ServletException{
		paper = new GameField();
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




