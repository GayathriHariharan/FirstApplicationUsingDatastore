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
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.*;

@SuppressWarnings("serial")
public class OptionsServlet extends HttpServlet {
	
	private static DatastoreService ds = DatastoreServiceFactory
			.getDatastoreService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String deleteempid=(String)request.getParameter("delempid");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		//1st try
		Query q=new Query("Employee");
		PreparedQuery pq=ds.prepare(q);
		
		int c=1;
		
		for(Entity ee: pq.asIterable()){
			String empidone=(String)ee.getProperty("empid");
			if((empidone.equals(deleteempid)))
				c++;
			
			if(c==2){
				ds.delete(ee.getKey());
				out.println("Deleted successfully");
				out.println("<form action='options.html' method='get'>"+
						"<input type='submit' value='BACK'>"+
						"</form>");	break;			
			}
			
			}
			
		if(c==1){
			out.println("The Employee ID does not exist.. Please retry..");
			request.getRequestDispatcher("delete.html").forward(request, response);
			
		}
		/*
		
		//2nd try
		Query q=new Query("Employee").setFilter(new FilterPredicate("empid",FilterOperator.EQUAL,deleteempid));
		PreparedQuery pq=ds.prepare(q);
		Entity employee=pq.asSingleEntity();
		Key k=KeyFactory.createKey("Employee", "empid");
		ds.delete(k);
		response.getWriter().println("Deleted Successfully");
		response.getWriter().println("<form action='options.html' method='get'>"+
				"<input type='submit' value='BACK'>"+
				"</form>");
*/
	}


	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String options=request.getParameter("options");
		
		if(options.equals("add")){
			
			request.getRequestDispatcher("employeedetails.html").forward(request, response);
			
			

		}else if(options.equals("view")){
			
			Query q=new Query("Employee");
			PreparedQuery pq=ds.prepare(q);
			//Entity e=null;
			//Key k=KeyFactory.createKey("Employee","empid");
			for(Entity empdetails: pq.asIterable()){
				out.println("<br>"+"<centre>"+"Employee Details"+"</centre>"+"<br>");
				out.println("----------------------------------------"+"<br>");
				out.println("ID         :"+empdetails.getProperty("empid")+"<br>");
				out.println("Name       :"+empdetails.getProperty("empname")+"<br>");
				out.println("Department :"+empdetails.getProperty("department")+"<br>");
				out.println("----------------------------------------"+"<br>");
				
				//e=ds.get(k);
			//continue here
			}
			//request.getRequestDispatcher("options.html").forward(request, response);

			out.println("<form action='options.html' method='get'>"+
			"<input type='submit' value='BACK'>"+
			"</form>");
			
			/*try {
				out.println(UserDetailsServlet.view());
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//out.println("No Entry Found");
			}*/
		}else if(options.equals("delete")){
			System.out.println("inside delete");
		/*out.println("<form >");
		out.println("<input type='text' name='delempid'>");
		out.println("<input type='submit' value='delete'>");
	     out.println("</form>");

*/	    
			
			request.getRequestDispatcher("delete.html").forward(request, response);
		//String deleteempid=(String)request.getParameter("delempid");
		//1st try
		/*Query q=new Query("Employee");
		PreparedQuery pq=ds.prepare(q);
		
		int c=1;
		
		for(Entity ee: pq.asIterable()){
			String empidone=(String)ee.getProperty("empid");
			if((empidone.equals(deleteempid)))
				c++;
			
			if(c>1){
				ds.delete(ee.getKey());
				out.println("Deleted successfully");
				break;
			}
			
			}
		if(c==1){
			out.println("The Employee ID does not exist.. Please retry..");
			
		}
		*/
		
		//2nd try
		/*Query q=new Query("Employee").setFilter(new FilterPredicate("empid",FilterOperator.EQUAL,deleteempid));
		PreparedQuery pq=ds.prepare(q);
		Entity employee=pq.asSingleEntity();
		Key k=KeyFactory.createKey("Employee", "empid");
		ds.delete(k);
		out.println("<form action='options.html' method='get'>"+
				"<input type='submit' value='BACK'>"+
				"</form>");*/
			//continue here-2
		}else if(options.equals("update")){
request.getRequestDispatcher("update.html").forward(request, response);
}
	}

	
	
}
