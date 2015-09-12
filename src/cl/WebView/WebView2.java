package cl.WebView;

import cl.intentService.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WebView2 extends Activity implements OnClickListener {
	private WebView wv;
	private TextView tv;
	private Button btnFresh;
	private Button btnBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_webview2);
		wv = (WebView) this.findViewById(R.id.webView2);
		tv = (TextView) this.findViewById(R.id.tv_wv2);
		btnFresh = (Button) this.findViewById(R.id.btn_refresh);
		btnBack = (Button) this.findViewById(R.id.btn_back);
		btnFresh.setOnClickListener(this);
		btnBack.setOnClickListener(this);
		/*
		 * 拦截webView中所有的下载动作，自己进行处理，下载到本地
		 */
		wv.setDownloadListener(new DownloadListener() {
			@Override
			public void onDownloadStart(String url, String userAgent,
					String contentDisposition, String mimetype, long contentLength) {
				Toast.makeText(getApplicationContext(), "url = " + url + " ," +
						"mimetype = " + mimetype + ", contentLength = " + 
						contentLength, Toast.LENGTH_SHORT).show();
				Log.i("TAG", "url = " + url + " ," +
						"mimetype = " + mimetype + ", contentLength = " + 
						contentLength);
			}
		});
		wv.loadUrl("http://www.baidu.com");
		wv.setWebChromeClient(new WebChromeClient(){
			//得到当前网址的标题
			@Override
			public void onReceivedTitle(WebView view, String title) {
				super.onReceivedTitle(view, title);
				tv.setText(title);
			}
		});
		//使得每次加载网址，都在WebView中
		wv.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.btn_refresh:
				wv.reload();//刷新webView
				break;
			case R.id.btn_back:
				finish();
				break;
			default:
				break;
		}
	}

}
