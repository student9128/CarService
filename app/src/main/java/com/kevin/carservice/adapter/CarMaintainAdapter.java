package com.kevin.carservice.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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


public class CarMaintainAdapter extends RecyclerView.Adapter<CarMaintainAdapter.MyViewHolder> {

    private Context context;
    List<CarMaintainBean.InfoBean> data;

    public CarMaintainAdapter(Context context, List<CarMaintainBean.InfoBean> data) {
        this.context = context;
        this.data = data;
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
        CarMaintainBean.InfoBean infoBean = data.get(position);
        holder.tvLicencePlate.setText(infoBean.getLicence());
        String status = infoBean.getStatus();
        if ("已发送".equals(status)) {
            holder.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.green_1));
        } else if ("已完成".equals(status)) {
            holder.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.blue_2));

        } else if ("检查中".equals(status)) {
            holder.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.yellow));

        }
        holder.tvStatus.setText(status);
        holder.tvTime.setText(infoBean.getTime());
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
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_licence_plate)
        TextView tvLicencePlate;
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
