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
	private String insertdbSQL;
	public mysql(String table) 
	{ 
	  try { 
	    Class.forName("com.mysql.jdbc.Driver"); 
	    con = DriverManager.getConnection( 
	    "jdbc:mysql://host:port/database", 
	    "root","password"); 	
	    insertdbSQL = "insert into "+table+" (sMarketNo,sStockidx,nPtr,nTime,nBid,nAsk,nClose,nQty) values (?,?,?,?,?,?,?,?)";
	  } 
	  catch(ClassNotFoundException e) 
	  { 
	    System.out.println("DriverClassNotFound :"+e.toString()); 
	  }
	  catch(SQLException x) { 
	    System.out.println("Exception :"+x.toString()); 
	  } 
	}
	public void insertTable(short sMarketNo, short sStockidx, int nPtr, int nTime,int nBid, int nAsk, int nClose, int nQty) 
	{ 
	  try 
	  { 
	    pst = con.prepareStatement(insertdbSQL); 
	      
	    pst.setShort(1, sMarketNo); 
	    pst.setShort(2, sStockidx); 
	    pst.setInt(3, nPtr); 
	    pst.setInt(4, nTime);
	    pst.setInt(5, nBid); 
	    pst.setInt(6, nAsk); 
	    pst.setInt(7, nClose); 
	    pst.setInt(8, nQty);
	    
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
