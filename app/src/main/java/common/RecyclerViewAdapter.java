package common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ds.myuidemo.R;

import java.util.ArrayList;

/**
 * Created by ds on 2017/7/25.
 *
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter implements View.OnClickListener,View.OnLongClickListener{

    private Context mContext;
    private ArrayList<App> apps;

    public RecyclerViewAdapter(Context context, ArrayList<App> list){
        mContext = context;
        apps = list;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemLongClick(v);
        }
        return false;
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);
        void onItemLongClick(View view);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.app_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).tv.setBackgroundResource(apps.get(position).mImageId);
        ((MyViewHolder)holder).tv.setText(apps.get(position).mAppName);
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    TextView tv;

    public MyViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.app_name);
    }
}