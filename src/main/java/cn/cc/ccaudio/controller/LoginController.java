package cn.cc.ccaudio.controller;

import cn.cc.ccaudio.constant.StatusEnum;
import cn.cc.ccaudio.service.UserMainService;
import cn.cc.ccaudio.utils.Result;
import cn.cc.ccaudio.utils.ReturnObj;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 1.用户名校验
     * 2.登录
     */

    @Autowired
    UserMainService userMainService;

    // 校验用户名是否存在
    @GetMapping("/check")
    public ReturnObj checkUserName(@Param("userName") String userName){
        logger.info("校验用户名 start:" + userName);
        ReturnObj returnObj = new ReturnObj(StatusEnum.Status006);
        if(StringUtils.isNotEmpty(userName)){
            returnObj = userMainService.checkUserName(userName);
        }
        logger.info("校验用户名 end:" + returnObj);
        return returnObj;
    }

    // 加密最后有时间再做

    // 登录
    @GetMapping("/submit")
    public ReturnObj login(@Param("userName") String userName,@Param("passWord") String passWord){

        logger.info("登录 start >>> : userName:" + userName + ",passWord:" + passWord);

        ReturnObj returnObj = new ReturnObj(StatusEnum.Status006);
        // 查询用户存在并，生成token返回
        System.out.println(userName);
        if(StringUtils.isNotEmpty(userName)&&StringUtils.isNotEmpty(passWord)){
            returnObj = userMainService.findForLogin(userName,passWord);
        }

        logger.info("登录 end >>> :" + returnObj);

        return returnObj;
    }

    @GetMapping("/token")
    public ReturnObj ckeckToken(@Param("token") String token){

        logger.info("校验token start >>> :" + token);

        ReturnObj returnObj = new ReturnObj(StatusEnum.Status006);
        // 查询用户存在并，生成token返回
        logger.info("------------"+token);
        if(StringUtils.isNotEmpty(token)){
            returnObj = userMainService.findUserByToken(token);
        }

        logger.info("校验token end >>> :" + returnObj);
        return returnObj;
    }

}
