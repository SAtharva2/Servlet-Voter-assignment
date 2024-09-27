

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CheckAgeServlet")
public class CheckAgeServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String unm=(String) request.getAttribute("unm");//The "unm" is from ServletLogin page
		//& the String unm is variable which store the data from "unm"
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/athadb1","root","W7301@jqir#");
			
			PreparedStatement pst=con.prepareStatement("select age from voter where username=?");
			pst.setString(1, unm);
			
			ResultSet rs=pst.executeQuery();
			
			if(rs.next())
			{
				int age=rs.getInt("age");
				
				if(age>=18)
				{
					out.println("<h2>You Are Eleigible To VOTE!!");
				}
				else
				{
					out.println("<h2>You Are NOT Eligible TO VOTE!!");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace(out);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
