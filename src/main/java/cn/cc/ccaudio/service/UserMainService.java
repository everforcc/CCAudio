package cn.cc.ccaudio.service;

import cn.cc.ccaudio.dto.UserMain;
import cn.cc.ccaudio.utils.ReturnObj;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMainService {

    /* 校验用户名 */
    public String checkUserName(String userName);

    /* 登录校验 */
    public String findForLogin(String userName,String passWord);

    public ReturnObj findUserList(int page,String like);

    /* 查看token 是否过期 */
    public String findUserByToken(String token);

    public boolean cc(String token);

    public ReturnObj modifyUserMsgByID(UserMain userMain);

    public ReturnObj addUser(@Param("userMain") UserMain userMain);
}
