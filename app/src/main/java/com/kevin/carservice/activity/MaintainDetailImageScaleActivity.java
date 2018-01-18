package com.kevin.carservice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.kevin.carservice.R;
import com.kevin.carservice.adapter.MaintainDetailImageScaleAdapter;
import com.kevin.carservice.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/18.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class MaintainDetailImageScaleActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.vp_view_pager)
    ViewPager vpViewPager;
    private MaintainDetailImageScaleAdapter mAdapter;
    private ArrayList<String> uris;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_maintain_detail_image_scale;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String position = bundle.getString("position");
        uris = bundle.getStringArrayList("uris");
        actionBar.setTitle((Integer.valueOf(position) + 1) + "/" + uris.size());
        mAdapter = new MaintainDetailImageScaleAdapter(this, uris);
        vpViewPager.setAdapter(mAdapter);
        vpViewPager.setCurrentItem(Integer.valueOf(position));
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        vpViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        actionBar.setTitle((position + 1) + "/" + uris.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
