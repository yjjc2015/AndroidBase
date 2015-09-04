package cl.httpclient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class HttpHelper {
	private static final String TAG = HttpHelper.class.getSimpleName();
	public static String doGet(String urlStr){
		HttpURLConnection conn = null;
		StringBuffer response = new StringBuffer();
		try{
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(2000);
			conn.setConnectTimeout(2000);
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			Log.e(TAG, "response = " + response.toString());
		}catch(Exception e){
			Log.e(TAG, e.getMessage(), e);
		}
		finally{
			if (conn != null) {
				conn.disconnect();
			}
		}
		return response.toString();
	}
}
