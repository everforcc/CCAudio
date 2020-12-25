package cn.cc.ccaudio.controller;

import cn.cc.ccaudio.constant.StatusEnum;
import cn.cc.ccaudio.dto.UserMain;
import cn.cc.ccaudio.service.UserMainService;
import cn.cc.ccaudio.utils.ReturnObj;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author c.c.
 * @date 2020/12/24
 */
@RestController
@RequestMapping("/user")
public class UserController {

    // 所有controller 增加 非空 校验

    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    UserMainService userMainService;

    // 统一在 controller 处理返回 ReturnObj

    /**
     * 1.新增用户
     * 2.修改用户
     */

    @GetMapping("/addUser")
    public ReturnObj addUser(UserMain userMain){
        logger.info("新增用户开始 >>> ");
        ReturnObj returnObj = new ReturnObj();

        if(userMain!=null){
            logger.info("新增用户信息 >>> " + userMain.getName() + " " + userMain.getUserName() + " " + userMain.getPassWord() + " " + userMain.getRemark());
            if(StringUtils.isNotEmpty(userMain.getName())&&StringUtils.isNotEmpty(userMain.getUserName())&&StringUtils.isNotEmpty(userMain.getPassWord())){
                returnObj = userMainService.addUser(userMain);
            }else {
                returnObj = new ReturnObj(StatusEnum.Status998);
            }
        }else {
            returnObj = new ReturnObj(StatusEnum.Status998);
        }

        logger.info("新增用户结束 >>> ");

        return returnObj;
    }

    @GetMapping("/queryUserList")
    public ReturnObj queryUserList(int page,String like,String token){

        logger.info("查询用户开始 >>> ");
        ReturnObj returnObj = new ReturnObj();

        if(!userMainService.cc(token)){
            returnObj.setStatusEnum(StatusEnum.Status004);
            return returnObj;
        }

        if((page + "")!=null){
            logger.info("page >>> " + page);
            returnObj = userMainService.findUserList(page,like);
        }else {
            returnObj = new ReturnObj(StatusEnum.Status998);
        }

        logger.info("查询用户结束 >>> " + returnObj);

        return returnObj;
    }

    @GetMapping("modifyUser")
    public ReturnObj modifyUser(UserMain userMain){
        ReturnObj returnObj = new ReturnObj();
        logger.info("修改用户开始 >>> " + userMain.getId() + " >>> " + userMain.getPassWord());

        if(userMain != null){
           returnObj = userMainService.modifyUserMsgByID(userMain);
        }else {
            returnObj = new ReturnObj(StatusEnum.Status998);
        }
        logger.info("修改用户结束 >>> ");
        return returnObj;
    }


}
