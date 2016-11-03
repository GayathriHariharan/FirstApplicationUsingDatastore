package com.example.datastoreapplication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
public class AddOperationServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		response.sendRedirect("login.html");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
	
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		DatastoreService ds = DatastoreServiceFactory
				.getDatastoreService();

	String empid=request.getParameter("empid");
	String empname=request.getParameter("empname");
	String department=request.getParameter("department");
	
	
	Entity empdetails=new Entity("Employee");
	
	Query q=new Query("Employee");
	PreparedQuery pq=ds.prepare(q);
	
	int c=1;
	
	for(Entity ee: pq.asIterable()){
	String empidone=(String)ee.getProperty("empid");
	if((empidone.equals(empid)))
		c++;
	}
	
	if(c==1){
	empdetails.setProperty("empid",empid);
	empdetails.setProperty("empname", empname);
	empdetails.setProperty("department", department);
	ds.put(empdetails);
	out.println("Details have been added successfully");
	out.println("<br>");
	request.getRequestDispatcher("options.html").forward(request, response);
	}else{
		out.println("The employee ID already exist.. Please enter any other employee id.");
		request.getRequestDispatcher("employeedetails.html").forward(request, response);
	}
	
	
		
		
	//if(empid!=null & empname!=null & department!=null)
	//UserDetailsServlet.add(empid,empname,department);
	
	/*else{
		out.println("Please fill all the fields");
		request.getRequestDispatcher("/options").forward(request, response);
	}*/
	
	}
}
