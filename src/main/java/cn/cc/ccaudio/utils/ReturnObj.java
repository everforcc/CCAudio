package cn.cc.ccaudio.utils;

import cn.cc.ccaudio.constant.StatusEnum;
import com.alibaba.fastjson.JSON;

/**
 * 此类用于所有返回的情况 code和msg不允许为空
 */
public class ReturnObj {



    /* 状态码 */
    private String code;
    /* 信息 */
    private String value;
    /* Obj */
    private Object data;

    // get set 重要


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setStatusEnum(StatusEnum statusEnum){
        this.code = statusEnum.getCode();
        this.value = statusEnum.getValue();
    }

    public ReturnObj(StatusEnum statusEnum, Object data) {
        this.code = statusEnum.getCode();
        this.value = statusEnum.getValue();
        this.data = data;
    }

    public ReturnObj(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public ReturnObj(StatusEnum statusEnum) {
        this.code = statusEnum.getCode();
        this.value = statusEnum.getValue();
    }

    public ReturnObj() {
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
