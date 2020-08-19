<%@ page import ="java.util.*" import ="java.util.Map.Entry" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="com.webapp.*" language="java" %>
<%@ page import ="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WebApp Project</title>
<link rel=StyleSheet href="site.css" type="text/css" />
</head>
<body>

<%
HashMap<String, Integer> result = (HashMap<String, Integer>) request.getAttribute("patientTypes");
String search_term = (String) request.getAttribute("searchTerm");
%>

<h1>Search results for <span id="search_term"> <%=search_term %> </span></h1>


<h4> Showing results from <span id="total_studies"> <%=result.remove("Total_studies") %></span> relevant studies</h4>
<table style="with: 80%" id="results" >
<tr>
    <th>Patient type</th>
    <th>Number of patients</th>
  </tr>
<%for (Map.Entry<String, Integer> entry: result.entrySet()){%>
	<tr>
	<td><%=entry.getKey()%></td>
	<td><%=entry.getValue()%></td>
	</tr>
<%}%>
</table>
</body>
</html>