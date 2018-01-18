package com.kevin.carservice.activity;

import android.view.View;

import com.kevin.carservice.R;
import com.kevin.carservice.base.BaseActivity;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/17.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class MaintainDetailActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public int setLayoutResId() {
        return R.layout.activity_maintain_detail;
    }

    @Override
    public void initView() {
        tvTitle.setText("详情");
        tvFunction.setText("新增");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        tvFunction.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_function:
                startNewActivity(MaintainAddActivity.class);
                break;
        }
    }
}
