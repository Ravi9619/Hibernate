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
		<c:when test="${status eq 'success' }">
			<h1 style="color: green; text-align: center" >
				RECORD INSERTED SUCCESSFULLY
			</h1>
		</c:when>
		<c:otherwise>
			<h1 style="color: red; text-align: center" >
				RECORD INSERTION FAILED
			</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>