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
public class UpdateServlet extends HttpServlet{
	
	private static DatastoreService ds = DatastoreServiceFactory
			.getDatastoreService();

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String updateempid=(String)request.getParameter("empid");
		String updateempname=(String)request.getParameter("empname");
		String updatedepartment=(String)request.getParameter("department");

		Query q=new Query("Employee");
		PreparedQuery pq=ds.prepare(q);
		
		int c=1;
		
		for(Entity ee: pq.asIterable()){
			String empidone=(String)ee.getProperty("empid");
			if((empidone.equals(updateempid)))
				c++;
			
			if(c==2){
				/*ds.delete(ee.getKey());
				out.println("Enter the employee details that you want to update");
				request.getRequestDispatcher("updateemployeedetails.html").forward(request, response);
				*/
				
				ee.setProperty("empid",updateempid);
				ee.setProperty("empname", updateempname);
				ee.setProperty("department", updatedepartment);
				ds.put(ee);
				out.println("Updated successfully");
				request.getRequestDispatcher("options.html").forward(request, response);
				
				break;
			}
			
			}
			
		if(c==1){
			out.println("The Employee ID does not exist.. Please retry..");
			request.getRequestDispatcher("update.html").forward(request, response);
			
		}
		
}
	
	
}
