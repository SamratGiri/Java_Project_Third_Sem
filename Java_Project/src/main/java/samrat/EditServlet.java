package samrat;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String query ="UPDATE BOOK SET BOOKNAME = ?, BOOKEDITION = ?, BOOKPRICE = ? WHERE id = ?";
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		int id =  Integer.parseInt(req.getParameter("id"));
		String bookName = req.getParameter("bookName");
		String bookEdition = req.getParameter("bookEdition");
		float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///hero","root","");
		PreparedStatement ps = con.prepareStatement(query);){
			ps.setString(1, bookName);
			ps.setString(2, bookEdition);
			ps.setFloat(3,bookPrice);
			ps.setInt(4, id);
			int count = ps.executeUpdate();
			if(count==1) {
			pw.println("<h2>Record is Edited Successfully</h2>");
			}else {
				pw.println("<h2>Record not Registered Sucessfully</h2>");
			}					
		}catch(SQLException se) {
			se.printStackTrace();
			pw.println("<h1>" + se.getMessage()+"</h2>");
	
		}catch(Exception e) {		
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage()+"</h2>");
		}
		pw.println("<a href='home.html'>Home</a>");
		pw.println("<br>");
		pw.println("<a href='bookList'>Book List</a>");
		}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

}

