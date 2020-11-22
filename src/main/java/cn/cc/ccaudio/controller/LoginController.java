package cn.cc.ccaudio.controller;

import cn.cc.ccaudio.constant.StatusEnum;
import cn.cc.ccaudio.service.UserMainService;
import cn.cc.ccaudio.utils.Result;
import cn.cc.ccaudio.utils.ReturnObj;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    /**
     * 1.用户名校验
     * 2.登录
     */

    @Autowired
    UserMainService userMainService;

    // 校验用户名是否存在
    @GetMapping("/check")
    public String checkUserName(@Param("userName") String userName){
        if(StringUtils.isNotEmpty(userName)){
            return userMainService.checkUserName(userName);
        }
        ReturnObj returnObj = new ReturnObj(StatusEnum.Status006);
        return returnObj.toString();
    }



    // 加密最后有时间再做

    // 登录
    @GetMapping("/submit")
    public String login(@Param("userName") String userName,@Param("passWord") String passWord){

        // 查询用户存在并，生成token返回
        System.out.println(userName);
        if(StringUtils.isNotEmpty(userName)&&StringUtils.isNotEmpty(passWord)){
            return userMainService.queryForLogin(userName,passWord);
        }
        ReturnObj returnObj = new ReturnObj(StatusEnum.Status006);
        return returnObj.toString();
    }

    @GetMapping("/token")
    public String ckeckToken(@Param("token") String token){

        // 查询用户存在并，生成token返回
        System.out.println("------------"+token);
        if(StringUtils.isNotEmpty(token)){
            return userMainService.findUserByToken(token);
        }
        ReturnObj returnObj = new ReturnObj(StatusEnum.Status006);
        return returnObj.toString();
    }

}
