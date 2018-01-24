package com.kevin.carservice.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/23.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */

@Entity
public class CarDataEntity {
    @Id
    private Long id;
    @Property(nameInDb = "carNumber")
    private String carNumber;
    @Property(nameInDb = "status")
    private String status;
    @Property(nameInDb = "createTime")
    private String createTime;
    @Property(nameInDb = "statusColor")
    private String statusColor;
    @Generated(hash = 623947470)
    public CarDataEntity(Long id, String carNumber, String status,
            String createTime, String statusColor) {
        this.id = id;
        this.carNumber = carNumber;
        this.status = status;
        this.createTime = createTime;
        this.statusColor = statusColor;
    }
    @Generated(hash = 1727941528)
    public CarDataEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCarNumber() {
        return this.carNumber;
    }
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getStatusColor() {
        return this.statusColor;
    }
    public void setStatusColor(String statusColor) {
        this.statusColor = statusColor;
    }
}
