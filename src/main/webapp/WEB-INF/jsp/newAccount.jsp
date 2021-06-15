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
	</style>

</head>
<body>
<%@ include file="header.jsp"%>
		<table class="n13">
		  <tr>
		    <td>ID:</td>
		    <td><form:input path="member.memberId" />
		      <form:errors path="account.firstName" cssClass="error" /></td>
		  </tr>
		  <tr>
		    <td>Last name:</td>
		    <td><form:input path="account.lastName" /> 
		      <form:errors path="account.lastName" cssClass="error" /></td>
		  </tr>
		  <tr>
		    <td>Email:</td>
		    <td><form:input path="account.email" />
		      <form:errors path="account.email" cssClass="error" /></td>
		  </tr>
		  <tr>
		    <td>Phone:</td>
		    <td><form:input path="account.phone" /> 
		      <form:errors path="account.phone" cssClass="error" /></td>
		  </tr>
		  <tr>
		    <td>Address 1:</td>
		    <td><form:input path="account.address1" />
		      <form:errors path="account.address1" cssClass="error" /></td>
		  </tr>
		  <tr>
		    <td>Address 2:</td>
		    <td><form:input path="account.address2" />
		      <form:errors path="account.address2" cssClass="error" /></td>
		  </tr>
		  <tr>
		    <td>City:</td>
		    <td><form:input path="account.city" /> 
		      <form:errors path="account.city" cssClass="error" /></td>
		  </tr>
		  <tr>
		    <td>State:</td>
		    <td><form:input path="account.state" /> 
		      <form:errors path="account.state" cssClass="error" /></td>
		  </tr>
		  <tr>
		    <td>Zip:</td>
		    <td><form:input path="account.zip" /> 
		      <form:errors path="account.zip" cssClass="error" /></td>
		  </tr>
		  <tr>
		    <td>Country:</td>
		    <td><form:input path="account.country" />
		      <form:errors path="account.country" cssClass="error" /></td>
		  </tr>
		</table>
		
		<h3>
		  <font color="darkgreen">Profile Information</font>
		</h3>
		
		<table class="n13" >
		  <tr>
		    <td>Language Preference:</td>
		    <td><form:select path="account.languagePreference" items="${languages}" />
		      <form:errors path="account.languagePreference" cssClass="error" /></td>
		  </tr>
		  <tr>
		    <td>Favourite Category:</td>
		    <td><form:select path="account.favouriteCategoryId" items="${categories}" /> 
		      <form:errors path="account.favouriteCategoryId" cssClass="error" /></td>
		  </tr>
		  <tr>
		    <td colspan="2"><form:checkbox path="account.listOption" label="Enable MyList" /> 
		      <form:errors path="account.listOption" cssClass="error" /></td>
		  </tr>
		  <tr>
		    <td colspan="2"><form:checkbox path="account.bannerOption" label="Enable MyBanner" /> 
		      <form:errors path="account.bannerOption" cssClass="error" /></td>
		  </tr>
		</table>
</body>
</html>