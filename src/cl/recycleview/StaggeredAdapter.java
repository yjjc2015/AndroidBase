package cl.recycleview;

import java.util.ArrayList;
import java.util.List;

import cl.intentService.R;

import android.content.Context;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder> {
	
	private LayoutInflater mInflater;
	private List<String> mDatas;
	private List<Integer> mSize;
	private int orientation;
	private OnItemClickListener mOnItemClickListener;

	static class MyViewHolder extends RecyclerView.ViewHolder {
		TextView tv;
		public MyViewHolder(View itemView) {
			super(itemView);
			tv = (TextView) itemView.findViewById(R.id.item_tv);
		}
	}
	
	static interface OnItemClickListener {
		void onItemClick(View view, int position);
		void onItemLongClick(View view, int position);
	}
	
	public StaggeredAdapter (Context context , List<String> datas, int orientation){
		mInflater = LayoutInflater.from(context);
		mDatas = datas;
		this.orientation = orientation;
		mSize = new ArrayList<Integer>();
		for (int i = 0; i < mDatas.size(); i++) {
			mSize.add((int) (100+Math.random()*300));
		}
	}

	public void setmOnItemClickListener(OnItemClickListener listener) {
		this.mOnItemClickListener = listener;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = null;
		if (orientation == OrientationHelper.HORIZONTAL) {
			view = mInflater.inflate(R.layout.item_hor_single_textview, parent, false);
		}else {
			view = mInflater.inflate(R.layout.item_single_textview, parent, false);
		}
		MyViewHolder viewHolder = new MyViewHolder(view);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(final MyViewHolder holder, int position) {
		//改变了View的高度
		LayoutParams lp = holder.itemView.getLayoutParams();
		if (orientation == OrientationHelper.HORIZONTAL) {
			lp.width = mSize.get(position);
		} else {
			lp.height = mSize.get(position);
		}
		holder.itemView.setLayoutParams(lp);
		holder.tv.setText(mDatas.get(position));
		
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

	@Override
	public int getItemCount() {
		return mDatas.size();
	}
	
	public void addData(int pos, String data){
		mDatas.add(pos, data);
		notifyItemInserted(pos);
	}
	
	public void delData(int pos){
		mDatas.remove(pos);
		notifyItemRemoved(pos);
	}
}
