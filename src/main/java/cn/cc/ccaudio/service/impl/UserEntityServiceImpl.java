package cn.cc.ccaudio.service.impl;

import cn.cc.ccaudio.bean.UserEntity;
import cn.cc.ccaudio.dao.UserEntityXMLMapper;
import cn.cc.ccaudio.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserEntityServiceImpl")
public class UserEntityServiceImpl implements UserEntityService {
    @Autowired
    UserEntityXMLMapper userEntityXMLMapper;
    @Override
    public UserEntity selectUser(String userName) {
        return userEntityXMLMapper.selectByUserName(userName);
    }
}
