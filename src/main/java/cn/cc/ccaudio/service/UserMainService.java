package cn.cc.ccaudio.service;

public interface UserMainService {

    /* 校验用户名 */
    public String checkUserName(String userName);

    /* 登录校验 */
    public String queryForLogin(String userName,String passWord);

    /* 查看token 是否过期 */
    public String findUserByToken(String token);
}
