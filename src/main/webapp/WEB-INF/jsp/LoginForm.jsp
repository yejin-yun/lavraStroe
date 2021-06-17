<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp"%>
<meta charset="EUC-KR">
<title>login page</title>

<style>


</style>
</head>
<body>

<br>


<div style = "margin:50px; margin-top:200px " >
  <form action='<c:url value="/shop/login.do"/>' method="POST">
    <c:if test="${!empty signonForwardAction}">
      <input type="hidden" name="forwardAction"
        value='<c:url value="${signonForwardAction}"/>' />
    </c:if>
    <div style = "text-align:ceter; width:600px;margin:auto;">
    <c:if test="${!empty message}">
  		<b><font color="red"><c:url value="${message}" /></font></b>
	</c:if>
	</div>
	<br><br>
    <table style = " width:500px; height:100px; margin:auto;">
      <tr>
        <td colspan="2">Please enter your Id and password. <br />&nbsp;
        </td>
      </tr>
      <tr>
        <td>ID:</td>
        <td><input type="text" name="memberId" size="20"/></td>
        <td rowspan="2">
        <input name="update" type="image" src="../images/bagic/login.png"  style = "width:80px"/>
      	</td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type="password" name="password" size="20"/></td>
      </tr>
      <tr>
        </tr>
    </table>
  </form>
  <div style = "text-align:center; width:500px; margin:auto;">
  		<br>
	  <a href='<c:url value="/shop/newMember.do"/>'> 
	    <h4 style=" background-color:black; color:white">new Account</h4>
	  </a>
  </div>
</div>

</body>
</html>