package com.kevin.carservice;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevin.carservice.activity.MaintainAddActivity;
import com.kevin.carservice.activity.MaintainDetailActivity;
import com.kevin.carservice.activity.MaintainSearchActivity;
import com.kevin.carservice.adapter.CarMaintainAdapter;
import com.kevin.carservice.base.BaseActivity;
import com.kevin.carservice.base.BaseObserver;
import com.kevin.carservice.bean.CarMaintainBean;
import com.kevin.carservice.constant.Constant;
import com.kevin.carservice.database.CarDao;
import com.kevin.carservice.http.AppRetrofit;
import com.kevin.carservice.view.DividerItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements CarMaintainAdapter.OnRecyclerItemClickListener, View.OnClickListener, OnRefreshListener {

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
    @BindView(R.id.refresh_header)
    ClassicsHeader refreshHeader;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefresh;
    private CarMaintainAdapter mAdapter;
    private List<CarMaintainBean.CTNTBean> data = new ArrayList<>();
    private CarDao carDao;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        tvTitle.setText("车辆维修");
        tvFunction.setText("新增");
        carDao = new CarDao();
//        carDao.deleteDatabase();
    }

    @Override
    public void initData() {
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setDivider(R.drawable.bg_recycler_divider);
        rvRecyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new CarMaintainAdapter(this, data);
        rvRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void initListener() {
        llSearchBox.setOnClickListener(this);
        mAdapter.setOnRecyclerViewItemClickListener(this);
        tvFunction.setOnClickListener(this);
        smartRefresh.setOnRefreshListener(this);
//        queryData(DeviceUtils.getIMEI(this));
        queryData("864394010980110");
    }

    @Override
    public void onRecyclerViewItemClick(int position) {
        startNewActivity(MaintainDetailActivity.class);
    }

    private void queryData(final String sn) {
        Observable<CarMaintainBean> observable = AppRetrofit.getInstance().getHttpService().queryData(sn);
        observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<CarMaintainBean>() {
                    @Override
                    public void onNext(CarMaintainBean carMaintainBean) {
                        String state = carMaintainBean.getState();
                        printLogd(state);
                        if (Constant.STATE_SUCCESS.equals(state)) {
                            List<CarMaintainBean.CTNTBean> ctnt = carMaintainBean.getCTNT();
                            mAdapter.addData(ctnt);
//                            insertDB(ctnt);
                        } else {
                            showToast(carMaintainBean.getReturnMsg());
                        }
                        finishRefresh();
                    }
                });
    }

    private void insertDB(List<CarMaintainBean.CTNTBean> d) {
        carDao.deleteDatabase();
        for (int i = 0; i < d.size(); i++) {
            String carNo = d.get(i).getCarNo();
            String time = d.get(i).getCzsj();
            String status = d.get(i).getZt();
            String color = d.get(i).getColor();
            carDao.insert(carNo, status, time, color);
        }
//        CarDataHelper instance = new CarDataHelper(this);
//        SQLiteDatabase writableDatabase = instance.getWritableDatabase();
//        String x = "7";
//        String sql = "select * from carTable where carNumber like '%" + x + "%'";
//        Cursor cursor = writableDatabase.rawQuery(sql, null);
//        cursor.moveToFirst();
//        while (cursor.moveToNext()) {
//            String carNumber = cursor.getString(cursor.getColumnIndex(CarTable.COLUMN_CAR_NUMBER));
//            String status = cursor.getString(cursor.getColumnIndex(CarTable.COLUMN_CAR_STATUS));
//            String createTime = cursor.getString(cursor.getColumnIndex(CarTable.COLUMN_CAR_TIME));
//            String statusColor = cursor.getString(cursor.getColumnIndex(CarTable.COLUMN_STATUS_COLOR));
//            LogK.d(TAG, "carNumber:\t" + carNumber);
//            LogK.d(TAG, "status:\t" + status);
//            LogK.d(TAG, "createTime:\t" + createTime);
//            LogK.d(TAG, "statusColor:\t" + statusColor);
//        }
    }

    private void finishRefresh() {
        if (smartRefresh.isRefreshing()) {
            smartRefresh.finishRefresh();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_function:
                startNewActivity(MaintainAddActivity.class);
                break;
            case R.id.ll_search_box:
                startNewActivity(MaintainSearchActivity.class);
                break;
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
//        queryData(DeviceUtils.getIMEI(this));
        queryData("864394010980110");
    }
}
