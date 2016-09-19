import com.sun.jna.win32.StdCallLibrary.StdCallCallback;

public class FOnNotifyTicksGet implements StdCallCallback{
	public FOnNotifyTicksGet(SKQuoteLib skquotelib){
	}
	public void callback(short sMarketNo, short sStockidx, int nPtr, int nTime,int nBid, int nAsk, int nClose, int nQty){
		System.out.println(sMarketNo);
		System.out.println(sStockidx);
		System.out.println(nPtr);
		System.out.println(nTime);
		System.out.println(nBid);
		System.out.println(nAsk);
		System.out.println(nClose);
		System.out.println(nQty);
	}
}
