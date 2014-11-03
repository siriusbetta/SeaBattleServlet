<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Title of the document</title>
<style>
table, th, td{
	border: 1px solid black;
	margin: auto;
}
.button{
	width: 500px;
	height: 50px;
	background-color: black;
	text-align: center;
}

</style>
</head>

<body>
<div>
<div id = "somediv">
<table style="width:500px">
	<c:forEach var = "i" begin = "0" end = "9">
		<tr height = "45px">
		<c:forEach var = "j" begin = "0" end = "9">
		<c:choose>
   			<c:when test="${gameField[i * 10 + j].dangerWater == 1}">
   				<c:set var = "color" value = "#000000"></c:set>
   			</c:when>
   			<c:otherwise>
				<c:set var = "color" value = "#D3EDF6"></c:set>
			</c:otherwise>
		</c:choose>
			<td bgcolor =${color} width = "45px" ></td>
			
		</c:forEach>
		</tr>
	</c:forEach>
	</table>
</div>

<div style = "width: 500px; height: 70px; text-align: center; 
margin: auto; position: relative;" >
		<div style = "position:absolute; bottom:0; left:234px;">
			
		</div>
</div>
	
</div>
</body>
</html>