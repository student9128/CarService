package com.kevin.carservice.activity;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kevin.carservice.R;
import com.kevin.carservice.RequestCode;
import com.kevin.carservice.adapter.PicViewAdapter;
import com.kevin.carservice.base.BaseActivity;
import com.kevin.carservice.base.BaseObserver;
import com.kevin.carservice.bean.MaintainAddSaveImageBean;
import com.kevin.carservice.constant.Constant;
import com.kevin.carservice.http.AppRetrofit;
import com.kevin.carservice.utils.DisplayUtils;
import com.kevin.carservice.utils.UuidUtils;
import com.kevin.carservice.view.another.SpaceItemDecoration;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/17.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class MaintainAddActivity extends BaseActivity implements View.OnClickListener, PicViewAdapter.OnRecyclerItemClickListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_plate_number)
    EditText tvPlateNumber;
    @BindView(R.id.tv_phone_number)
    EditText tvPhoneNumber;
    @BindView(R.id.tv_car_type)
    EditText tvCarType;
    @BindView(R.id.tv_service_specialist)
    EditText tvServiceSpecialist;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.iv_add_drivers_license)
    ImageView ivAddDriversLicense;
    @BindView(R.id.et_driver_license_comments)
    EditText etDriverLicenseComments;
    @BindView(R.id.iv_add_driving_license)
    ImageView ivAddDrivingLicense;
    @BindView(R.id.et_driving_license_comments)
    EditText etDrivingLicenseComments;
    @BindView(R.id.iv_add_light)
    ImageView ivAddLight;
    @BindView(R.id.et_light)
    EditText etLight;
    @BindView(R.id.iv_add_wiper_blade)
    ImageView ivAddWiperBlade;
    @BindView(R.id.et_wiper_blade)
    EditText etWiperBlade;
    @BindView(R.id.iv_add_storage_battery)
    ImageView ivAddStorageBattery;
    @BindView(R.id.et_storage_battery)
    EditText etStorageBattery;
    @BindView(R.id.iv_add_cooling_liquid)
    ImageView ivAddCoolingLiquid;
    @BindView(R.id.et_cooling_liquid)
    EditText etCoolingLiquid;
    @BindView(R.id.iv_add_power_fluid)
    ImageView ivAddPowerFluid;
    @BindView(R.id.et_power_fluid)
    EditText etPowerFluid;
    @BindView(R.id.iv_add_air_filter)
    ImageView ivAddAirFilter;
    @BindView(R.id.et_air_filter)
    EditText etAirFilter;
    @BindView(R.id.iv_add_cabin_filter)
    ImageView ivAddCabinFilter;
    @BindView(R.id.et_cabin_filter)
    EditText etCabinFilter;
    @BindView(R.id.iv_add_air_damper)
    ImageView ivAddAirDamper;
    @BindView(R.id.et_air_damper)
    EditText etAirDamper;
    @BindView(R.id.iv_add_engine)
    ImageView ivAddEngine;
    @BindView(R.id.et_engine)
    EditText etEngine;
    @BindView(R.id.iv_add_left_front)
    ImageView ivAddLeftFront;
    @BindView(R.id.iv_add_left_rear)
    ImageView ivAddLeftRear;
    @BindView(R.id.iv_add_right_front)
    ImageView ivAddRightFront;
    @BindView(R.id.iv_add_right_rear)
    ImageView ivAddRightRear;
    @BindView(R.id.et_thread_depth)
    EditText etThreadDepth;
    @BindView(R.id.iv_add_left_front_brake_lining)
    ImageView ivAddLeftFrontBrakeLining;
    @BindView(R.id.iv_add_left_rear_brake_lining)
    ImageView ivAddLeftRearBrakeLining;
    @BindView(R.id.iv_add_right_front_brake_lining)
    ImageView ivAddRightFrontBrakeLining;
    @BindView(R.id.iv_add_right_rear_brake_lining)
    ImageView ivAddRightRearBrakeLining;
    @BindView(R.id.et_brake_lining_thickness)
    EditText etBrakeLiningThickness;
    @BindView(R.id.iv_add_chassis_attachment)
    ImageView ivAddChassisAttachment;
    @BindView(R.id.et_chassis_attachment)
    EditText etChassisAttachment;
    @BindView(R.id.iv_add_chassis_screw)
    ImageView ivAddChassisScrew;
    @BindView(R.id.et_chassis_screw)
    EditText etChassisScrew;
    @BindView(R.id.iv_maintain_document)
    ImageView ivMaintainDocument;
    @BindView(R.id.et_maintain_document)
    EditText etMaintainDocument;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @BindView(R.id.rv_recycler_view_driving_license)
    RecyclerView rvRecyclerViewDrivingLicense;
    @BindView(R.id.rv_recycler_view_light)
    RecyclerView rvRecyclerViewLight;
    @BindView(R.id.rv_recycler_view_wiper_blade)
    RecyclerView rvRecyclerViewWiperBlade;
    @BindView(R.id.rv_recycler_view_storage_battery)
    RecyclerView rvRecyclerViewStorageBattery;
    @BindView(R.id.rv_recycler_view_cooling_liquid)
    RecyclerView rvRecyclerViewCoolingLiquid;
    @BindView(R.id.rv_recycler_view_power_fluid)
    RecyclerView rvRecyclerViewPowerFluid;
    @BindView(R.id.rv_recycler_view_air_filter)
    RecyclerView rvRecyclerViewAirFilter;
    @BindView(R.id.rv_recycler_view_cabin_filter)
    RecyclerView rvRecyclerViewCabinFilter;
    @BindView(R.id.rv_recycler_view_air_damper)
    RecyclerView rvRecyclerViewAirDamper;
    @BindView(R.id.rv_recycler_view_engine)
    RecyclerView rvRecyclerViewEngine;
    @BindView(R.id.rv_recycler_view_chassis_attachment)
    RecyclerView rvRecyclerViewChassisAttachment;
    @BindView(R.id.rv_recycler_view_chassis_screw)
    RecyclerView rvRecyclerViewChassisScrew;
    @BindView(R.id.rv_recycler_view_maintain_document)
    RecyclerView rvRecyclerViewMaintainDocument;
    @BindView(R.id.iv_delete_left_front)
    ImageView ivDeleteLeftFront;
    @BindView(R.id.iv_delete_left_rear)
    ImageView ivDeleteLeftRear;
    @BindView(R.id.iv_delete_right_front)
    ImageView ivDeleteRightFront;
    @BindView(R.id.iv_delete_right_rear)
    ImageView ivDeleteRightRear;
    @BindView(R.id.iv_delete_left_front_brake)
    ImageView ivDeleteLeftFrontBrake;
    @BindView(R.id.iv_delete_left_rear_brake)
    ImageView ivDeleteLeftRearBrake;
    @BindView(R.id.iv_delete_right_front_brake)
    ImageView ivDeleteRightFrontBrake;
    @BindView(R.id.iv_delete_right_rear_brake)
    ImageView ivDeleteRightRearBrake;
    @BindView(R.id.rv_recycler_view_drivers_license)
    RecyclerView rvRecyclerViewDriversLicense;

    @BindView(R.id.cb_wiper_blade)
    CheckBox cbWiperBlade;
    @BindView(R.id.cb_wiper_blade_clean)
    CheckBox cbWiperBladeClean;
    @BindView(R.id.cb_wiper_blade_replace)
    CheckBox cbWiperBladeReplace;
    @BindView(R.id.cb_storage_battery)
    CheckBox cbStorageBattery;
    @BindView(R.id.cb_storage_battery_carry_out)
    CheckBox cbStorageBatteryCarryOut;
    @BindView(R.id.cb_cooling_liquid)
    CheckBox cbCoolingLiquid;
    @BindView(R.id.cb_cooling_liquid_clean)
    CheckBox cbCoolingLiquidClean;
    @BindView(R.id.cb_cooling_liquid_replace)
    CheckBox cbCoolingLiquidReplace;
    @BindView(R.id.cb_power_fluid)
    CheckBox cbPowerFluid;
    @BindView(R.id.cb_power_fluid_clean)
    CheckBox cbPowerFluidClean;
    @BindView(R.id.cb_power_fluid_replace)
    CheckBox cbPowerFluidReplace;
    @BindView(R.id.cb_air_filter)
    CheckBox cbAirFilter;
    @BindView(R.id.cb_air_filter_clean)
    CheckBox cbAirFilterClean;
    @BindView(R.id.cb_air_filter_replace)
    CheckBox cbAirFilterReplace;
    @BindView(R.id.cb_cabin_filter)
    CheckBox cbCabinFilter;
    @BindView(R.id.cb_cabin_filter_clean)
    CheckBox cbCabinFilterClean;
    @BindView(R.id.cb_cabin_filter_replace)
    CheckBox cbCabinFilterReplace;
    @BindView(R.id.cb_air_damper)
    CheckBox cbAirDamper;
    @BindView(R.id.cb_air_damper_clean)
    CheckBox cbAirDamperClean;
    @BindView(R.id.cb_air_damper_replace)
    CheckBox cbAirDamperReplace;
    @BindView(R.id.cb_engine)
    CheckBox cbEngine;
    @BindView(R.id.cb_engine_carry_out)
    CheckBox cbEngineCarryOut;
    @BindView(R.id.cb_thread_depth)
    CheckBox cbThreadDepth;
    @BindView(R.id.cb_brake_lining)
    CheckBox cbBrakeLining;
    @BindView(R.id.cb_chassis_attachment)
    CheckBox cbChassisAttachment;
    @BindView(R.id.cb_chassis_attachment_carry_out)
    CheckBox cbChassisAttachmentCarryOut;
    @BindView(R.id.cb_chassis_screw)
    CheckBox cbChassisScrew;
    @BindView(R.id.cb_chassis_screw_carry_out)
    CheckBox cbChassisScrewCarryOut;
    @BindView(R.id.cb_maintain_document)
    CheckBox cbMaintainDocument;
    @BindView(R.id.cb_maintain_document_carry_out)
    CheckBox cbMaintainDocumentCarryOut;

    private PicViewAdapter mDriversLicenseAdapter,
            mDrivingLicenseAdapter,
            mLightAdapter,
            mWiperBladeAdapter,
            mStorageBatteryAdapter,
            mCoolingLiquidAdapter,
            mPowerFluidAdapter,
            mAirFilterAdapter,
            mCabinFilterAdapter,
            mAirDamperAdapter,
            mEngineAdapter,
            mChassisAttachmentAdapter,
            mChassisScrewAdapter,
            mMaintainDocumentAdapter;
    private List<Uri> uris = new ArrayList<>();
    private List<Uri> uris1 = new ArrayList<>();
    private List<Uri> uris2 = new ArrayList<>();
    private List<Uri> uris3 = new ArrayList<>();
    private List<Uri> uris4 = new ArrayList<>();
    private List<Uri> uris5 = new ArrayList<>();
    private List<Uri> uris6 = new ArrayList<>();
    private List<Uri> uris7 = new ArrayList<>();
    private List<Uri> uris8 = new ArrayList<>();
    private List<Uri> uris9 = new ArrayList<>();
    private List<Uri> uris10 = new ArrayList<>();
    private List<Uri> uris11 = new ArrayList<>();
    private List<Uri> uris12 = new ArrayList<>();
    private List<Uri> uris13 = new ArrayList<>();

    private List<String> path = new ArrayList<>();
    private List<String> path1 = new ArrayList<>();
    private List<String> path2 = new ArrayList<>();
    private List<String> path3 = new ArrayList<>();
    private List<String> path4 = new ArrayList<>();
    private List<String> path5 = new ArrayList<>();
    private List<String> path6 = new ArrayList<>();
    private List<String> path7 = new ArrayList<>();
    private List<String> path8 = new ArrayList<>();
    private List<String> path9 = new ArrayList<>();
    private List<String> path10 = new ArrayList<>();
    private List<String> path11 = new ArrayList<>();
    private List<String> path12 = new ArrayList<>();
    private List<String> path13 = new ArrayList<>();

    private List<String> path14 = new ArrayList<>();
    private List<String> path15 = new ArrayList<>();
    private List<String> path16 = new ArrayList<>();
    private List<String> path17 = new ArrayList<>();
    private List<String> path18 = new ArrayList<>();
    private List<String> path19 = new ArrayList<>();
    private List<String> path20 = new ArrayList<>();
    private List<String> path21 = new ArrayList<>();

    //修改完名字后的路径
    private List<String> afterPath = new ArrayList<>();
    private List<String> afterPath1 = new ArrayList<>();
    private List<String> afterPath2 = new ArrayList<>();
    private List<String> afterPath3 = new ArrayList<>();
    private List<String> afterPath4 = new ArrayList<>();
    private List<String> afterPath5 = new ArrayList<>();
    private List<String> afterPath6 = new ArrayList<>();
    private List<String> afterPath7 = new ArrayList<>();
    private List<String> afterPath8 = new ArrayList<>();
    private List<String> afterPath9 = new ArrayList<>();
    private List<String> afterPath10 = new ArrayList<>();
    private List<String> afterPath11 = new ArrayList<>();
    private List<String> afterPath12 = new ArrayList<>();
    private List<String> afterPath13 = new ArrayList<>();

    private List<String> afterPath14 = new ArrayList<>();
    private List<String> afterPath15 = new ArrayList<>();
    private List<String> afterPath16 = new ArrayList<>();
    private List<String> afterPath17 = new ArrayList<>();
    private List<String> afterPath18 = new ArrayList<>();
    private List<String> afterPath19 = new ArrayList<>();
    private List<String> afterPath20 = new ArrayList<>();
    private List<String> afterPath21 = new ArrayList<>();

    private List<List<String>> listPath = new ArrayList<>();
    private List<String> listKey = new ArrayList<>();

    @Override
    public int setLayoutResId() {
        return R.layout.activity_maintain_add;
    }

    @Override
    public void initView() {
        tvTitle.setText("新增");
        mDriversLicenseAdapter = new PicViewAdapter(this, uris, RequestCode.CHOOSE_DRIVERS_LICENSE);
        mDrivingLicenseAdapter = new PicViewAdapter(this, uris1, RequestCode.CHOOSE_DRIVING_LICENSE);
        mLightAdapter = new PicViewAdapter(this, uris2, RequestCode.CHOOSE_LIGHT);
        mWiperBladeAdapter = new PicViewAdapter(this, uris3, RequestCode.CHOOSE_WIPER_BLADE);
        mStorageBatteryAdapter = new PicViewAdapter(this, uris4, RequestCode.CHOOSE_STORAGE_BATTERY);
        mCoolingLiquidAdapter = new PicViewAdapter(this, uris5, RequestCode.CHOOSE_COOLING_LIQUID);
        mPowerFluidAdapter = new PicViewAdapter(this, uris6, RequestCode.CHOOSE_POWER_FLUID);
        mAirFilterAdapter = new PicViewAdapter(this, uris7, RequestCode.CHOOSE_AIR_FILTER);
        mCabinFilterAdapter = new PicViewAdapter(this, uris8, RequestCode.CHOOSE_CABIN_FILTER);
        mAirDamperAdapter = new PicViewAdapter(this, uris9, RequestCode.CHOOSE_AIR_DAMPER);
        mEngineAdapter = new PicViewAdapter(this, uris10, RequestCode.CHOOSE_ENGINE);
        mChassisAttachmentAdapter = new PicViewAdapter(this, uris11, RequestCode.CHOOSE_CHASSIS_ATTACHMENT);
        mChassisScrewAdapter = new PicViewAdapter(this, uris12, RequestCode.CHOOSE_CHASSIS_SCREW);
        mMaintainDocumentAdapter = new PicViewAdapter(this, uris13, RequestCode.CHOOSE_MAINTAIN_DOCUMENT);


        rvRecyclerViewDriversLicense.setLayoutManager(new GridLayoutManager(this, 3));
        rvRecyclerViewDrivingLicense.setLayoutManager(new GridLayoutManager(this, 3));
        rvRecyclerViewLight.setLayoutManager(new GridLayoutManager(this, 3));
        rvRecyclerViewWiperBlade.setLayoutManager(new GridLayoutManager(this, 3));
        rvRecyclerViewStorageBattery.setLayoutManager(new GridLayoutManager(this, 3));
        rvRecyclerViewCoolingLiquid.setLayoutManager(new GridLayoutManager(this, 3));
        rvRecyclerViewPowerFluid.setLayoutManager(new GridLayoutManager(this, 3));
        rvRecyclerViewAirFilter.setLayoutManager(new GridLayoutManager(this, 3));
        rvRecyclerViewCabinFilter.setLayoutManager(new GridLayoutManager(this, 3));
        rvRecyclerViewAirDamper.setLayoutManager(new GridLayoutManager(this, 3));
        rvRecyclerViewEngine.setLayoutManager(new GridLayoutManager(this, 3));
        rvRecyclerViewChassisAttachment.setLayoutManager(new GridLayoutManager(this, 3));
        rvRecyclerViewChassisScrew.setLayoutManager(new GridLayoutManager(this, 3));
        rvRecyclerViewMaintainDocument.setLayoutManager(new GridLayoutManager(this, 3));

        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(DisplayUtils.dip2px(this, 5), 3);
        rvRecyclerViewDriversLicense.addItemDecoration(spaceItemDecoration);
        rvRecyclerViewDrivingLicense.addItemDecoration(spaceItemDecoration);
        rvRecyclerViewLight.addItemDecoration(spaceItemDecoration);
        rvRecyclerViewWiperBlade.addItemDecoration(spaceItemDecoration);
        rvRecyclerViewStorageBattery.addItemDecoration(spaceItemDecoration);
        rvRecyclerViewCoolingLiquid.addItemDecoration(spaceItemDecoration);
        rvRecyclerViewPowerFluid.addItemDecoration(spaceItemDecoration);
        rvRecyclerViewAirFilter.addItemDecoration(spaceItemDecoration);
        rvRecyclerViewCabinFilter.addItemDecoration(spaceItemDecoration);
        rvRecyclerViewAirDamper.addItemDecoration(spaceItemDecoration);
        rvRecyclerViewEngine.addItemDecoration(spaceItemDecoration);
        rvRecyclerViewChassisAttachment.addItemDecoration(spaceItemDecoration);
        rvRecyclerViewChassisScrew.addItemDecoration(spaceItemDecoration);
        rvRecyclerViewMaintainDocument.addItemDecoration(spaceItemDecoration);

        rvRecyclerViewDriversLicense.setAdapter(mDriversLicenseAdapter);
        rvRecyclerViewDrivingLicense.setAdapter(mDrivingLicenseAdapter);
        rvRecyclerViewLight.setAdapter(mLightAdapter);
        rvRecyclerViewWiperBlade.setAdapter(mWiperBladeAdapter);
        rvRecyclerViewStorageBattery.setAdapter(mStorageBatteryAdapter);
        rvRecyclerViewCoolingLiquid.setAdapter(mCoolingLiquidAdapter);
        rvRecyclerViewPowerFluid.setAdapter(mPowerFluidAdapter);
        rvRecyclerViewAirFilter.setAdapter(mAirFilterAdapter);
        rvRecyclerViewCabinFilter.setAdapter(mCabinFilterAdapter);
        rvRecyclerViewAirDamper.setAdapter(mAirDamperAdapter);
        rvRecyclerViewEngine.setAdapter(mEngineAdapter);
        rvRecyclerViewChassisAttachment.setAdapter(mChassisAttachmentAdapter);
        rvRecyclerViewChassisScrew.setAdapter(mChassisScrewAdapter);
        rvRecyclerViewMaintainDocument.setAdapter(mMaintainDocumentAdapter);
    }

    @Override
    public void initData() {
        for (int i = 0; i < 10; i++) {
            String s = UuidUtils.generateUuid();
            printLogd("uuid:\t" + i + "===" + s);
        }
    }

    @Override
    public void initListener() {
        tvDate.setOnClickListener(this);

        ivAddDriversLicense.setOnClickListener(this);
        ivAddDrivingLicense.setOnClickListener(this);
        ivAddLight.setOnClickListener(this);
        ivAddWiperBlade.setOnClickListener(this);
        ivAddStorageBattery.setOnClickListener(this);
        ivAddCoolingLiquid.setOnClickListener(this);
        ivAddPowerFluid.setOnClickListener(this);
        ivAddAirFilter.setOnClickListener(this);
        ivAddCabinFilter.setOnClickListener(this);
        ivAddAirDamper.setOnClickListener(this);
        ivAddEngine.setOnClickListener(this);
        ivAddLeftFront.setOnClickListener(this);
        ivAddLeftRear.setOnClickListener(this);
        ivAddRightFront.setOnClickListener(this);
        ivAddRightRear.setOnClickListener(this);
        ivAddLeftFrontBrakeLining.setOnClickListener(this);
        ivAddLeftRearBrakeLining.setOnClickListener(this);
        ivAddRightFrontBrakeLining.setOnClickListener(this);
        ivAddRightRearBrakeLining.setOnClickListener(this);
        ivAddChassisAttachment.setOnClickListener(this);
        ivAddChassisScrew.setOnClickListener(this);
        ivMaintainDocument.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        mDriversLicenseAdapter.setOnRecyclerItemClickListener(this);
        mDrivingLicenseAdapter.setOnRecyclerItemClickListener(this);
        mLightAdapter.setOnRecyclerItemClickListener(this);
        mWiperBladeAdapter.setOnRecyclerItemClickListener(this);
        mStorageBatteryAdapter.setOnRecyclerItemClickListener(this);
        mCoolingLiquidAdapter.setOnRecyclerItemClickListener(this);
        mPowerFluidAdapter.setOnRecyclerItemClickListener(this);
        mAirFilterAdapter.setOnRecyclerItemClickListener(this);
        mCabinFilterAdapter.setOnRecyclerItemClickListener(this);
        mAirDamperAdapter.setOnRecyclerItemClickListener(this);
        mEngineAdapter.setOnRecyclerItemClickListener(this);
        mChassisScrewAdapter.setOnRecyclerItemClickListener(this);
        mChassisScrewAdapter.setOnRecyclerItemClickListener(this);
        mMaintainDocumentAdapter.setOnRecyclerItemClickListener(this);

        ivDeleteLeftFront.setOnClickListener(this);
        ivDeleteLeftRear.setOnClickListener(this);
        ivDeleteRightFront.setOnClickListener(this);
        ivDeleteRightRear.setOnClickListener(this);
        ivDeleteLeftFrontBrake.setOnClickListener(this);
        ivDeleteLeftRearBrake.setOnClickListener(this);
        ivDeleteRightFrontBrake.setOnClickListener(this);
        ivDeleteRightRearBrake.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_date:
                showDatePickerDialog();
                break;
            case R.id.iv_add_drivers_license:
                choosePics(RequestCode.CHOOSE_DRIVERS_LICENSE);
                break;
            case R.id.iv_add_driving_license:
                choosePics(RequestCode.CHOOSE_DRIVING_LICENSE);
                break;
            case R.id.iv_add_light:
                choosePics(RequestCode.CHOOSE_LIGHT);
                break;
            case R.id.iv_add_wiper_blade:
                choosePics(RequestCode.CHOOSE_WIPER_BLADE);
                break;
            case R.id.iv_add_storage_battery:
                choosePics(RequestCode.CHOOSE_STORAGE_BATTERY);
                break;
            case R.id.iv_add_cooling_liquid:
                choosePics(RequestCode.CHOOSE_COOLING_LIQUID);
                break;
            case R.id.iv_add_power_fluid:
                choosePics(RequestCode.CHOOSE_POWER_FLUID);
                break;
            case R.id.iv_add_air_filter:
                choosePics(RequestCode.CHOOSE_AIR_FILTER);
                break;
            case R.id.iv_add_cabin_filter:
                choosePics(RequestCode.CHOOSE_CABIN_FILTER);
                break;
            case R.id.iv_add_air_damper:
                choosePics(RequestCode.CHOOSE_AIR_DAMPER);
                break;
            case R.id.iv_add_engine:
                choosePics(RequestCode.CHOOSE_ENGINE);
                break;
            case R.id.iv_add_left_front:
                choosePics(RequestCode.CHOOSE_THREAD_LEFT_FRONT);
                break;
            case R.id.iv_add_left_rear:
                choosePics(RequestCode.CHOOSE_THREAD_LEFT_REAR);
                break;
            case R.id.iv_add_right_front:
                choosePics(RequestCode.CHOOSE_THREAD_RIGHT_FRONT);
                break;
            case R.id.iv_add_right_rear:
                choosePics(RequestCode.CHOOSE_THREAD_RIGHT_REAR);
                break;
            case R.id.iv_add_left_front_brake_lining:
                choosePics(RequestCode.CHOOSE_BRAKE_LEFT_FRONT);
                break;
            case R.id.iv_add_left_rear_brake_lining:
                choosePics(RequestCode.CHOOSE_BRAKE_LEFT_REAR);
                break;
            case R.id.iv_add_right_front_brake_lining:
                choosePics(RequestCode.CHOOSE_BRAKE_RIGHT_FRONT);
                break;
            case R.id.iv_add_right_rear_brake_lining:
                choosePics(RequestCode.CHOOSE_BRAKE_RIGHT_REAR);
                break;
            case R.id.iv_add_chassis_attachment:
                choosePics(RequestCode.CHOOSE_CHASSIS_ATTACHMENT);
                break;
            case R.id.iv_add_chassis_screw:
                choosePics(RequestCode.CHOOSE_CHASSIS_SCREW);
                break;
            case R.id.iv_maintain_document:
                choosePics(RequestCode.CHOOSE_MAINTAIN_DOCUMENT);
                break;
            case R.id.btn_save:
                showToast("保存");
                initAllKey();
                addAllPath();
                saveImages();
                break;
            case R.id.btn_submit:
                showToast("提交");
                break;
            case R.id.iv_delete_left_front:
                ivAddLeftFront.setImageResource(R.drawable.ic_add_box);
                ivDeleteLeftFront.setVisibility(View.INVISIBLE);
                afterPath14.clear();
                break;
            case R.id.iv_delete_left_rear:
                ivAddLeftRear.setImageResource(R.drawable.ic_add_box);
                ivDeleteLeftRear.setVisibility(View.INVISIBLE);
                afterPath15.clear();
                break;
            case R.id.iv_delete_right_front:
                ivAddRightFront.setImageResource(R.drawable.ic_add_box);
                ivDeleteRightFront.setVisibility(View.INVISIBLE);
                afterPath16.clear();
                break;
            case R.id.iv_delete_right_rear:
                ivAddRightRear.setImageResource(R.drawable.ic_add_box);
                ivDeleteRightRear.setVisibility(View.INVISIBLE);
                afterPath17.clear();
                break;
            case R.id.iv_delete_left_front_brake:
                ivAddLeftFrontBrakeLining.setImageResource(R.drawable.ic_add_box);
                ivDeleteLeftFrontBrake.setVisibility(View.INVISIBLE);
                afterPath18.clear();
                break;
            case R.id.iv_delete_left_rear_brake:
                ivAddLeftRearBrakeLining.setImageResource(R.drawable.ic_add_box);
                ivDeleteLeftRearBrake.setVisibility(View.INVISIBLE);
                afterPath19.clear();
                break;
            case R.id.iv_delete_right_front_brake:
                ivAddRightFrontBrakeLining.setImageResource(R.drawable.ic_add_box);
                ivDeleteRightFrontBrake.setVisibility(View.INVISIBLE);
                afterPath20.clear();
                break;
            case R.id.iv_delete_right_rear_brake:
                ivAddRightRearBrakeLining.setImageResource(R.drawable.ic_add_box);
                ivDeleteRightRearBrake.setVisibility(View.INVISIBLE);
                afterPath21.clear();
                break;
        }
    }

    private void initAllKey() {
//        saveImages(afterPath, "jsz_zp");
//        saveImages(afterPath1, "xsz_zp");
//        saveImages(afterPath2, "qcdg_zp");
//        saveImages(afterPath3, "ygp_zp");
//        saveImages(afterPath4, "dpjcddy_zp");
//        saveImages(afterPath5, "lqy_zp");
//        saveImages(afterPath6, "dlzxy_zp");
//        saveImages(afterPath7, "kqlx_zp");
//        saveImages(afterPath8, "ktlx_zp");
//        saveImages(afterPath9, "jqm_zp");
//        saveImages(afterPath10, "fdjyx_zp");
//        saveImages(afterPath11, "dpljj_zp");
//        saveImages(afterPath12, "dpls_zp");
//        saveImages(afterPath13, "clbydab_zp");
//
//        saveImages(afterPath14, "lthwsdzq_zp");//轮胎花纹深度
//        saveImages(afterPath15, "lthwsdzh_zp");
//        saveImages(afterPath16, "lthwsdyq_zp");
//        saveImages(afterPath17, "lthwsdyh_zp");
//        saveImages(afterPath18, "zdmcphdzq_zp");//制动摩擦片剩余厚度
//        saveImages(afterPath19, "zdmcphdzh_zp");
//        saveImages(afterPath20, "zdmcphdyq_zp");
//        saveImages(afterPath21, "zdmcphdyh_zp");

        listKey.add("jsz_zp");
        listKey.add("xsz_zp");
        listKey.add("qcdg_zp");
        listKey.add("ygp_zp");
        listKey.add("dpjcddy_zp");
        listKey.add("lqy_zp");
        listKey.add("dlzxy_zp");
        listKey.add("kqlx_zp");
        listKey.add("ktlx_zp");
        listKey.add("jqm_zp");
        listKey.add("fdjyx_zp");
        listKey.add("dpljj_zp");
        listKey.add("dpls_zp");
        listKey.add("clbydab_zp");

        listKey.add("lthwsdzq_zp");//轮胎花纹深度
        listKey.add("lthwsdzh_zp");
        listKey.add("lthwsdyq_zp");
        listKey.add("lthwsdyh_zp");
        listKey.add("zdmcphdzq_zp");//制动摩擦片剩余厚度
        listKey.add("zdmcphdzh_zp");
        listKey.add("zdmcphdyq_zp");
        listKey.add("zdmcphdyh_zp");
    }

    /**
     * 将所有模块路径集合统一存起来
     */
    private void addAllPath() {
        listPath.add(afterPath);
        listPath.add(afterPath1);
        listPath.add(afterPath2);
        listPath.add(afterPath3);
        listPath.add(afterPath4);
        listPath.add(afterPath5);
        listPath.add(afterPath6);
        listPath.add(afterPath7);
        listPath.add(afterPath8);
        listPath.add(afterPath9);
        listPath.add(afterPath10);
        listPath.add(afterPath11);
        listPath.add(afterPath12);
        listPath.add(afterPath13);
        listPath.add(afterPath14);
        listPath.add(afterPath15);
        listPath.add(afterPath16);
        listPath.add(afterPath17);
        listPath.add(afterPath18);
        listPath.add(afterPath19);
        listPath.add(afterPath20);
        listPath.add(afterPath21);
    }

    /**
     * 保存图片
     */
    private void saveImages() {
//        Map<String, RequestBody> map = new HashMap<>();
        Map<String, Map<String, RequestBody>> mapMap = new HashMap<>();
//        if (imagePath.size() > 0) {
//            for (String sx : imagePath) {
//                File file = new File(sx);
//                RequestBody requestPic = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//                map.put(file.getName(), requestPic);
//            }
//        }
//        if (map.size() > 0) {
//        }
        for (int i = 0; i < listPath.size(); i++) {
            Map<String, RequestBody> map1 = new HashMap<>();
            for (int j = 0; j < listPath.get(i).size(); j++) {
                File file = new File(listPath.get(i).get(j));
                RequestBody requestPic = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                map1.put(file.getName(), requestPic);
            }
            mapMap.put(listKey.get(i), map1);
            uploadPics(mapMap);
        }

    }

    int i = 0;

    private void uploadPics(Map<String, Map<String, RequestBody>> map) {
        //        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestPic);
        Observable<Map<String, Object>> requestBodyObservable = AppRetrofit.getInstance()
                .getHttpService().uploadImage("Pics", map);
        requestBodyObservable.subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Map<String, Object>>() {
                    @Override
                    public void onNext(Map<String, Object> stringObjectMap) {
                        String json = new Gson().toJson(stringObjectMap);
                        i++;
                        printLogd(i + "=====" + json);
                        Gson gson = new Gson();
                        MaintainAddSaveImageBean saveImageBean = gson.fromJson(json, MaintainAddSaveImageBean.class);
                        String state = saveImageBean.getState();
                        if (Constant.STATE_SUCCESS.equals(state)) {
                            String returnMsg = saveImageBean.getReturnMsg();
                            showToast(returnMsg);
                        } else {

                        }
                    }
                });
    }

    Calendar myCalendar = Calendar.getInstance();

    private void showDatePickerDialog() {
        new DatePickerDialog(this, d, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String myFormat = "yyyy-MM-dd"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CHINA);
            String date = sdf.format(myCalendar.getTime());
            tvDate.setText(date);
        }

    };


    private void choosePics(int requestCode) {
        Matisse.from(this)
                .choose(MimeType.of(MimeType.JPEG, MimeType.PNG, MimeType.GIF))
                .theme(R.style.Matisse_Dracula)
                .countable(true)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true, "com.kevin.carservice.fileprovider"))
                .maxSelectable(3)
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(
                        getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new PicassoEngine())
                .forResult(requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            setImageByRequestCode(requestCode, data);
        }
    }


    private void setImageByRequestCode(int requestCode, Intent data) {
        switch (requestCode) {
            case RequestCode.CHOOSE_DRIVERS_LICENSE:
                mDriversLicenseAdapter.setData(getUris(data));
                compressImage(getPath(path, data), afterPath);
                break;
            case RequestCode.CHOOSE_DRIVING_LICENSE:
                mDrivingLicenseAdapter.setData(getUris(data));
                compressImage(getPath(path1, data), afterPath1);
                break;
            case RequestCode.CHOOSE_LIGHT:
                mLightAdapter.setData(getUris(data));
                compressImage(getPath(path2, data), afterPath2);
                break;
            case RequestCode.CHOOSE_WIPER_BLADE:
                mWiperBladeAdapter.setData(getUris(data));
                compressImage(getPath(path3, data), afterPath3);
                break;
            case RequestCode.CHOOSE_STORAGE_BATTERY:
                mStorageBatteryAdapter.setData(getUris(data));
                compressImage(getPath(path4, data), afterPath4);
                break;
            case RequestCode.CHOOSE_COOLING_LIQUID:
                mCoolingLiquidAdapter.setData(getUris(data));
                compressImage(getPath(path5, data), afterPath5);
                break;
            case RequestCode.CHOOSE_POWER_FLUID:
                mPowerFluidAdapter.setData(getUris(data));
                compressImage(getPath(path6, data), afterPath6);
                break;
            case RequestCode.CHOOSE_AIR_FILTER:
                mAirFilterAdapter.setData(getUris(data));
                compressImage(getPath(path7, data), afterPath7);
                break;
            case RequestCode.CHOOSE_CABIN_FILTER:

                mCabinFilterAdapter.setData(getUris(data));
                compressImage(getPath(path8, data), afterPath8);
                break;
            case RequestCode.CHOOSE_AIR_DAMPER:
                mAirDamperAdapter.setData(getUris(data));
                compressImage(getPath(path9, data), afterPath9);
                break;
            case RequestCode.CHOOSE_ENGINE:
                mEngineAdapter.setData(getUris(data));
                compressImage(getPath(path10, data), afterPath10);
                break;
            case RequestCode.CHOOSE_CHASSIS_ATTACHMENT:
                mChassisAttachmentAdapter.setData(getUris(data));
                compressImage(getPath(path11, data), afterPath11);
                break;
            case RequestCode.CHOOSE_CHASSIS_SCREW:
                mChassisScrewAdapter.setData(getUris(data));
                compressImage(getPath(path12, data), afterPath12);
                break;
            case RequestCode.CHOOSE_MAINTAIN_DOCUMENT:
                mMaintainDocumentAdapter.setData(getUris(data));
                compressImage(getPath(path13, data), afterPath13);
                break;
            case RequestCode.CHOOSE_THREAD_LEFT_FRONT:
                setImageByRequestCode(ivAddLeftFront, data);
                ivDeleteLeftFront.setVisibility(View.VISIBLE);
                compressImage(getPath(path14, data), afterPath14);
                break;
            case RequestCode.CHOOSE_THREAD_LEFT_REAR:
                setImageByRequestCode(ivAddLeftRear, data);
                ivDeleteLeftRear.setVisibility(View.VISIBLE);
                compressImage(getPath(path15, data), afterPath15);
                break;
            case RequestCode.CHOOSE_THREAD_RIGHT_FRONT:
                setImageByRequestCode(ivAddRightFront, data);
                ivDeleteRightFront.setVisibility(View.VISIBLE);
                compressImage(getPath(path16, data), afterPath16);
                break;
            case RequestCode.CHOOSE_THREAD_RIGHT_REAR:
                setImageByRequestCode(ivAddRightRear, data);
                ivDeleteRightRear.setVisibility(View.VISIBLE);
                compressImage(getPath(path17, data), afterPath17);
                break;
            case RequestCode.CHOOSE_BRAKE_LEFT_FRONT:
                setImageByRequestCode(ivAddLeftFrontBrakeLining, data);
                ivDeleteLeftFrontBrake.setVisibility(View.VISIBLE);
                compressImage(getPath(path18, data), afterPath18);
                break;
            case RequestCode.CHOOSE_BRAKE_LEFT_REAR:
                setImageByRequestCode(ivAddLeftRearBrakeLining, data);
                ivDeleteLeftRearBrake.setVisibility(View.VISIBLE);
                compressImage(getPath(path19, data), afterPath19);
                break;
            case RequestCode.CHOOSE_BRAKE_RIGHT_FRONT:
                setImageByRequestCode(ivAddRightFrontBrakeLining, data);
                ivDeleteRightFrontBrake.setVisibility(View.VISIBLE);
                compressImage(getPath(path20, data), afterPath20);
                break;
            case RequestCode.CHOOSE_BRAKE_RIGHT_REAR:
                setImageByRequestCode(ivAddRightRearBrakeLining, data);
                ivDeleteRightRearBrake.setVisibility(View.VISIBLE);
                compressImage(getPath(path21, data), afterPath21);
                break;
            default:
                break;
        }
    }

    /**
     * 根据Uri来获取图片对应的路径
     *
     * @param path
     * @param data
     * @return
     */
    private List<String> getPath(List<String> path, Intent data) {
        for (Uri u : getUris(data)) {
            String[] proj = {MediaStore.Images.Media.DATA};
            ContentResolver cr = this.getContentResolver();
            Cursor cursor = cr.query(u, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
//最后根据索引值获取图片路径
            String path1 = cursor.getString(column_index);
            printLogd("---------" + path1);
            path.add(path1);
        }
        return path;
    }

    private List<Uri> getUris(Intent data) {
        return Matisse.obtainResult(data);
    }

    private void setImageByRequestCode(ImageView imageView, Intent data) {
        Glide.with(this)
                .load(getUris(data).get(0))
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .into(imageView);
    }


    @Override
    public void onRecyclerItemClick(int requestCode, int position) {
        switch (requestCode) {
            case RequestCode.CHOOSE_DRIVERS_LICENSE:
                startScaleActivity(uris, position);
                break;
            case RequestCode.CHOOSE_DRIVING_LICENSE:
                startScaleActivity(uris1, position);
                break;
            case RequestCode.CHOOSE_LIGHT:
                startScaleActivity(uris2, position);
                break;
            case RequestCode.CHOOSE_WIPER_BLADE:
                startScaleActivity(uris3, position);
                break;
            case RequestCode.CHOOSE_STORAGE_BATTERY:
                startScaleActivity(uris4, position);
                break;
            case RequestCode.CHOOSE_COOLING_LIQUID:
                startScaleActivity(uris5, position);
                break;
            case RequestCode.CHOOSE_POWER_FLUID:
                startScaleActivity(uris6, position);
                break;
            case RequestCode.CHOOSE_AIR_FILTER:
                startScaleActivity(uris7, position);
                break;
            case RequestCode.CHOOSE_CABIN_FILTER:
                startScaleActivity(uris8, position);
                break;
            case RequestCode.CHOOSE_AIR_DAMPER:
                startScaleActivity(uris9, position);
                break;
            case RequestCode.CHOOSE_ENGINE:
                startScaleActivity(uris10, position);
                break;
            case RequestCode.CHOOSE_CHASSIS_ATTACHMENT:
                startScaleActivity(uris11, position);
                break;
            case RequestCode.CHOOSE_CHASSIS_SCREW:
                startScaleActivity(uris12, position);
                break;
            case RequestCode.CHOOSE_MAINTAIN_DOCUMENT:
                startScaleActivity(uris13, position);
                break;
        }
    }

    @Override
    public void onItemDeleteClick(int requestCode, int position) {
        switch (requestCode) {
            case RequestCode.CHOOSE_DRIVERS_LICENSE:
                mDriversLicenseAdapter.deleteData(uris, position);
                afterPath.remove(position);//点击删除将需要上传的集合中的路径也相应删除
                break;
            case RequestCode.CHOOSE_DRIVING_LICENSE:
                mDrivingLicenseAdapter.deleteData(uris1, position);
                afterPath1.remove(position);
                break;
            case RequestCode.CHOOSE_LIGHT:
                mLightAdapter.deleteData(uris2, position);
                afterPath2.remove(position);
                break;
            case RequestCode.CHOOSE_WIPER_BLADE:
                mWiperBladeAdapter.deleteData(uris3, position);
                afterPath3.remove(position);
                break;
            case RequestCode.CHOOSE_STORAGE_BATTERY:
                mStorageBatteryAdapter.deleteData(uris4, position);
                afterPath4.remove(position);
                break;
            case RequestCode.CHOOSE_COOLING_LIQUID:
                mCoolingLiquidAdapter.deleteData(uris5, position);
                afterPath5.remove(position);
                break;
            case RequestCode.CHOOSE_POWER_FLUID:
                mPowerFluidAdapter.deleteData(uris6, position);
                afterPath6.remove(position);
                break;
            case RequestCode.CHOOSE_AIR_FILTER:
                mAirFilterAdapter.deleteData(uris7, position);
                afterPath7.remove(position);
                break;
            case RequestCode.CHOOSE_CABIN_FILTER:
                mCabinFilterAdapter.deleteData(uris8, position);
                afterPath8.remove(position);
                break;
            case RequestCode.CHOOSE_AIR_DAMPER:
                mAirDamperAdapter.deleteData(uris9, position);
                afterPath9.remove(position);
                break;
            case RequestCode.CHOOSE_ENGINE:
                mEngineAdapter.deleteData(uris10, position);
                afterPath10.remove(position);
                break;
            case RequestCode.CHOOSE_CHASSIS_ATTACHMENT:
                mChassisAttachmentAdapter.deleteData(uris11, position);
                afterPath11.remove(position);
                break;
            case RequestCode.CHOOSE_CHASSIS_SCREW:
                mChassisScrewAdapter.deleteData(uris12, position);
                afterPath12.remove(position);
                break;
            case RequestCode.CHOOSE_MAINTAIN_DOCUMENT:
                mMaintainDocumentAdapter.deleteData(uris13, position);
                afterPath13.remove(position);
                break;
        }
    }


    private void startScaleActivity(List<Uri> uri, int position) {
        Intent intent = new Intent(this, MaintainDetailImageScaleActivity.class);
        Bundle bundle = new Bundle();
        ArrayList<String> stringList = uriToString(uri);
        bundle.putStringArrayList("uris", stringList);
        bundle.putString("position", String.valueOf(position));
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

    /**
     * 将uri集合转为string集合
     *
     * @param uri
     * @return
     */
    private ArrayList<String> uriToString(List<Uri> uri) {
        ArrayList<String> x = new ArrayList<>();
        for (Uri uri1 : uri) {
            x.add(uri1.toString());
        }
        return x;
    }

    /**
     * 对多个图片进行压缩
     *
     * @param path
     */
    private void compressImage(List<String> path, final List<String> renamePath) {
        Luban.with(this)
                .load(path)                     //传人要压缩的图片
                .ignoreBy(100)//图片小时100kb后不再压缩
                .setCompressListener(new OnCompressListener() { //设置回调

                    @Override
                    public void onStart() {
                        //压缩开始前调用，可以在方法内启动 loading UI
//                        showToast("开始");
                    }

                    @Override
                    public void onSuccess(File file) {
                        //压缩成功后调用，返回压缩后的图片文件
                        String absolutePath = file.getAbsolutePath();
                        String path22 = file.getPath();
                        try {
                            String canonicalPath = file.getCanonicalPath();
                            printLogd("cano:::\t" + canonicalPath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        String parent = file.getParent();
                        printLogd("parent:::\t" + parent);
                        printLogd("absolutePath===:\t" + absolutePath);
                        printLogd("path22===:\t" + path22);
                        long l = file.length() / 1024;
                        printLogd("压缩后图片：" + l + "KB");
//                        showToast("压缩成功");
                        File file1 = new File(absolutePath);
                        String pathname = file.getParent() + File.separator + UuidUtils.generateUuid() + ".jpg";
                        boolean b = file1.renameTo(new File(pathname));
                        printLogd("pathname:---:\t" + pathname);
                        printLogd("boolean:---:\t" + b);
                        renamePath.add(pathname);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // 当压缩过去出现问题时调用
//                        showToast("Error::" + e.getMessage());
                    }
                }).launch();    //启动压缩

    }

}
