package samrat;

import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
@WebServlet("/deleteurl")
public class DeleteRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String query ="delete from BOOK WHERE id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		int id =  Integer.parseInt(req.getParameter("id"));
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///hero","root","");
		PreparedStatement ps = con.prepareStatement(query);){			
			ps.setInt(1,id);
			int x = ps.executeUpdate();
			pw.println("Records deleted succesfully "+x);				
		}catch(SQLException se) {
			se.printStackTrace();
			pw.println("<h1>" + se.getMessage()+"</h2>");	
		}catch(Exception e) {		
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage()+"</h2>");
		}
		pw.println("<a href='home.html'>Home</a>");
		pw.println("<a href='bookList'>Book List</a>");
		}
}
