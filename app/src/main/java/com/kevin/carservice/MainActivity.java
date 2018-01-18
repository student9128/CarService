package com.kevin.carservice;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevin.carservice.activity.MaintainAddActivity;
import com.kevin.carservice.activity.MaintainDetailActivity;
import com.kevin.carservice.adapter.CarMaintainAdapter;
import com.kevin.carservice.base.BaseActivity;
import com.kevin.carservice.bean.CarMaintainBean;
import com.kevin.carservice.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements CarMaintainAdapter.OnRecyclerItemClickListener, View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tv_name_search)
    TextView tvNameSearch;
    @BindView(R.id.ll_search_box)
    LinearLayout llSearchBox;
    @BindView(R.id.rv_recycler_view)
    RecyclerView rvRecyclerView;
    private CarMaintainAdapter mAdapter;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        tvTitle.setText("车辆维修");
        tvFunction.setText("新增");
    }

    @Override
    public void initData() {
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setDivider(R.drawable.bg_recycler_divider);
        rvRecyclerView.addItemDecoration(dividerItemDecoration);
        CarMaintainBean carMaintanceBean = new CarMaintainBean();
        List<CarMaintainBean.InfoBean> data = new ArrayList<>();
        addData(data);
        mAdapter = new CarMaintainAdapter(this, data);
        rvRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void initListener() {
        mAdapter.setOnRecyclerViewItemClickListener(this);
        tvFunction.setOnClickListener(this);
    }

    @Override
    public void onRecyclerViewItemClick(int position) {
        startNewActivity(MaintainDetailActivity.class);
    }

    private void addData(List<CarMaintainBean.InfoBean> data) {
        CarMaintainBean.InfoBean x = new CarMaintainBean.InfoBean();
        x.setLicence("苏AC55S3");
        x.setStatus("已发送");
        x.setTime("2018-01-16 14:58:52");
        data.add(x);
        CarMaintainBean.InfoBean x1 = new CarMaintainBean.InfoBean();
        x1.setLicence("苏AT6676");
        x1.setStatus("已发送");
        x1.setTime("2018-01-15 19:53:32");
        data.add(x1);
        CarMaintainBean.InfoBean x2 = new CarMaintainBean.InfoBean();
        x2.setLicence("苏AC42S3");
        x2.setStatus("已完成");
        x2.setTime("2018-01-15 16:06:02");
        data.add(x2);
        CarMaintainBean.InfoBean x3 = new CarMaintainBean.InfoBean();
        x3.setLicence("苏A985S3");
        x3.setStatus("检查中");
        x3.setTime("2018-01-14 18:12:24");
        data.add(x3);
        CarMaintainBean.InfoBean x4 = new CarMaintainBean.InfoBean();
        x4.setLicence("沪AM56S3");
        x4.setStatus("已发送");
        x4.setTime("2018-01-11 11:58:09");
        data.add(x4);
        CarMaintainBean.InfoBean x5 = new CarMaintainBean.InfoBean();
        x5.setLicence("京A95699");
        x5.setStatus("检查中");
        x5.setTime("2018-01-11 14:09:09");
        data.add(x5);
        CarMaintainBean.InfoBean x6 = new CarMaintainBean.InfoBean();
        x6.setLicence("沪A00023");
        x6.setStatus("已完成");
        x6.setTime("2018-01-10 11:58:09");
        data.add(x6);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_function:
                startNewActivity(MaintainAddActivity.class);
                break;
        }
    }
}
