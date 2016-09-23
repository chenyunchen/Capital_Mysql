import java.util.Arrays;

import com.sun.jna.win32.StdCallLibrary.StdCallCallback;

public class FOnNotifyKLineData implements StdCallCallback{
	private SKQuoteLib skquotelib=null;
	public FOnNotifyKLineData(SKQuoteLib skquotelib){
		this.skquotelib=skquotelib;
	}
	public void callback(String Stock, String Data) {
		System.out.println(Data);
	}
}
