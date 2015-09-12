package cl.animation;

import cl.alertdialog.DensityUtil;
import cl.intentService.R;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

public class ValueAndTypeAnimator extends Activity {
	private ImageView ivBall;
	private int mScreenWidth;
	private int mScreenHeight;
	private int mX;
	private int mY;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_valueandtypeanimation);
		ivBall = (ImageView) this.findViewById(R.id.id_ball);
		mScreenWidth = getResources().getDisplayMetrics().widthPixels;
		mScreenHeight = getResources().getDisplayMetrics().heightPixels;
		ivBall.post(new Runnable() {
			@Override
			public void run() {
				mX = mScreenWidth - ivBall.getWidth();
				mY = mScreenHeight - ivBall.getHeight() 
//						- DensityUtil.getStatusBarHeight(getApplicationContext());
						- DensityUtil.getStatusBarHeight2(ValueAndTypeAnimator.this);
			}
		});
	}
	/**
	 * 自由落体
	 */
	public void verticalRun(View view){
		ValueAnimator ani = ValueAnimator.ofFloat(0f, mY);
		ani.setTarget(view);
		ani.setDuration(1000);
		ani.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				ivBall.setTranslationY((Float) animation.getAnimatedValue());
			}
		});
		ani.start();
	}
	/**
	 * 抛物线
	 */
	public void parabolaRun(View view){
		ValueAnimator ani = new ValueAnimator();
		ani.setDuration(1000);
		ani.setObjectValues(new PointF(0, 0));
		ani.setInterpolator(new LinearInterpolator());
		ani.setEvaluator(new TypeEvaluator<PointF>() {
			@Override
			public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
				Log.e("TAG", fraction + "");
				//x方向的速度v=200px/s;y方向的加速度为0.5f,速度也是v
				PointF point = new PointF();
				point.x = mX * fraction;
				point.y = mY * fraction * fraction;
				return point;
			}
		});
		ani.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				PointF point = (PointF) animation.getAnimatedValue();
				ivBall.setX(point.x);
				ivBall.setY(point.y);
			}
		});
		ani.start();
	}
	/**
	 * 变向
	 */
	public void turnningRun(View view){
//		ObjectAnimator oa1 = ObjectAnimator.ofFloat(view, "rotate", );
	}
}
