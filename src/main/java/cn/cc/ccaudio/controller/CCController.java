package cn.cc.ccaudio.controller;

import cn.cc.ccaudio.service.AudioFileMainService;
import cn.cc.ccaudio.service.UserAudioHistoryService;
import cn.cc.ccaudio.service.UserAudioMarkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/cc")
public class CCController {
// 多加几个限制

    private final static Logger logger = LoggerFactory.getLogger(CCController.class);

    @Autowired
    AudioFileMainService audioFileMainService;
    @Autowired
    UserAudioHistoryService userAudioHistoryService;
    @Autowired
    UserAudioMarkService userAudioMarkService;
    // 在controller里面做基础的数据校验和返回
    @PostMapping("/upload") //MultipartFile
    public String upload(@RequestParam("fileList") MultipartFile[] fileList,String fileType){

        logger.info("开始上传文件:" + fileType );
        // 校验用户名必须为 cc

        if("1".equals(fileType)){

        }else if("2".equals(fileType)){

        }else {
            // 系统故障
        }

        String result = "123";
        //String result = audioFileMainService.saveAudioFile(fileList);
        logger.info("结束上传文件");
        System.out.println(result);
        //return "1";
        return result;
    }

}
