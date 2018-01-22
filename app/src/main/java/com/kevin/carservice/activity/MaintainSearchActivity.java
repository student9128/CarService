package com.kevin.carservice.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevin.carservice.R;
import com.kevin.carservice.adapter.CarMaintainSearchAdapter;
import com.kevin.carservice.base.BaseActivity;
import com.kevin.carservice.base.BaseObserver;
import com.kevin.carservice.bean.CarMaintainBean;
import com.kevin.carservice.constant.Constant;
import com.kevin.carservice.database.CarDao;
import com.kevin.carservice.http.AppRetrofit;
import com.kevin.carservice.utils.DeviceUtils;
import com.kevin.carservice.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/20.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class MaintainSearchActivity extends BaseActivity implements TextWatcher, CarMaintainSearchAdapter.OnRecyclerItemClickListener, View.OnClickListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.et_name_search)
    EditText etNameSearch;
    @BindView(R.id.ll_search_box)
    LinearLayout llSearchBox;
    @BindView(R.id.rv_recycler_view)
    RecyclerView rvRecyclerView;
    private CarDao carDao;
    private List<CarMaintainBean.CTNTBean> data = new ArrayList<>();
    private List<CarMaintainBean.CTNTBean> datax = new ArrayList<>();
    private List<CarMaintainBean.CTNTBean> searchData = new ArrayList<>();
    private CarMaintainSearchAdapter mAdapter;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_maintain_search;
    }

    @Override
    public void initView() {
        tvTitle.setText("车辆维修");
        tvFunction.setText("新增");
//        carDao = new CarDao();
        queryData(DeviceUtils.getIMEI(this));
    }

    @Override
    public void initData() {
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setDivider(R.drawable.bg_recycler_divider);
        rvRecyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new CarMaintainSearchAdapter(this, data);
        rvRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        etNameSearch.addTextChangedListener(this);
        mAdapter.setOnRecyclerViewItemClickListener(this);
        tvFunction.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

        String s = charSequence.toString();
//        List<CarMaintainBean.CTNTBean> strings = carDao.queryByKeyLetter(datax, s);
        if (datax.size() > 0 && s.length() > 0) {
            searchData.clear();
            for (int i = 0; i < datax.size(); i++) {
                String carNo = datax.get(i).getCarNo();
                if (carNo.contains(s)) {
                    searchData.add(datax.get(i));
                }
            }
            mAdapter.addData(searchData);
        } else {
            searchData.clear();
            mAdapter.addData(searchData);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
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
                            datax.clear();
                            datax.addAll(ctnt);
                        } else {
//                            showToast(carMaintainBean.getReturnMsg());
                        }
                    }
                });
    }

    @Override
    public void onRecyclerViewItemClick(int position) {
        startNewActivity(MaintainDetailActivity.class);
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
