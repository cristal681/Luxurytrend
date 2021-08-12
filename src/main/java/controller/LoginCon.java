package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDAO;
import user.UserDTO;


@WebServlet("/LoginCon")
public class LoginCon extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// 
		String email = request.getParameter("email");
	      String pw = request.getParameter("pw");
	     UserDAO dao = new UserDAO();
	     UserDTO user = dao.login(email, pw);
	      
	         if(user != null) {
	        	 HttpSession session = request.getSession();
	        	 session.setAttribute("login", user);
	        	 response.sendRedirect("main.jsp");
	         }else {
	        	 System.out.println("로그인실패..");
	            response.sendRedirect("login.jsp");
	         }
	         

		

	    		
	    	}

	}


