package cl.WebView;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JavaScriptInterface {
	Context mContext;
	
	public JavaScriptInterface(Context c) {
		mContext = c;
	}			
	@JavascriptInterface
	public void showToast(String info){
		Toast.makeText(mContext, info, Toast.LENGTH_LONG).show();
	}
}
