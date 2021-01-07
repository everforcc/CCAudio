package cn.cc.ccaudio.service;

import cn.cc.ccaudio.dto.UserMain;
import cn.cc.ccaudio.utils.ReturnObj;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMainService {

    /* 校验用户名 */
    ReturnObj checkUserName(String userName);

    /* 登录校验 */
    ReturnObj findForLogin(String userName,String passWord);

    ReturnObj findUserList(int page,String like);

    /* 查看token 是否过期 */
    ReturnObj findUserByToken(String token);

    boolean cc(String token);

    ReturnObj modifyUserMsgByID(UserMain userMain);

    ReturnObj addUser(@Param("userMain") UserMain userMain);
}
