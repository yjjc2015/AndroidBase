package cl.httpclient;

import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import cl.intentService.R;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class HttpClientActivity extends Activity {
	private TextView tv;
	
	private Handler handler;
//	private final String myWebPageUrl = "http://baidu.com";
//	private final String myWebPageUrl = "http://192.168.100.7:8080/javaweb/";//tomcat
	private final String myWebPageUrl = "http://192.168.100.7:80/getdata.xml";//apache
//	private final String myWebPageUrl = "http://localhost/getdata.xml";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_httpclient);
		tv = (TextView) this.findViewById(R.id.tv_httpclient);
		handler = new MyHandler(tv, this);
		testLocalIpAndMac();
		new Thread(new Runnable() {
			@Override
			public void run() {
				String response = HttpHelper.doGet(myWebPageUrl);
				Log.i(getClass().getSimpleName(), "response = " + response);
				Message msg = new Message();
				msg.what = 0x001;
				msg.obj = response;
				handler.sendMessage(msg);
			}
		})
//		;
		.start();
	}
	
	/**
	 *	resolve the problem : "Handler Class Should be Static or Leaks Occur"
	 */
	private static class MyHandler extends Handler {
		private final WeakReference<TextView> mTv;
		private final WeakReference<Activity> mActivity;
		public MyHandler(TextView tv, HttpClientActivity activity){
			mTv = new WeakReference<TextView>(tv);
			mActivity = new WeakReference<Activity>(activity);
		}
		@Override
		public void handleMessage(Message msg) {
			if (mActivity.get() == null) {
				return;
			}
			switch (msg.what) {
			case 0x001:
				mTv.get().setText(msg.obj.toString());
				break;

			default:
				break;
			}
		}
	}
	
	private final String TAG = getClass().getSimpleName();
	public void testLocalIpAndMac(){
        Log.i(TAG, "IP: "+getLocalIpAddress()+", MAC: "+getLocalMacAddress());
    }

    /**
     * 获取Android本机IP地址
     * 
     * @return
     */
    private String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("WifiPreference IpAddress", ex.toString());
        }
        return null;
    }
    /**
     * 获取Android本机MAC
     * 
     * @return
     */
    private String getLocalMacAddress() {
        WifiManager wifi = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    } 

}
