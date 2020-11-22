package cn.cc.ccaudio.dao;

import cn.cc.ccaudio.dto.UserMain;
import org.apache.ibatis.annotations.*;

public interface UserMainMapper {

    /* 检查用户名是否存在,登录和注册的时候用 */
    public UserMain checkUserName(String userName);

    /* 登录用 */
    public UserMain queryForLogin(@Param("userName") String userName,@Param("passWord") String passWord);

    public UserMain findUserByToken(String token);

    public int insertUserEntity(UserMain userMain);

    /* 登录时设置token */
    public int updateTokenByUserName(UserMain userMain);

    public int deleteByUserName(UserMain userMain);

}
