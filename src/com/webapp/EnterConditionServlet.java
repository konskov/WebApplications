package com.webapp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


@WebServlet(
		name="enterconditionservlet",
		urlPatterns="/search"
)

public class EnterConditionServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		HashMap<String, Integer> patientTypes;
		String conditionName = req.getParameter("name");
		System.out.print(conditionName);
		try {
			ConditionService conditionService = new ConditionService();
			patientTypes = conditionService.getPatientTypes(conditionName);
			req.setAttribute("patientTypes", patientTypes);
			req.setAttribute("searchTerm", conditionName);
			RequestDispatcher view = req.getRequestDispatcher("results.jsp");
	        view.forward(req, resp);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at ").append(request.getContextPath());
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
    	dispatcher.forward(request, response);
	}
}
