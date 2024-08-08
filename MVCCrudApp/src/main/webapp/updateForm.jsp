<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result Page Addition Student</title>
</head>
<body bgcolor="lightblue">
<br>
<br>
	<c:choose>
		<c:when test="${student ne null || ! empty student }">
			<form method="get" action="./controller/updateRecord" >
				<table align="center" >
					<tr>
						<th>SID</th>
						<td>
							<input type="text" name="sid" readonly="readonly" value="${student.sid }" >
						</td>
					</tr>
					<tr>
						<th>SNAME</th>
						<td>
							<input type="text" name="sname" value="${student.sname }" >
						</td>
					</tr>
					<tr>
						<th>SAGE</th>
						<td>
							<input type="text" name="sage" value="${student.sage }" >
						</td>
					</tr>
					<tr>
						<th>SADDRESS</th>
						<td>
							<input type="text" name="saddress" value="${student.saddress }" >
						</td>
					</tr>
					<tr>
						<th></th>
						<td>
							<input type="submit" value="update" >
						</td>
					</tr>
				</table>
			</form>
		</c:when>
		<c:otherwise>
			<h1 style="color: red; text-align: center" >
				NO RECORD TO DISPLAY
			</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>