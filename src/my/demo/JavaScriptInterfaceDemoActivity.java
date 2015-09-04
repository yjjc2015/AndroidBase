package my.demo;

import cl.intentService.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class JavaScriptInterfaceDemoActivity extends Activity {
	private WebView Wv;
	private TextView myTextView;	
	final Handler myHandler = new Handler();
	private final String myWebPageUrl = "http://192.168.100.7:80/index.html";
       
	
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.main);
        Wv = (WebView)findViewById(R.id.webView1);
        myTextView = (TextView)findViewById(R.id.textView1);        
        final JavaScriptInterface myJavaScriptInterface
     	= new JavaScriptInterface(this);    	 
    	 
        Wv.getSettings().setJavaScriptEnabled(true);
        Wv.addJavascriptInterface(myJavaScriptInterface, "Android");
//        Wv.loadUrl("file:///android_asset/www/index.html"); 
        Wv.loadUrl(myWebPageUrl); 
    }
    
    public class JavaScriptInterface {
		Context mContext;

	    JavaScriptInterface(Context c) {
	        mContext = c;
	    }
	    
	    public void showToast(String webMessage){	    	
	       Toast.makeText(mContext, webMessage, Toast.LENGTH_SHORT).show();
	    }
    }
}