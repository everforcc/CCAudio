package cn.cc.ccaudio.controller;

import cn.cc.ccaudio.service.AudioSystemService;
import cn.cc.ccaudio.utils.ReturnObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author c.c.
 * @date 2021/2/14
 */
@RestController
@RequestMapping("/cover")
public class CoverController {

    @Autowired
    AudioSystemService audioSystemService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    // 上传一张图片
    @PostMapping("/upload")
    public ReturnObj uploadPic(@RequestParam("file") MultipartFile[] file){
        ReturnObj returnObj = new ReturnObj();
        returnObj = audioSystemService.saveAudioSystem(file);
        logger.info("uploadPic:" + returnObj);
        return returnObj;
    }

    @GetMapping("/getCover")
    public ReturnObj getCover(){
        ReturnObj returnObj = new ReturnObj();
        returnObj = audioSystemService.findAudioSystem();
        logger.info("getCover:" + returnObj);
        return returnObj;
    }

}
