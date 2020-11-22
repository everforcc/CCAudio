package cn.cc.ccaudio.service.impl;

import cn.cc.ccaudio.constant.StatusEnum;
import cn.cc.ccaudio.dao.UserMainMapper;
import cn.cc.ccaudio.dto.UserMain;
import cn.cc.ccaudio.service.UserMainService;
import cn.cc.ccaudio.utils.ReturnObj;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service("UserMainServiceImpl")
public class UserMainServiceImpl implements UserMainService {

    @Autowired
    private UserMainMapper userMainMapper;
    @Override
    public String checkUserName(String userName) {
        ReturnObj returnObj = new ReturnObj();
        UserMain userMain = userMainMapper.checkUserName(userName);
        if(userMain==null){
            // 没有这个用户
            returnObj.setStatusEnum(StatusEnum.Status005);
            return returnObj.toString();
        }else {
            returnObj.setStatusEnum(StatusEnum.Status200);
            return returnObj.toString();
        }
    }

    @Override
    public String queryForLogin(String userName,String passWord) {
        ReturnObj returnObj = new ReturnObj();
        //先检查用户是否存在，如果存在那么生成token，如果不存在就返回密码错误
        UserMain userMain = userMainMapper.queryForLogin(userName,passWord);
        if(userMain==null){
            returnObj.setStatusEnum(StatusEnum.Status003);
            return returnObj.toString();
        }else {
            String token = UUID.randomUUID().toString();
            userMain.setToken(token);
            userMainMapper.updateTokenByUserName(userMain);
            // 返回token
            Map<String,String> map = new HashMap<>();
            map.put("token",token);

            returnObj.setStatusEnum(StatusEnum.Status200);
            returnObj.setData(map);
            return JSON.toJSONString(returnObj);
        }
    }

    @Override
    public String findUserByToken(String token) {
        ReturnObj returnObj = new ReturnObj();
        //先检查用户是否存在，如果存在那么生成token，如果不存在就返回密码错误
        UserMain userMain = userMainMapper.findUserByToken(token);
        if(userMain==null){
            returnObj.setStatusEnum(StatusEnum.Status003);
            return returnObj.toString();
        }else {
            returnObj.setStatusEnum(StatusEnum.Status200);
            return JSON.toJSONString(returnObj);
        }
    }
}

