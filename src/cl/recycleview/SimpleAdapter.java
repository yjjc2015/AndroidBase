package cl.recycleview;

import java.util.List;
import cl.intentService.R;
import android.content.Context;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.MyViewHolder> {
	private LayoutInflater mInflater;
	private List<App> mAppList;
	private int orientation;
	private OnItemClickListener mOnItemClickListener;
	
	public SimpleAdapter (Context context, List<App> appList, int orientation) {
		this.mAppList = appList;
		this.mInflater = LayoutInflater.from(context);
		this.orientation = orientation;
	}

	public void setmOnItemClickListener(OnItemClickListener listener) {
		this.mOnItemClickListener = listener;
	}

	@Override
	public int getItemCount() {
		return mAppList.size();
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = null;
		if (orientation == OrientationHelper.HORIZONTAL) {
			view = mInflater.inflate(R.layout.item_hor_single_textview, parent, false);
		}else{
			view = mInflater.inflate(R.layout.item_single_textview, parent, false);
		}
		MyViewHolder viewHolder = new MyViewHolder(view);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(final MyViewHolder holder, int position) {
		holder.tv.setText(mAppList.get(position).getName());
		holder.iv.setImageDrawable(mAppList.get(position).getIcon());
		setUpItemEvent(holder);
	}
	
	protected void setUpItemEvent(final MyViewHolder holder){
		if (mOnItemClickListener != null) {
			holder.itemView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mOnItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
				}
			});
			holder.itemView.setOnLongClickListener(new OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					mOnItemClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
					return false;
				}
			});
		}
	}
	
	public void addData(int pos, App app){
		mAppList.add(pos, app);
		notifyItemInserted(pos);//告诉系统：添加了一个Item
	}
	public void delData(int pos){
		mAppList.remove(pos);
		notifyItemRemoved(pos);
	}

	static class MyViewHolder extends ViewHolder {
		TextView tv;
		ImageView iv;
		public MyViewHolder(View itemView) {
			super(itemView);
			tv = (TextView) itemView.findViewById(R.id.item_tv);
			iv = (ImageView) itemView.findViewById(R.id.item_iv);
		}
	}
	
	//Item点击事件
	static interface OnItemClickListener {
		void onItemClick(View view, int position);
		void onItemLongClick(View view, int position);
	}
}
