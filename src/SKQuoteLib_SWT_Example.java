import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import java.util.Timer;
import java.util.TimerTask;

import com.sun.jna.Native;
import com.sun.jna.ptr.ShortByReference;

public class SKQuoteLib_SWT_Example {
	//static FOnNotifyMarketTot fnmt;
	//static FOnNotifyKLineData fnkld;
	public static ShortByReference sbr_tick = new ShortByReference((short) -1);
	//static FOnNotifyQuote qu;
	static FOnNotifyTicksGet tg;
	public static void main(String[] args) throws InterruptedException{
	    String argu = args[0];
		final Display display = new Display();
		final Shell shell = new Shell(display);
		//shell.setSize(300, 200);
		//shell.setText("群益報價API");
		//shell.setLayout(new RowLayout());
		final SKQuoteLib skquotelib = (SKQuoteLib) Native.loadLibrary(
				"SKQuoteLib", SKQuoteLib.class);
		System.out.println("start tw");

		//final Button button = new Button(shell, SWT.PUSH);
		//button.setText("接收報價");
		//final Button button2 = new Button(shell, SWT.PUSH);
		//button2.setText("接收報價2");
		//final Button close = new Button(shell, SWT.PUSH);
		//close.setText("結束連線");
		//final Label connectionlabel = new Label(shell, SWT.SHADOW_IN);
		//connectionlabel.setText("Connecting...");
		System.out.println("skquotelib =" + skquotelib);
		final int ini = skquotelib.SKQuoteLib_Initialize("User Id","Password");
		System.out.println("inti " + ini);
		if (ini == 0) {
			//qu = new FOnNotifyQuote(skquotelib);
			//fnkld=new FOnNotifyKLineData(skquotelib);
			///fnmt = new FOnNotifyMarketTot(skquotelib,twse_ohlc);
			//fnq = new FOnNotifyQuote(skquotelib,twse_ohlc);
			//int kline = skquotelib.SKQuoteLib_AttachKLineDataCallBack(fnkld);
			tg = new FOnNotifyTicksGet(skquotelib);
			tg.setMySql(argu);
			int citime = skquotelib
					.SKQuoteLib_AttchServerTimeCallBack(new FOnNotifyServerTime(
							skquotelib));
			int connectioncb = skquotelib
					.SKQuoteLib_AttachConnectionCallBack(new FOnNotifyConnection(
							skquotelib));
			
			// int tot = skquotelib.SKQuoteLib_AttachMarketTotCallBack(fnmt);
			//int c = skquotelib
			//		.SKQuoteLib_AttachQuoteCallBack(qu);
			int t;
			if (argu.equals("history_tick")){
				t = skquotelib
					.SKQuoteLib_AttachHistoryTicksGetCallBack(tg);
			} else {
				t = skquotelib.SKQuoteLib_AttachTicksGetCallBack(tg);
			}
			skquotelib.SKQuoteLib_EnterMonitor();
		}
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				int tmp = skquotelib.SKQuoteLib_RequestTicks(sbr_tick, "TX00");
			}
			
		},5000);
		
		//Thread th = new Thread(){
		//	public void run(){
		//		int tmp = skquotelib.SKQuoteLib_RequestTicks(sbr_tick, "TX00");
		//	}
		//};
		//th.start();
		//try {
        //    // Thread B 加入 Thread A
        //    th.join();
        //} 
        //catch(InterruptedException e) { 
        //    e.printStackTrace(); 
        //} 

		//button.addSelectionListener(new SelectionListener() {

		//	public void widgetSelected(SelectionEvent event) {
		//		//String tmp = "Status: "
		//		//		+ skquotelib.SKQuoteLib_RequestStocks(sbr_tick, "TX00");
		//		String tmp = "Status: "
		//				+ skquotelib.SKQuoteLib_RequestTicks(sbr_tick, "TX00");
		//		connectionlabel.setText(tmp);
		//	}

		//	public void widgetDefaultSelected(SelectionEvent event) {
		//		//String tmp = "Status: "
		//		//		+ skquotelib.SKQuoteLib_RequestStocks(sbr_tick, "TX00");
		//		String tmp = "Status: "
		//				+ skquotelib.SKQuoteLib_RequestTicks(sbr_tick, "TX00");
		//		connectionlabel.setText(tmp);
		//	}
		//});
		
		//button2.addSelectionListener(new SelectionListener() {

		//	public void widgetSelected(SelectionEvent event) {
		//		String tmp = "Status: "
		//				+ skquotelib.SKQuoteLib_GetKLine("TX00", 4);
		//		connectionlabel.setText(tmp);
		//	}

		//	public void widgetDefaultSelected(SelectionEvent event) {
		//		String tmp = "Status: "
		//				+ skquotelib.SKQuoteLib_GetKLine("TX00", 4);
		//		connectionlabel.setText(tmp);
		//	}
		//});
		//close.addSelectionListener(new SelectionListener() {

		//	public void widgetSelected(SelectionEvent event) {
		//		tg.clearMysql();
		//		String tmp = "Status: " + skquotelib.SKQuoteLib_LeaveMonitor();
		//		;
		//		connectionlabel.setText(tmp);
		//	}

		//	public void widgetDefaultSelected(SelectionEvent event) {
		//		String tmp = "Status: " + skquotelib.SKQuoteLib_LeaveMonitor();
		//		;
		//		connectionlabel.setText(tmp);
		//	}
		//});

		//shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		//display.dispose();
		
	}
}