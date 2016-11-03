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

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
         resp.sendRedirect("login.html");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		
		 DatastoreService ds = DatastoreServiceFactory .getDatastoreService();
		 

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		/*
		 * Key key=KeyFactory.createKey("User", username); try { Entity
		 * e=ds.get(key);
		 * 
		 * if (e != null) { if (password.equals(e.getProperty("password"))) {
		 * out.println("Successfully logged in.."); //continue here }else{
		 * out.println("Password incorrect");
		 * request.getRequestDispatcher("login.html").forward(request,
		 * response); } } } catch (EntityNotFoundException e) {
		 * out.println("The username doesnot exist");
		 * request.getRequestDispatcher("login.html").forward(request,
		 * response); }
		 */

		/*
		 * Filter usernamefilter = new FilterPredicate("username",
		 * FilterOperator.EQUAL, username);
		 * 
		 * Query q = new Query("User").setFilter(usernamefilter); Entity theUser
		 * = ds.prepare(q).asSingleEntity(); if (theUser != null) { if
		 * (password.equals(theUser.getProperty("password"))) {
		 * 
		 * }else{ System.out.println("Password incorrect"); //continue here }
		 */
		
		
		
		/*boolean flag = UserDetailsServlet.loginCheck(username, password);
		if (flag == true) {
			out.println("Succesfully logged in..");
			request.getRequestDispatcher("options.html").forward(request,
					response);
		} else {
			out.println("Invalid credentials");
			request.getRequestDispatcher("login.html").forward(request,
					response);
		}*/
		
		Query q=new Query("User");
		PreparedQuery pq=ds.prepare(q);
		String uname=null;
		String pass=null;
		int c=1;
		for(Entity user: pq.asIterable()){
		uname=(String)user.getProperty("username");
		pass=(String)user.getProperty("password");
		
		if(username.equals(uname)){
			
			if(pass!=null && pass.equals(password)){
			c++;
			break;
			}
			}
			
		
		}
		
		if (c>1) {
			out.println("Succesfully logged in..");
			request.getRequestDispatcher("options.html").forward(request,
					response);
		} else {
			out.println("Invalid credentials");
			request.getRequestDispatcher("login.html").forward(request,
					response);
		}
		
		
		
	}
}
