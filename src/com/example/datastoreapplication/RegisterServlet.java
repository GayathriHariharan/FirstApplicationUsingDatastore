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

public class RegisterServlet extends HttpServlet{
	
	 
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out=response.getWriter();
		
		 DatastoreService ds = DatastoreServiceFactory
					.getDatastoreService();
		
		String name=request.getParameter("name");
		String phonenumber=request.getParameter("phonenumber");
		String emailid=request.getParameter("emailid");
		String username=request.getParameter("username");  
		String password=request.getParameter("password");
		
		
		
			
/*
		 Query q=new Query("User");
		 PreparedQuery pq=ds.prepare(q);
		 
		 int c=1;
		 
		 for(Entity u: pq.asIterable()){
			String usernametest= (String) u.getProperty("username");
			if((usernametest.equals("username")))
				c++;
			
		 }
		 
		 if(c==1){*/
		Entity user = new Entity("User");
		user.setProperty("name", name);
		user.setProperty("phonenumber", phonenumber);
		user.setProperty("emailid", emailid);
		user.setProperty("username", username);
		user.setProperty("password", password);
		ds.put(user);
		
		out.println("You are Successfully registered.. Please Login to continue..");
		request.getRequestDispatcher("login.html").forward(request, response);
		/*}else{
			 out.println("Username already exist.. please enter any unique username");
				request.getRequestDispatcher("register.html").forward(request, response); 
		 }*/
		
/*boolean flag=UserDetailsServlet.createUser(name, phonenumber, emailid, username, password);
		
	if(flag==true){
			out.println("Registered successfully");
			request.getRequestDispatcher("login.html").forward(request, response);
		}else{
			out.println("Something went wrong..Please register again");
			request.getRequestDispatcher("register.html").forward(request, response);
		}*/
	}
}
