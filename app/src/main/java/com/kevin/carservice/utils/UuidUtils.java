package com.kevin.carservice.utils;

import java.util.UUID;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/22.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class UuidUtils {
    public static String generateUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().toUpperCase();
    }
}
