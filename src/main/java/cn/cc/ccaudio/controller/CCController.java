package cn.cc.ccaudio.controller;

import cn.cc.ccaudio.constant.StatusEnum;
import cn.cc.ccaudio.dto.AudioFileMain;
import cn.cc.ccaudio.service.AudioFileMainService;
import cn.cc.ccaudio.service.UserAudioHistoryService;
import cn.cc.ccaudio.service.UserAudioMarkService;
import cn.cc.ccaudio.utils.ReturnObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/cc")
public class CCController {

    // 用户校验限制 cc
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AudioFileMainService audioFileMainService;
    @Autowired
    UserAudioHistoryService userAudioHistoryService;
    @Autowired
    UserAudioMarkService userAudioMarkService;
    // 在controller里面做基础的数据校验和返回
    @PostMapping("/upload") //MultipartFile
    public ReturnObj upload(@RequestParam("fileList") MultipartFile[] fileList, String fileType){
        ReturnObj returnObj = new ReturnObj();
        logger.info("开始上传文件 start >>> :" + fileType );
        // 校验用户名必须为 cc

        if("1".equals(fileType)||"2".equals(fileType)){
            returnObj = audioFileMainService.saveAudioFile(fileList,fileType);
        }else {
            // 系统故障
            returnObj.setStatusEnum(StatusEnum.Status998);
        }

        logger.info("结束上传文件 end >>> " + returnObj);

        return returnObj;
    }

    @GetMapping("/update")
    public ReturnObj updateFile(AudioFileMain audioFileMain){
        ReturnObj returnObj = new ReturnObj();
        logger.info("开始修改文件 start >>> :" + audioFileMain );
        if(returnObj!=null){
            logger.info( audioFileMain.toString() );
            returnObj = audioFileMainService.modifyAudioByRealName(audioFileMain);
        }else {
            returnObj.setStatusEnum(StatusEnum.Status998);
        }

        logger.info("结束修改文件 start >>> :" + returnObj );
        return returnObj;
    }

}
