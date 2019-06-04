<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body style="background-color:lightblue;">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<form:form action="${contextPath}/advert/logout" method="post" commandName="advert">
<input type="submit" value="Logout">
</form:form>
<center>
<h1>Welcome, ${user.firstName} </h1>

<a href="${contextPath}/category/add" >Add a Book</a> <br />
<a href="${contextPath}/advert/add" >Book Description</a> <br />
<a href="${contextPath}/advert/sellerlist" >View Books</a> <br />
</center>

</body>
</html>