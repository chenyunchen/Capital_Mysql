import com.sun.jna.win32.StdCallLibrary.StdCallCallback;

public class FOnNotifyConnection implements StdCallCallback{
	public FOnNotifyConnection(SKQuoteLib skquotelib){
	}
	public void callback(int a,int b){
		System.out.println(this.getClass().getName()+" , "+a+" , "+b);
		if(a==100 && b==0) {
			System.out.println("�������A���n�J���\!!");
		}
		else
			System.out.println("�������_!!");
	}
} 