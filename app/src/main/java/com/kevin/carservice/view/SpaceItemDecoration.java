package com.kevin.carservice.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kevin.carservice.adapter.PicViewAdapter;
import com.kevin.carservice.utils.DisplayUtils;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private Context context;
    private PicViewAdapter adapter;
    private int space;

    /**
     * @param space 传入的值，其单位视为dp
     */
    public SpaceItemDecoration(Context context, PicViewAdapter adapter, int space) {
        this.context = context;
        this.adapter = adapter;
        this.space = DisplayUtils.dip2px(context, space);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemCount = adapter.getItemCount();
        int pos = parent.getChildAdapterPosition(view);
//            Log.d(TAG, "itemCount>>" +itemCount + ";Position>>" + pos);

        outRect.left = 0;
        outRect.top = 0;
        outRect.bottom = 0;


        if (pos != (itemCount - 1)) {
            outRect.right = space;
        } else {
            outRect.right = 0;
        }
    }
}