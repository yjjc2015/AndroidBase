package cl.animation;

import cl.intentService.R;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class OldAnimationActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_oldanimation);
	}
	public void show(View view){
		Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
	}
	public void move(View view){
		//传统的动画效果：缺点：监听位置/属性都不变，仅仅改变了显示位置，耗费资源严重
//		TranslateAnimation animation = new TranslateAnimation(0, 200f, 0, 0);
//		animation.setDuration(1000);
//		animation.setFillAfter(true);//当动画结束时，会停留在动画结束的位置
		ImageView imageView = (ImageView) findViewById(R.id.imageView);
//		imageView.startAnimation(animation);
		//向右平移200px
//		ObjectAnimator.ofFloat(imageView, "translationX", 0f, 200f).setDuration(1000).start();
		//项下平移200px
//		ObjectAnimator.ofFloat(imageView, "y", 0f, 200f).setDuration(1000).start();
		//旋转360度（以z轴为中心，旋转）
//		ObjectAnimator.ofFloat(imageView, "rotation", 0f, 180f).setDuration(1000).start();
		
//		PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("translationX", 0f, 200f);
//		PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("y", 0f, 200f);
//		PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("rotation", 0f, 270f);
//		ObjectAnimator.ofPropertyValuesHolder(imageView, p1, p2, p3)
//						.setDuration(1000).start();
		
		ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 200f).setDuration(1000);
		ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView, "y", 0f, 200f).setDuration(2000);
		ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f).setDuration(1000);
		AnimatorSet set = new AnimatorSet();
//		set.playSequentially(animator1, animator2, animator3);//顺序执行
//		set.playTogether(animator1, animator2, animator3);//同时执行
		
		//先同时执行animator2和animator3
		set.play(animator2).with(animator3);
		//再执行animator1
		set.play(animator1).after(animator2);
//		set.setDuration(100);//这个属性一旦设置，所有子ObjectAnimator的时间设置不起作用
		set.start();
	}
}
