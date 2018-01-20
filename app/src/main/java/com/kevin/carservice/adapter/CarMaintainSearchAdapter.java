package com.kevin.carservice.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevin.carservice.R;
import com.kevin.carservice.bean.CarMaintainBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/17.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class CarMaintainSearchAdapter extends RecyclerView.Adapter<CarMaintainSearchAdapter.MyViewHolder> {

    private Context context;
    List<CarMaintainBean.CTNTBean> data;

    public CarMaintainSearchAdapter(Context context, List<CarMaintainBean.CTNTBean> data) {
        this.context = context;
        this.data = data;
    }

    public void addData(List<CarMaintainBean.CTNTBean> d) {
        this.data.clear();
        this.data.addAll(d);
        notifyDataSetChanged();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_item_recycler_car_maintain, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        CarMaintainBean.CTNTBean ctntBean = data.get(position);
        holder.tvCarNo.setText(ctntBean.getCarNo());
        holder.tvStatus.setText(ctntBean.getZt());
        String color = ctntBean.getColor();
        String[] strings = color.split(",");
        int r = Integer.parseInt(strings[0]);
        int g = Integer.parseInt(strings[1]);
        int b = Integer.parseInt(strings[2]);
        holder.tvStatus.setTextColor(Color.rgb(r, g, b));
        holder.tvTime.setText(ctntBean.getCzsj());
        holder.llMaintainItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onRecyclerViewItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_car_number)
        TextView tvCarNo;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_time)
        TextView tvTime;

        @BindView(R.id.ll_maintain_item)
        LinearLayout llMaintainItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerItemClickListener l) {
        this.listener = l;
    }

    private OnRecyclerItemClickListener listener;

    public interface OnRecyclerItemClickListener {
        void onRecyclerViewItemClick(int position);
    }
}
