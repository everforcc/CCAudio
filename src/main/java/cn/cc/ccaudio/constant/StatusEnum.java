package cn.cc.ccaudio.constant;

// 定义返回状态
public enum StatusEnum {


    Status001("001","用户未登录，请先登录"),
    Status002("002","凭证已失效，请重新登录"),
    Status003("003","用户名或密码错误"),
    Status004("004","没有权限"),
    Status005("005","用户名不存在"),
    Status006("006","用户名或密码不能为空"),
    Status200("200","成功");



    private String code;
    private String value;

    StatusEnum() {
    }

    StatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

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
}
