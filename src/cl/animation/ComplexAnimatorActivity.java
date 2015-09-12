package cl.animation;

import java.util.ArrayList;
import java.util.List;

import cl.intentService.R;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

public class ComplexAnimatorActivity extends Activity implements OnClickListener {
	private int[] res = {R.id.imageView_0, R.id.imageView_1,
			R.id.imageView_2, R.id.imageView_3,
			R.id.imageView_4, R.id.imageView_5,
			R.id.imageView_6, R.id.imageView_7,
			R.id.imageView_8, R.id.imageView_9};
	private ImageView[] ivs = new ImageView[10];
	private boolean flag = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_complexanimator);
		for(int i=0; i<res.length; i++){
			ImageView iv = (ImageView) findViewById(res[i]);
			iv.setOnClickListener(this);
			ivs[i] = iv;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.imageView_0:
				if (flag) {
					expandViews();
					flag = false;
				}else {
					closeViews();
					flag = true;
				}
				break;
			case R.id.imageView_1:
				Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
				break;
			case R.id.imageView_2:
				Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
				break;
			case R.id.imageView_3:
				Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
				break;
			case R.id.imageView_4:
				Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
				break;
			case R.id.imageView_5:
				Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();
				break;
			case R.id.imageView_6:
				Toast.makeText(this, "6", Toast.LENGTH_SHORT).show();
				break;
			case R.id.imageView_7:
				Toast.makeText(this, "7", Toast.LENGTH_SHORT).show();
				break;
			case R.id.imageView_8:
				Toast.makeText(this, "8", Toast.LENGTH_SHORT).show();
				break;
			case R.id.imageView_9:
				Toast.makeText(this, "9", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
	}
	
	//收缩所有的View
	private void closeViews() {
		for(int i=0; i<res.length; i++){
			ObjectAnimator animator = ObjectAnimator.ofFloat(ivs[i], "translationY", 100*i, 0);
			animator.setDuration(500);
			animator.setStartDelay(i * 200);
//			if (i % 2 == 1) {
//				animator.setInterpolator(new OvershootInterpolator(10));
//			} else {
//				animator.setInterpolator(new BounceInterpolator());
//			}
			animator.setInterpolator(new BounceInterpolator());
			animator.start();
		}
	}

	//展开所有的View
	public void expandViews(){
		for(int i=1; i<res.length; i++){
			ObjectAnimator animator = ObjectAnimator.ofFloat(ivs[i], "translationY", 0, 100*i);
			animator.setDuration(500);
			animator.setStartDelay(i * 300);
//			if (i % 2 == 0) {
//				animator.setInterpolator(new OvershootInterpolator(10));
//			} else {
//				animator.setInterpolator(new BounceInterpolator());
//			}
			animator.setInterpolator(new BounceInterpolator());
			animator.start();
		}
	}
}
