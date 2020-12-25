package cn.cc.ccaudio.dto;

import java.util.Date;

// 用户信息表
public class UserMain {
    private static final long serialVersionUID = 1L;
    /* */
    private int id;
    /* 用户名 */
    private String name;
    /* 真实姓名 */
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

    public UserMain(String name, String userName, String passWord, Date tokenExpireDate, Date createDate, int effect, String remark) {
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
        this.tokenExpireDate = tokenExpireDate;
        this.createDate = createDate;
        this.effect = effect;
        this.remark = remark;
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

    /*public UserMain(int id, String name, String userName, String passWord, String token, Date tokenExpireDate, Date createDate, Date accountExpireDate, int effect, String phone, String remark) {
        this.id = id;
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
    }*/

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

    public int getId() {
        return id;
    }

    // 有set才能附上值
    public void setId(int id) {
        this.id = id;
    }
}
