package com.kevin.carservice.http;

import com.kevin.carservice.bean.CarMaintainBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2017/12/27.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public interface HttpInterface {

    @FormUrlEncoded
    @POST("http://116.62.114.81/carx/qcwxSy.do")
    Observable<CarMaintainBean> queryData(@Field("SN") String sn);



}
