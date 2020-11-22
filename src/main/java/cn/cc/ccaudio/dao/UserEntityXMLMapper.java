package cn.cc.ccaudio.dao;

import cn.cc.ccaudio.bean.UserEntity;

//@Mapper
public interface UserEntityXMLMapper {
   public UserEntity selectByUserName(String userName);
}
