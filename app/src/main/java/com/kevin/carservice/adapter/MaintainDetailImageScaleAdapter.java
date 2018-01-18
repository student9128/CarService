package com.kevin.carservice.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.kevin.carservice.R;

import java.util.List;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/18.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class MaintainDetailImageScaleAdapter extends PagerAdapter {
    private Context context;
    private List<String> uris;

    public MaintainDetailImageScaleAdapter(Context context, List<String> uris) {
        this.context = context;
        this.uris = uris;
    }

    @Override
    public int getCount() {
        return uris.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {

        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.adapter_maintain_detail_image_scale, null);

        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.pv_photo_view);
        Glide.with(context)
                .load(uris.get(position))
                .into(imageView);
        container.addView(linearLayout);
        int childCount = container.getChildCount();
        return linearLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        LinearLayout view = (LinearLayout) object;
        container.removeView(view);
    }
}
