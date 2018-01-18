package com.kevin.carservice.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kevin.carservice.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/18.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class PicViewAdapter extends RecyclerView.Adapter<PicViewAdapter.MyViewHolder> {
    private List<Uri> mUris;
    private List<String> mPaths;
    private Context context;

    public PicViewAdapter(Context context) {
        this.context = context;
    }

    public PicViewAdapter(Context context, List<Uri> uris) {
        this.context = context;
        this.mUris = uris;
    }

    public void setData(List<Uri> uris) {
//        mUris = uris;
        mUris.addAll(uris);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pic_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context)
                .load(mUris.get(position))
                .placeholder(R.drawable.ic_placeholder)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUris == null ? 0 : mUris.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_image_1)
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
