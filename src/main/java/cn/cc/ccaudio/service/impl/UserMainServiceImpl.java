package cn.cc.ccaudio.service.impl;

import cn.cc.ccaudio.constant.StatusEnum;
import cn.cc.ccaudio.controller.CCController;
import cn.cc.ccaudio.dao.UserMainMapper;
import cn.cc.ccaudio.dto.UserMain;
import cn.cc.ccaudio.service.UserMainService;
import cn.cc.ccaudio.utils.ReturnObj;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service("userMainService")
@Transactional // 事务的注解
public class UserMainServiceImpl implements UserMainService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserMainMapper userMainMapper;

    @Override
    public ReturnObj checkUserName(String userName) {
        ReturnObj returnObj = new ReturnObj();
        UserMain userMain = userMainMapper.checkUserName(userName);
        if(userMain==null){
            // 没有这个用户
            returnObj.setStatusEnum(StatusEnum.Status005);
        }else {
            returnObj.setStatusEnum(StatusEnum.Status200);
        }
        return returnObj;
    }

    @Override
    public ReturnObj findForLogin(String userName,String passWord) {
        ReturnObj returnObj = new ReturnObj();
        //先检查用户是否存在，如果存在那么生成token，如果不存在就返回密码错误
        UserMain userMain = userMainMapper.queryForLogin(userName,passWord);
        if(userMain==null){
            returnObj.setStatusEnum(StatusEnum.Status003);
        }else {
            String token = UUID.randomUUID().toString();
            userMain.setToken(token);

            userMain.setTokenExpireDate(getNextDate());

            userMainMapper.updateTokenByUserName(userMain);
            // 返回token
            Map<String,String> map = new HashMap<>();
            map.put("token",token);
            map.put("name",userName);
            returnObj.setStatusEnum(StatusEnum.Status200);
            returnObj.setData(map);
        }
        return returnObj;
    }

    // 先这样
    private Date getNextDate(){
        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,365);// 默认一年
        date=calendar.getTime();
        return date;
    }

    @Override
    public ReturnObj findUserList(int page,String like) {
        List<UserMain> userMainList = userMainMapper.queryUserList(page,like);
        int total = userMainMapper.queryUserCount(like);

        ReturnObj returnObj = new ReturnObj();

        int pageNum = (int) Math.ceil((double)total/5);

        returnObj.setStatusEnum(StatusEnum.Status200);
        returnObj.setData(total,pageNum,userMainList);

        return returnObj;
    }

    @Override
    public ReturnObj findUserByToken(String token) {
        ReturnObj returnObj = new ReturnObj();
        //先检查用户是否存在，如果存在那么生成token，如果不存在就返回密码错误
        UserMain userMain = userMainMapper.queryUserByToken(token);
        if(userMain==null){
            returnObj.setStatusEnum(StatusEnum.Status003);
        }else {
            returnObj.setStatusEnum(StatusEnum.Status200);
        }
        return returnObj;
    }

    @Override
    public boolean cc(String token) {
        UserMain userMain = userMainMapper.queryUserByToken(token);
        if(1 == userMain.getId()&&"admin".equals(userMain.getUserName())){
           return true;
        }
        logger.warn(userMain.getId() + " >>> 攻击系统 " );
        return false;
    }

    @Override
    public ReturnObj modifyUserMsgByID(UserMain userMain) {
        ReturnObj returnObj = new ReturnObj(StatusEnum.Status998);
        //先检查用户是否存在，如果存在那么生成token，如果不存在就返回密码错误
        if (userMain != null && (userMain.getId() + "") != null) {
            // 用户存在
            UserMain userMainxists = userMainMapper.queryUserByID(userMain.getId());
            if (userMainxists != null) {
                int result = userMainMapper.updateUserMsgByID(userMain);
                if (result == 1) {
                    returnObj.setStatusEnum(StatusEnum.Status200);
                }
            }
        }
        return returnObj;
    }


    @Override
    public ReturnObj addUser(UserMain userMain) {
        ReturnObj returnObj = new ReturnObj();
        UserMain userMainExist = userMainMapper.checkUserName(userMain.getUserName());
        if(userMainExist==null){
            // 没有这个用户
            userMain.setTokenExpireDate(getNextDate());
            int result = userMainMapper.insertUser(userMain);
            System.out.println(result);
            returnObj.setStatusEnum(StatusEnum.Status200);
        }else {
            returnObj.setStatusEnum(StatusEnum.StatusUser200);
        }
        return returnObj;
    }
}

