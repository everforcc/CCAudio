package cn.cc.ccaudio.bean;


import java.util.Date;

/**
 * 验证码实体类
 */
public class UserEntity {
    /**
     * ID
     */
    private Integer id;
    /**
     * name 别名
     */
    private String name;
    /**
     * username 用户名
     */
    private String username;
    /**
     * password 密码
     */
    private String password;
    /**
     * token 登陆凭证
     */
    private String token;
    /**
     * token 过期时间
     */
    private Date tokenExpireDate;
    /**
     *  登录时间
     */
    private Date createDate;

    public UserEntity() {
    }

    public UserEntity(Integer id, String name, String username, String password, String token, Date tokenExpireDate, Date createDate) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.token = token;
        this.tokenExpireDate = tokenExpireDate;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
