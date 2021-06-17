 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Lavra</title>
	<link type="text/css" rel="stylesheet" href="<c:url value='/style/view.css' />"> <%-- #paging 때문에 --%>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
	
	<style>
.funcs {
			margin-top: 3%;
			margin-bottom: 10%;
		}
		.funcs input 
		{
			margin-right: 10px;
			padding: 5px;
			background-color: white;
			border: 1px solid #646EFF;
			color: #646EFF;
			border-top-left-radius: 5px; 
			border-bottom-left-radius: 5px;
			border-top-right-radius: 5px; 
        	border-bottom-right-radius: 5px;
      }
		.funcs input:hover
		{ 	
			color:white; 
			background-color: #646EFF; 
		}
		.member{
			width:40%;
		}
	</style>

</head>
<body>
<%@ include file="header.jsp"%>
<div align="center">
<form:form modelAttribute="memberForm" method="post">
  <form:errors cssClass="error" /> <br><br>
		<table class = "member">
		   <tr>
            <td>User ID:</td>
            <td>
            <c:if test="${MemberForm.newAccount}">
              <form:input path="member.memberId" />
              <B><form:errors path="member.memberId" cssClass="error" /></B>
            </c:if> 
            <c:if test="${!MemberForm.newMember}">
              <c:out value="${MemberForm.member.memberId}" />
            </c:if>
            </td>
          </tr>
          <tr>
            <td>New password:</td>
            <td>
              <form:password path="member.password" /> 
              <B><form:errors path="member.password" cssClass="error" /></B></td>
          </tr>
          <tr>
            <td>Repeat password:</td>
            <td>
              <form:password path="repeatedPassword" /> 
              <B><form:errors path="repeatedPassword" cssClass="error" /></B></td>
          </tr>
		  <tr>
		  <tr>
		    <td>name:</td>
		    <td><form:input path="member.username" /> 
		      <form:errors path="member.username" cssClass="error" /></td>
		  </tr>
		    <td>Email : </td>
		    <td><form:input path="member.email" /> 
		      <form:errors path="member.email" cssClass="error" /></td>
		  </tr>
		  <tr>
		    <td>기본 주소1 : </td>
		    <td><form:input path="member.addr1" /> 
		      <form:errors path="member.addr1" cssClass="error" /></td>
		  </tr>
		  <tr>
		    <td>상세 주소 :</td>
		    <td><form:input path="member.addr2" /> 
		      <form:errors path="member.addr2" cssClass="error" /></td>
		  </tr>
		  <tr>
		    <td>우편 번호 :</td>
		    <td><form:input path="member.zip" /> 
		      <form:errors path="member.zip" cssClass="error" /></td>
		  </tr>
		  <tr>
		    <td>phone :</td>
		    <td><form:input path="member.phone" /> 
		      <form:errors path="member.phone" cssClass="error" /></td>
		  </tr>
		</table>
		  <br />
    <input type="image" src="../images/button_submit.gif" name="submit"
      value="Save Account Information" />
</form:form>
<p></p>
</div>
</body>
</html>