

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		try 
		{
			PrintWriter out=response.getWriter();
			
			String unm=request.getParameter("t1");
			String ageStr=request.getParameter("t2");
			int age=Integer.parseInt(ageStr);
			
			//response.setContentType(login.html);
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/athadb1","root","W7301@jqir#");
			
			PreparedStatement pt=con.prepareStatement("select * from voter where username=? and age=?");
			
		    pt.setString(1,unm);
		    pt.setInt(2, age);
		    
		    ResultSet rs=pt.executeQuery();
		    
			if(rs.next())
			{
				request.setAttribute("unm", unm);
				//The "unm" is from line no.36 unm and next unm will send the data to next page 
				RequestDispatcher rd=request.getRequestDispatcher("/CheckAgeServlet");
				rd.forward(request, response);
			}
			else
			{
				out.println("<h2>Data Not Found!!!!!!!");
				RequestDispatcher rd=request.getRequestDispatcher("/login.html");
				rd.include(request, response);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
