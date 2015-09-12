package cl.animation;

import cl.intentService.R;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ObjectAnimatorActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_objectanimator);
	}
	
	public void doAnimRun(final View view){
		//1.最简单的一种类型的属性动画:旋转360度
//		ObjectAnimator.ofFloat(view, "rotationX", 0f, 360F)
//		.setDuration(1000).start();
		
		//2.用一个ObjectAnimator对象实现2种重叠的动画效果：缩放和透明度
//		ObjectAnimator anim = ObjectAnimator
//				.ofFloat(view, "chenlong", 0F, 1f)
//				.setDuration(1000);
//		anim.addUpdateListener(new AnimatorUpdateListener() {
//			@Override
//			public void onAnimationUpdate(ValueAnimator animation) {
//				float val = (Float) animation.getAnimatedValue();
//				//0~1：完全透明~不透明
//				view.setAlpha(val);
//				//0~5倍放大
//				view.setScaleX(val * 5);
//				view.setScaleY(val * 5);
//			}
//		});
//		anim.start();
		
		/**
		 * 3.实现一个动画更改多个效果：使用propertyValuesHolder
		 * 1秒内
		 * 透明度：不透明-透明-不透明
		 * 缩放：水平和竖直缩放效果一样：1倍--0--1倍--5倍
		 */
//		PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
//		PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f, 0f, 5f);
//		PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f, 0f, 5f);
//		ObjectAnimator.ofPropertyValuesHolder(view, pvh1, pvh2, pvh3)
//		.setDuration(3000).start();
		
		//4.透明度从0~1,对属性进行监听
//		ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 0F, 1F);
//		animator.setDuration(5000);
//		animator.addListener(new AnimatorListener() {
//			@Override
//			public void onAnimationStart(Animator animation) {
//				Toast.makeText(getApplicationContext(), "动画开始了", Toast.LENGTH_SHORT).show();
//			}
//			@Override
//			public void onAnimationRepeat(Animator animation) {
//				
//			}
//			@Override
//			public void onAnimationEnd(Animator animation) {
//				Toast.makeText(getApplicationContext(), "动画结束了", Toast.LENGTH_SHORT).show();
//			}
//			@Override
//			public void onAnimationCancel(Animator animation) {
//				
//			}
//		});
//		animator.addListener(new AnimatorListenerAdapter() {
//			@Override
//			public void onAnimationEnd(Animator animation) {
//				Toast.makeText(getApplicationContext(), "动画结束了", Toast.LENGTH_SHORT).show();
//			}
//		});
//		animator.start();
	}
	public void doTextRun(View view){
		Toast.makeText(this, "进来了", Toast.LENGTH_SHORT).show();
		final TextView tv = (TextView) view;
		//6.ValueAnimator和AnimatorUpdateListener监听
		ValueAnimator animator = ValueAnimator.ofInt(0, 100);
		animator.setDuration(10000);
		animator.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				Integer val = (Integer) animation.getAnimatedValue();
				tv.setText(val.toString());
			}
		});
		animator.setEvaluator(new TypeEvaluator<Integer>() {
			@Override
			public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
				//加速
//				int res = (int) (startValue + (endValue - startValue) * fraction * fraction);
				//减速
//				float rate = 1 - (1 - fraction) * (1 - fraction);
//				int res = (int) (startValue + (endValue - startValue) * rate); 
				//先加速，后减速
				if (fraction <= 0.5f) {
					float rate = fraction * fraction * 4;
					return (int) (startValue + (endValue - startValue) * rate);
				} else {
					float rate = 1 - (1 - fraction) * (1 - fraction) * 4;
					float centerValue = (endValue - startValue)/2.0f;
					int res =  (int) (centerValue + (endValue - centerValue)*rate);
					return res;
				}
			}
		});
		animator.start();
	}
}
