package Servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteItem")
public class DeleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id") == null) {
			response.sendRedirect("DisplayShoppingList");
			return;
		}
		Connection c = null;
	    try
	    {
	    	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu117";
            String username = "cs3220stu117";
            String password = "v4q42hKUx7Xv";
	        
	        c = DriverManager.getConnection( url, username, password );
        	String sql = "delete from shoplist where id = (?)";
	        
	        PreparedStatement pstmt = c.prepareStatement( sql );
	        pstmt.setString( 1, request.getParameter( "id" ) );
	        pstmt.executeUpdate();
	        
	        c.close();
	    }
	    catch( SQLException e )
	    {
	    	throw new ServletException( e );
	    }
	    finally
	    {
	    	try
	        {
	    		if( c != null ) c.close();
	        }
	        catch( SQLException e )
	        {
	        	throw new ServletException( e );
	        }
	    }
		response.sendRedirect("DisplayShoppingList");
	}
	
}
