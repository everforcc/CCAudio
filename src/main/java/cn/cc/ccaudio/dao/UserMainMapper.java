package cn.cc.ccaudio.dao;

import cn.cc.ccaudio.dto.UserMain;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface UserMainMapper {

    // @Options(useGeneratedKeys = true,keyColumn = "id")

    /* 检查用户名是否存在,登录和注册的时候用 */
    UserMain checkUserName(String userName);
    /* 登录用 */
    UserMain queryForLogin(@Param("userName") String userName,@Param("passWord") String passWord);

    List<UserMain> queryUserList(@Param("page")int page,@Param("like")String like);

    UserMain queryUserByToken(String token);

    UserMain queryUserByID(int id);

    int queryUserCount(@Param("like")String like);

    /* 登录时设置token */
    int updateTokenByUserName(UserMain userMain);
    /* 修改用户信息 */
    int updateUserMsgByID(UserMain userMain);


    int insertUser(UserMain userMain);


    int deleteByUserName(UserMain userMain);

}
