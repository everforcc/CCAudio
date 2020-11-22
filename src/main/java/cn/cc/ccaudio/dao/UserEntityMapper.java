package cn.cc.ccaudio.dao;

import cn.cc.ccaudio.bean.UserEntity;
import org.apache.ibatis.annotations.*;

//@Mapper
public interface UserEntityMapper {

   @Select("SELECT * FROM userentity e WHERE e.`username`= #{userName}  AND e.`effect`=1  ")
   public UserEntity selectByUserName(String userName);

   @Options(useGeneratedKeys = true,keyColumn = "id")
   @Insert("insert into userentity(username) values (#{username})")
   public int insertUserEntity(UserEntity userEntity);

   @Update("update userentity e set e.token=#{token} where e.username=#{username}")
   public int updateTokenByUserName(UserEntity userEntity);

   @Delete("delete from userentity e where e.username=#{username}")
   public int deleteByUserName(UserEntity userEntity);
}
