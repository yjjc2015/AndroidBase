package cl.alertdialog;

import cl.intentService.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AlertDialogActivity extends Activity {
	final String[] items = {"苹果", "梨", "橘子"};
	final String[] results = {"温性", "下火", "上火"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_alertdialog);
//		TextView tv = (TextView) this.findViewById(R.id.tv_alertDialog);
		LinearLayout layout = (LinearLayout) this.findViewById(R.id.layout_alertDialog);
		layout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog dialog = new AlertDialog.Builder(AlertDialogActivity.this).setTitle("请选择")
				.setSingleChoiceItems(items, -1, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String res = results[which];
						Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
						dialog.dismiss();
					}
				}).setCancelable(true).create();
				dialog.setCanceledOnTouchOutside(true);
				dialog.setOnCancelListener(new OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {
						Toast.makeText(getApplicationContext(), "dialog被取消了", Toast.LENGTH_SHORT).show();
					}
				});
				dialog.show();
			}
		});
	}

}
