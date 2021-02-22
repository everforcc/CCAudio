package cn.cc.ccaudio.service.impl;

import cn.cc.ccaudio.constant.StatusEnum;
import cn.cc.ccaudio.dao.AudioSystemMapper;
import cn.cc.ccaudio.dto.AudioSystem;
import cn.cc.ccaudio.service.AudioSystemService;
import cn.cc.ccaudio.utils.IOUtils;
import cn.cc.ccaudio.utils.ReturnObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.text.DecimalFormat;

/**
 * @author c.c.
 * @date 2021/2/14
 */
@Service("AudioSystemService")
public class AudioSystemServiceImpl implements AudioSystemService {

    Logger logger = LoggerFactory.getLogger(getClass());
    static final DecimalFormat df = new DecimalFormat("0000");
    @Resource
    AudioSystemMapper audioSystemMapper;

    @Override
    public ReturnObj saveAudioSystem(MultipartFile[] fileList) {
        ReturnObj returnObj = new ReturnObj();
        AudioSystem audioSystem = new AudioSystem();
        String path = "cover";
        try {
            String rename = "";
            MultipartFile file = fileList[0];
            String realName = file.getOriginalFilename();
            logger.info("开始保存文件:" + realName);
            int next = audioSystemMapper.queryFileCount() + 1;

            rename = df.format(next) + realName.substring(realName.lastIndexOf("."),realName.length());

            String mp3Time = IOUtils.saveFile(file, path + File.separator, rename);
            audioSystem.setPath("1");
            audioSystem.setRealName(realName);
            audioSystem.setEffect("1");
            audioSystem.setName(rename);
            audioSystem.setPath(path);

            audioSystemMapper.insertAudioSystem(audioSystem);
            returnObj.setStatusEnum(StatusEnum.Status200);
        }catch (Exception e){
            returnObj.setCode("000");
            returnObj.setValue(e.toString());
        }
        return returnObj;
    }

    @Override
    public ReturnObj findAudioSystem() {
        ReturnObj returnObj = new ReturnObj();
        try {
            AudioSystem audioSystem = audioSystemMapper.queryEffect();
            returnObj.setStatusEnum(StatusEnum.Status200);
            returnObj.setData(audioSystem);
        }catch (Exception e){
            returnObj.setCode("000");
            returnObj.setValue(e.toString());
        }
        return returnObj;
    }
}
