package cn.cc.ccaudio.dto;

import java.util.Date;

public class UserMain {
    /* */
    private int id;
    /* */
    private String name;
    /* */
    private String userName;
    /* */
    private String passWord;
    /* */
    private String token;
    /* */
    private Date tokenExpireDate;
    /* */
    private Date createDate;
    /* */
    private Date accountExpireDate;
    /* */
    private int effect;
    /* */
    private String phone;
    /* */
    private String remark;

    public UserMain() {
    }

    public UserMain(String name, String userName, String passWord, String token, Date tokenExpireDate, Date createDate, Date accountExpireDate, int effect, String phone, String remark) {
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
        this.token = token;
        this.tokenExpireDate = tokenExpireDate;
        this.createDate = createDate;
        this.accountExpireDate = accountExpireDate;
        this.effect = effect;
        this.phone = phone;
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenExpireDate() {
        return tokenExpireDate;
    }

    public void setTokenExpireDate(Date tokenExpireDate) {
        this.tokenExpireDate = tokenExpireDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getAccountExpireDate() {
        return accountExpireDate;
    }

    public void setAccountExpireDate(Date accountExpireDate) {
        this.accountExpireDate = accountExpireDate;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
