package cl.WebView;

import cl.intentService.R;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 *	WebView.loadData()
 */
public class WebView1 extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview1);
		final String mimeType = "text/html";
		final String encoding = "utf-8";
		WebView wv = (WebView) this.findViewById(R.id.wv1);
		wv.loadData("<a href='x'>Hello world!-1</a>", mimeType, encoding);
		wv = (WebView) this.findViewById(R.id.wv2);
		wv.loadData("<a href='x'>Hello world!-2</a>", mimeType, encoding);
		wv = (WebView) this.findViewById(R.id.wv3);
		wv.loadData("<a href='x'>Hello world!-3</a>", mimeType, encoding);
	}
	
}
