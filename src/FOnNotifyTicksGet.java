import com.sun.jna.win32.StdCallLibrary.StdCallCallback;

public class FOnNotifyTicksGet implements StdCallCallback{
	
	private SKQuoteLib skquotelib=null;
	private mysql mydb = null;
	
	public FOnNotifyTicksGet(SKQuoteLib skquotelib){
		this.skquotelib=skquotelib;
	}
	public void setMySql(String table){
		this.mydb = new mysql(table);
	}
	public void callback(short sMarketNo, short sStockidx, int nPtr, int nTime,int nBid, int nAsk, int nClose, int nQty){
		System.out.println("No: "+sMarketNo);
		System.out.println("Id: "+sStockidx);
		System.out.println("Ptr: "+nPtr);
		System.out.println("Time: "+nTime);
		System.out.println("Bid: "+nBid);
		System.out.println("Ask: "+nAsk);
		System.out.println("Close: "+nClose);
		System.out.println("Qty: "+nQty);
		System.out.println("");
		mydb.insertTable(sMarketNo, sStockidx, nPtr, nTime, nBid, nAsk, nClose, nQty);
	}
	
	public void clearMysql(){
		mydb.Close();
	}
}
