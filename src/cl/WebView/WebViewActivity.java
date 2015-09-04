package cl.WebView;

import cl.intentService.R;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 *	This is main WebView Activity
 */
public class WebViewActivity extends Activity {
	private WebView webView;
//	private final String myWebPageUrl = "http://10.0.2.2:80/index.html";
	private final String myWebPageUrl = "http://192.168.100.7:80/index.html";

//	private final String myWebPageUrl = "http://192.168.100.7:80/getdata.xml";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_webview);
		webView = (WebView) this.findViewById(R.id.webView);
		
		//now almost every webpage load with javascript
		webView.getSettings().setJavaScriptEnabled(true);
		
		webView.addJavascriptInterface(new JavaScriptInterface(this), "Android");
		
//		webView.loadUrl("file:///android_asset/www/index.html");
		webView.loadUrl(myWebPageUrl);
		
		//add this,when you link a new webpage,it will be in webView
//		webView.setWebViewClient(new WebViewClient());
	}
	
}
