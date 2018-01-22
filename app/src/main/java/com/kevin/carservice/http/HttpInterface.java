package com.kevin.carservice.http;

import com.kevin.carservice.bean.CarMaintainBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2017/12/27.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public interface HttpInterface {

    @FormUrlEncoded
    @POST("qcwxSy.do")
    Observable<CarMaintainBean> queryData(@Field("SN") String sn);

    @Multipart
    @POST("imgFileUpload.do")
    Observable<Map<String, Object>> uploadImage(@Part("Pics") String description,
                                                @PartMap Map<String, Map<String, RequestBody>> maps);

}
