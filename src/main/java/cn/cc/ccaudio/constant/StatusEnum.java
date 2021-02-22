package cn.cc.ccaudio.constant;

// 定义返回状态
public enum StatusEnum {

    //调整拦截器的错误和file的错误总结到一起，然后全部重新命名，注释掉，看报错

    Status000("000","无数据"),
    Status001("001","用户未登录，请先登录"),
    Status002("002","凭证已失效，请重新登录"),
    Status003("003","用户名或密码错误"),
    Status004("004","没有权限"),
    Status005("005","用户名不存在"),
    Status006("006","用户名或密码不能为空"),
    Status007("007","文件不存在"),
    Status997("998","暂无数据"),
    Status998("998","参数异常"),
    Status999("999","数据错误"),
    StatusUser200("001","用户已存在"),
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
