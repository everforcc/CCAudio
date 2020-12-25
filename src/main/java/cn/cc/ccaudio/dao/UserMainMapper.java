package cn.cc.ccaudio.dao;

import cn.cc.ccaudio.dto.UserMain;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface UserMainMapper {

    // @Options(useGeneratedKeys = true,keyColumn = "id")

    /* 检查用户名是否存在,登录和注册的时候用 */
    public UserMain checkUserName(String userName);
    /* 登录用 */
    public UserMain queryForLogin(@Param("userName") String userName,@Param("passWord") String passWord);

    public List<UserMain> queryUserList(@Param("page")int page,@Param("like")String like);

    public UserMain queryUserByToken(String token);

    public UserMain queryUserByID(int id);

    public int queryUserCount(@Param("like")String like);

    /* 登录时设置token */
    public int updateTokenByUserName(UserMain userMain);
    /* 修改用户信息 */
    public int updateUserMsgByID(UserMain userMain);


    public int insertUser(UserMain userMain);


    public int deleteByUserName(UserMain userMain);

}
