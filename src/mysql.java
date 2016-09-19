import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 

public class mysql {
	private Connection con = null;
	private Statement stat = null;
	private ResultSet rs = null; 
	private PreparedStatement pst = null; 
	private String insertdbSQL = "insert into stock (bid_price, ask_price, price, volume) values (?,?,?,?)";
	public mysql() 
	{ 
	  try { 
	    Class.forName("com.mysql.jdbc.Driver"); 
	    con = DriverManager.getConnection( 
	    "jdbc:mysql://host:ip/database", 
	    "root","password"); 	      
	  } 
	  catch(ClassNotFoundException e) 
	  { 
	    System.out.println("DriverClassNotFound :"+e.toString()); 
	  }
	  catch(SQLException x) { 
	    System.out.println("Exception :"+x.toString()); 
	  } 
	}
	public void insertTable(String bid,String ask,String price,String volume) 
	{ 
	  try 
	  { 
	    pst = con.prepareStatement(insertdbSQL); 
	      
	    pst.setString(1, bid); 
	    pst.setString(2, ask); 
	    pst.setString(3, price); 
	    pst.setString(4, volume); 
	    pst.executeUpdate(); 
	  } 
	  catch(SQLException e) 
	  { 
	    System.out.println("InsertDB Exception :" + e.toString()); 
	  } 
	}
	public void Close() 
	{ 
	  try 
	  { 
	    if(rs!=null) 
	    { 
	      rs.close(); 
	      rs = null; 
	    } 
	    if(stat!=null) 
	    { 
	      stat.close(); 
	      stat = null; 
	    } 
	    if(pst!=null) 
	    { 
	      pst.close(); 
	      pst = null; 
	    } 
	  } 
	  catch(SQLException e) 
	  { 
	    System.out.println("Close Exception :" + e.toString()); 
	  } 
	}
}
