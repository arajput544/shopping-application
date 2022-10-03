package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MODEL.Stores;



@WebServlet("/AddItem")
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Stores> stores = new ArrayList<Stores>();
		Connection c = null;
        try
        {
        	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu117";
            String username = "cs3220stu117";
            String password = "v4q42hKUx7Xv";

            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from stores" );

            while( rs.next() )
            	stores.add( new Stores( rs.getString("store_name") ) );

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
		getServletContext().setAttribute( "stores", stores );
		request.getRequestDispatcher( "/WEB-INF/AddItemView.jsp").forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String store_name;
		String sql;
		Connection c = null;
	    try
	    {
	    	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu117";
            String username = "cs3220stu117";
            String password = "v4q42hKUx7Xv";
	        
	        c = DriverManager.getConnection( url, username, password );
	        
	        if(request.getParameter("existing_store") != null) {
	        	store_name = request.getParameter("existing_store");
			}
			else {
				store_name = request.getParameter("store_name");
				
				sql = "insert into stores values (?)";
				
				PreparedStatement pstmt = c.prepareStatement( sql );
		        pstmt.setString( 1, store_name );
		        pstmt.executeUpdate();
				
			}
	        
        	sql = "insert into shoplist (name, store) values (?, ?)";
	        
	        PreparedStatement pstmt = c.prepareStatement( sql );
	        pstmt.setString( 1, request.getParameter( "item_name" ) );
	        pstmt.setString( 2, store_name );
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
