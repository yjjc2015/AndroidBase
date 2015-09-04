package cl.intentService;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
/**
 * theory：
 * IntentService will start a child thread automatically，
 * and it's onHandleIntent method is on the child thread，
 * when executed it will be destroyed。
 * 
 * use:
 * to complete a internet's request or a time-consuming thing, 
 * then use it can create child thread easily,
 * because use it we don't consider about when to destroy the service and how to create a child thread.
 */
public class MyIntentService extends IntentService {

	public MyIntentService() {
		super("MyIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(getClass().getSimpleName(), "Thread is is "+Thread.currentThread().getId());
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(getClass().getSimpleName(), "onCreate executting");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(getClass().getSimpleName(), "onDestroy executed");
	}

}
