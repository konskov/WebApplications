<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WebApp Project</title>
<link rel=StyleSheet href="site.css" type="text/css" />
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>
<body>

<div class="sidenav">
	<a href="#" > Home <i class="fas fa-home"></i></a>
	<a href="about.html" > About <i class="far fa-address-card"></i></a>
	<a href="contact.html"> Contact <i class="fas fa-envelope"></i></a>
</div>

 <div align="center" >
  <h1>Search for condition/disease</h1>
  <form action="<%= request.getContextPath() %>/search" method="post">
   <table style="with: 80%">
    <tr>
     <td><input type="text" class="search" name="name" placeholder="Search condition..." /></td>
    </tr>
   </table>
   <input type="submit" value="Submit" />
  </form>
 </div>
</body>
</html>