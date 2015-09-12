package cl.alertdialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import cl.intentService.R;
import android.app.Activity;
import android.app.Service;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class TimePickerDialogActivity extends Activity {
	TextView tv;
	Button btn;
	
	Calendar mCalendar;
	private SimpleDateFormat fullFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
	
	private ImageView mDragView;
	private WindowManager mWindowManager;
	private WindowManager.LayoutParams mWindowLayoutParams;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_timepickerdialog);
		btn = (Button) this.findViewById(R.id.id_time);
		tv = (TextView) this.findViewById(R.id.id_tv);
		
		mCalendar = Calendar.getInstance();
		
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showTime();
			}
		});
		
		//管理悬浮窗的管理器
		mWindowManager = (WindowManager) this.getSystemService(Service.WINDOW_SERVICE); 
	}
	public void showTime(){
		TimePickerDialog dialog = new TimePickerDialog(this, new OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
				mCalendar.set(Calendar.MINUTE, minute);
				String time = fullFormat.format(mCalendar.getTime());
				tv.setText(time);
			}
		}, mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), true);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}
	//创建一个悬浮框和设置
	public void CreateDragView(){
		//得到3个像素值:正方形view的尺寸，屏幕的宽高
		int rectWidth = DensityUtil.dp2px(this, 50);
		int screenWidth = getResources().getDisplayMetrics().widthPixels;
		int screenHeight = getResources().getDisplayMetrics().heightPixels;
		
		//创建并设置WindowLayoutParams
		mWindowLayoutParams = new WindowManager.LayoutParams();
		mWindowLayoutParams.format = PixelFormat.TRANSLUCENT;//	图片之外的其他地方透明
		//位置
		mWindowLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;//	左上
		mWindowLayoutParams.x = screenWidth - rectWidth;//	x轴起始位置
		mWindowLayoutParams.y = screenHeight - 2 * rectWidth;//	y轴起始位置
		
		mWindowLayoutParams.alpha = 0.55f;//	透明度
		mWindowLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL|
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;//	不能触摸和获取焦点
		mWindowLayoutParams.width = rectWidth;	//宽50dp
		mWindowLayoutParams.height = rectWidth;// 高50dp
		
		//创建并设置ImageView
		mDragView = new ImageView(getApplicationContext());
		mDragView.setImageResource(R.drawable.ic_iwork_addnote);
		mDragView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "点中了悬浮窗", Toast.LENGTH_SHORT).show();
			}
		});
	}
	//将悬浮窗添加到窗口中
	public void addDragView(){
		if (mDragView == null) {
			CreateDragView();
			//将ImageView根据设置好的属性添加到窗口中
			mWindowManager.addView(mDragView, mWindowLayoutParams);
		}
	}
	//从界面移除悬浮窗
	public void removeDragView(){
		if (mDragView != null) {
			mWindowManager.removeView(mDragView);
			mDragView = null;
			mWindowLayoutParams = null;
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
		addDragView();
	}
	@Override
	protected void onPause() {
		super.onPause();
		removeDragView();
	}
}
