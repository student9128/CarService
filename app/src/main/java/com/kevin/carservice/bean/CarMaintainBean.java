package com.kevin.carservice.bean;

import java.util.List;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/17.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class CarMaintainBean {

    /**
     * state : Y
     * returnMsg :
     * CTNT : [{"czsj":"2018-01-20 09:54:23","sfbc":"3","color":"0,128,0","carNo":"苏AC55S3","zt":"已发送","uuid":"3D2D4393-5AD0-4E39-87EC-1219CD4590BE"},{"czsj":"2018-01-20 07:24:10","sfbc":"3","color":"0,128,0","carNo":"沪T00002","zt":"已发送","uuid":"0336C73B-349C-44F1-8258-D356D3BBED15"},{"czsj":"2018-01-19 17:45:34","sfbc":"3","color":"0,128,0","carNo":"苏D05C59","zt":"已发送","uuid":"F0E9FDEF-F1F0-402B-B3E1-BC5156533854"},{"czsj":"2018-01-19 16:49:00","sfbc":"3","color":"0,128,0","carNo":"苏杭","zt":"已发送","uuid":"A403AC2B-040B-4FED-AE9B-27EC5494E2DD"},{"czsj":"2018-01-19 07:05:01","sfbc":"3","color":"0,128,0","carNo":"沪A12345","zt":"已发送","uuid":"284E16C8-EFF0-4CC3-8DB0-89FA764FBD7D"},{"czsj":"2018-01-18 13:48:16","sfbc":"1","color":"255,165,0","carNo":"鲁HEO35X","zt":"检查中","uuid":"DE997527-2575-426E-B193-B8D64E44E933"},{"czsj":"2018-01-17 16:37:13","sfbc":"1","color":"255,165,0","carNo":"苏D08765","zt":"检查中","uuid":"5FAA4750-CA3C-43C9-95F5-217B72A78A8D"},{"czsj":"2018-01-17 11:07:52","sfbc":"3","color":"0,128,0","carNo":"沪A44444","zt":"已发送","uuid":"0F814EF4-A613-4B48-AFE8-818F1C071AC3"},{"czsj":"2018-01-14 18:13:23","sfbc":"1","color":"255,165,0","carNo":"GGGG","zt":"检查中","uuid":"D9FDC541-F62B-47BD-929D-EC54785158C1"},{"czsj":"2018-01-11 23:19:05","sfbc":"2","color":"0,0,255","carNo":"沪AQMSF7","zt":"已完成","uuid":"1E60D569-842C-4B98-BA30-7EC5C3E57F9C"}]
     * CONFIG : [{"color":"255,0,0","group_name":"lthwsd","sort":1,"value":"小于1"},{"color":"255,0,0","group_name":"lthwsd","sort":2,"value":"1-1.5"},{"color":"255,165,0","group_name":"lthwsd","sort":3,"value":"1.6-1.9"},{"color":"255,165,0","group_name":"lthwsd","sort":4,"value":"2-2.4"},{"color":"0,139,0","group_name":"lthwsd","sort":5,"value":"2.5-3.5"},{"color":"0,139,0","group_name":"lthwsd","sort":6,"value":"大于3.5"},{"color":"255,0,0","group_name":"scphd","sort":1,"value":"小于2"},{"color":"255,0,0","group_name":"scphd","sort":2,"value":"2-2.9"},{"color":"255,165,0","group_name":"scphd","sort":3,"value":"3-3.9"},{"color":"255,165,0","group_name":"scphd","sort":4,"value":"4-4.9"},{"color":"0,139,0","group_name":"scphd","sort":5,"value":"5-5.9"},{"color":"0,139,0","group_name":"scphd","sort":6,"value":"大于6"},{"color":"0,139,0","group_name":"zdpdmmsl","sort":1,"value":"小于0.4"},{"color":"0,139,0","group_name":"zdpdmmsl","sort":2,"value":"0.4-0.6"},{"color":"255,0,0","group_name":"zdpdmmsl","sort":3,"value":"0.7-0.9"},{"color":"255,0,0","group_name":"zdpdmmsl","sort":4,"value":"1-1.5"},{"color":"255,0,0","group_name":"zdpdmmsl","sort":5,"value":"1.6-2"},{"color":"255,0,0","group_name":"zdpdmmsl","sort":6,"value":"大于2"}]
     */

    private String state;
    private String returnMsg;
    private List<CTNTBean> CTNT;
    private List<CONFIGBean> CONFIG;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public List<CTNTBean> getCTNT() {
        return CTNT;
    }

    public void setCTNT(List<CTNTBean> CTNT) {
        this.CTNT = CTNT;
    }

    public List<CONFIGBean> getCONFIG() {
        return CONFIG;
    }

    public void setCONFIG(List<CONFIGBean> CONFIG) {
        this.CONFIG = CONFIG;
    }

    public static class CTNTBean {
        /**
         * czsj : 2018-01-20 09:54:23
         * sfbc : 3
         * color : 0,128,0
         * carNo : 苏AC55S3
         * zt : 已发送
         * uuid : 3D2D4393-5AD0-4E39-87EC-1219CD4590BE
         */

        private String czsj;
        private String sfbc;
        private String color;
        private String carNo;
        private String zt;
        private String uuid;

        public String getCzsj() {
            return czsj;
        }

        public void setCzsj(String czsj) {
            this.czsj = czsj;
        }

        public String getSfbc() {
            return sfbc;
        }

        public void setSfbc(String sfbc) {
            this.sfbc = sfbc;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getCarNo() {
            return carNo;
        }

        public void setCarNo(String carNo) {
            this.carNo = carNo;
        }

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }

    public static class CONFIGBean {
        /**
         * color : 255,0,0
         * group_name : lthwsd
         * sort : 1.0
         * value : 小于1
         */

        private String color;
        private String group_name;
        private double sort;
        private String value;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public double getSort() {
            return sort;
        }

        public void setSort(double sort) {
            this.sort = sort;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
