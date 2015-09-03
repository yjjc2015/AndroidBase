package cl.intentService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IntentServiceActivity extends Activity implements OnClickListener {
	private Button startIntentService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_intentservice);
		startIntentService = (Button) this.findViewById(R.id.startIntentService);
		startIntentService.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.startIntentService:
				Log.i(getClass().getSimpleName(), "Main Thread is "+Thread.currentThread().getId());
				Intent intentService = new Intent(this, MyIntentService.class);
				startService(intentService);
				break;
		}
	}

}
