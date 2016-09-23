import java.util.Arrays;

import com.sun.jna.win32.StdCallLibrary.StdCallCallback;


public class FOnNotifyQuote implements StdCallCallback{
	private SKQuoteLib skquotelib=null;
	public FOnNotifyQuote(SKQuoteLib skquotelib){
		this.skquotelib=skquotelib;
	} 
	public void callback(short Market, short Index) {
		int Status;
		SKQuoteLib.Stock stock = new SKQuoteLib.Stock();
		Status = skquotelib.SKQuoteLib_GetStockByIndex(Market, Index, stock);
		if (Status == 0) {
			String[] str = translation(stock);
			System.out.println(Arrays.toString(str));
		}
	}
	private String[] translation(SKQuoteLib.Stock stock) {
		double Dot = Math.pow(10, stock.m_sDecimal);
		String[] result = new String[4];
		result[0] = (stock.m_nBid / Dot) + "";
		result[1] = (stock.m_nAsk / Dot) + "";
		result[2] = (stock.m_nRef / Dot) + "";
		result[3] = stock.m_nTickQty + "";
		return result;
		
	}
}
