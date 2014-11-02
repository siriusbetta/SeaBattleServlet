<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>jjjk</h1>
	
	<table style="width:500px">
	<c:forEach var = "i" begin = "0" end = "9">
		<tr height = "50px">
		<c:forEach var = "j" begin = "0" end = "9">
		<c:choose>
   			<c:when test="${gameField[i * 10 + j].dangerWater == 1}">
   				<c:set var = "color" value = "#000000"></c:set>
   			</c:when>
   			<c:otherwise>
				<c:set var = "color" value = "#D3EDF6"></c:set>
			</c:otherwise>
		</c:choose>
			<td bgcolor =${color} width = "50px" ></td>
			
		</c:forEach>
		</tr>
	</c:forEach>
	</table>
  
    
  
</body>
</html>