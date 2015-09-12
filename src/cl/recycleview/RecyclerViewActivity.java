package cl.recycleview;

import java.util.ArrayList;
import java.util.List;
import cl.intentService.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class RecyclerViewActivity extends Activity implements OnClickListener {
	RecyclerView mRecyclerView;
	Button btnAdd;
	Button btnDel;
	
	private List<App> mAppList;
	private SimpleAdapter adapter;
//	private StaggeredAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_recycleview);
		
		initDatas();
		initViews();
	}

	private void initDatas() {
		//init datas
//		mDatas = new ArrayList<String>();
//		for (int i = 'A'; i <= 'z'; i++) {
//			mDatas.add("" + (char)i);
//		}
		mAppList = AppHelper.getLauncherApps(this.getPackageManager());
	}

	private void initViews() {
		btnAdd = (Button) this.findViewById(R.id.btnAdd);
		btnDel = (Button) this.findViewById(R.id.btnDel);
		mRecyclerView = (RecyclerView) this.findViewById(R.id.recycleView);
		btnAdd.setOnClickListener(this);
		btnDel.setOnClickListener(this);
		
		//边界回弹效果
		mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);//消除边界回弹效果
//		mRecyclerView.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);//当内容长度等于/超过View的长度，才有这个效果
//		mRecyclerView.setOverScrollMode(View.OVER_SCROLL_ALWAYS);//缺省值，一直都有这个效果
		
		mRecyclerView.setFadingEdgeLength(0);
		
		LayoutManager layoutManager = null;
//		一、ListView
		//1.水平
//		adapter = new SimpleAdapter(this, mDatas, OrientationHelper.HORIZONTAL);
		//设置RecycleView的布局管理器
//		layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
		//2.垂直
		adapter = new SimpleAdapter(this, mAppList, OrientationHelper.VERTICAL);
		layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		
//		二、GridView
		//1.水平
//		adapter = new SimpleAdapter(this, mDatas, OrientationHelper.HORIZONTAL);
//		layoutManager = new GridLayoutManager(this, 3, OrientationHelper.HORIZONTAL, false);
		//2.垂直
//		adapter = new SimpleAdapter(this, mDatas, OrientationHelper.VERTICAL);
//		layoutManager = new GridLayoutManager(this, 3, OrientationHelper.VERTICAL, false);
		
//		三、瀑布流
		//水平
//		adapter = new StaggeredAdapter(this, mAppList, OrientationHelper.HORIZONTAL);
//		layoutManager = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL);
		//垂直
//		adapter = new StaggeredAdapter(this, mAppList, OrientationHelper.VERTICAL);
//		layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

		
//		四、配置适配器并设置布局管理器
		mRecyclerView.setAdapter(adapter);
		mRecyclerView.setLayoutManager(layoutManager);
		
//		五、最后，可以设置效果：动画和自定义分割线
		//设置动画效果
//		mRecyclerView.setItemAnimator(new DefaultItemAnimator());//默认的动画效果，所以加不加都没变
		//设置RecycleView的Item间的分隔线
//		mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
		
//		六、监听Item点击和长按事件
		adapter.setmOnItemClickListener(new SimpleAdapter.OnItemClickListener() {
			@Override
			public void onItemLongClick(View view, int position) {
				Toast.makeText(RecyclerViewActivity.this, "长按了"+position, Toast.LENGTH_SHORT).show();
				startActivity(mAppList.get(position).getIntent());
			}
			@Override
			public void onItemClick(View view, int position) {
				Toast.makeText(RecyclerViewActivity.this, "点击了"+mAppList.get(position).getName(), Toast.LENGTH_SHORT).show();
			}
		});
//		adapter.setmOnItemClickListener(new StaggeredAdapter.OnItemClickListener() {
//			@Override
//			public void onItemLongClick(View view, int position) {
//				Toast.makeText(RecyclerViewActivity.this, "长按了"+position, Toast.LENGTH_SHORT).show();
//				startActivity(mAppList.get(position).getIntent());
//			}
//			@Override
//			public void onItemClick(View view, int position) {
//				Toast.makeText(RecyclerViewActivity.this, "点击了"+mAppList.get(position).getName(), Toast.LENGTH_SHORT).show();
//			}
//		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAdd:
			adapter.addData(mAppList.size()-1, mAppList.get(0));
			break;
		case R.id.btnDel:
			adapter.delData(3);
			break;
		default:
			break;
		}
	}
}
