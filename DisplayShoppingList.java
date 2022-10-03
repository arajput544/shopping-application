package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

import MODEL.Shoplist;



@WebServlet("/DisplayShoppingList")
public class DisplayShoppingList extends HttpServlet {
private static final long serialVersionUID = 1L;
   
public DisplayShoppingList() {
    super();
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	List<Shoplist> shopping_list = new ArrayList<Shoplist>();
	Connection c = null;
    try
    {
    	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu117";
        String username = "cs3220stu117";
        String password = "v4q42hKUx7Xv";

        c = DriverManager.getConnection( url, username, password );
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery( "select * from shoplist order by store" );

        while( rs.next() )
        	shopping_list.add( new Shoplist( rs.getInt( "id" ), rs.getString( "name" ), rs.getString( "store" ) ) );

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
	getServletContext().setAttribute( "shopping_list", shopping_list );
	request.getRequestDispatcher( "/WEB-INF/ShoppingListView.jsp").forward( request, response );
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
}

}
