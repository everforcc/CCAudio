package cn.cc.ccaudio.service.impl;

import cn.cc.ccaudio.constant.StatusEnum;
import cn.cc.ccaudio.dao.AudioFileTypeMapper;
import cn.cc.ccaudio.dto.AudioFileType;
import cn.cc.ccaudio.service.AudioFileTypeService;
import cn.cc.ccaudio.utils.ReturnObj;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author c.c.
 * @date 2021/2/8
 */
@Service("AudioFileTypeService")
@Transactional
public class AudioFileTypeServiceImpl implements AudioFileTypeService {

    @Resource
    AudioFileTypeMapper audioFileTypeMapper;

    @Override
    public ReturnObj findUserAudioDir(String parentType, int currentPage, int size) {
        ReturnObj returnObj = new ReturnObj();
        List<AudioFileType> audioFileTypeList = audioFileTypeMapper.queryAudioTypeList(parentType,(currentPage - 1),size);
        if(audioFileTypeList!=null&&audioFileTypeList.size()!=0){
            int totalNum = audioFileTypeMapper.queryTypeCount(parentType);
            int pageNum = (int) Math.ceil(totalNum/size);
            returnObj.setStatusEnum(StatusEnum.Status200);
            returnObj.setData(totalNum,pageNum,audioFileTypeList);
        }else {
            returnObj.setStatusEnum(StatusEnum.Status000);
        }
        return returnObj;
    }

    @Override
    public ReturnObj findUserAudioAllDir() {
        ReturnObj returnObj = new ReturnObj();
        List<AudioFileType> audioFileParentTypeList = audioFileTypeMapper.queryAudioTypeList("parent",0,999);
        List<AudioFileType> audioFileChildTypeList = audioFileTypeMapper.queryAudioTypeList("child",0,999);

        Map<String,List<AudioFileType>> map = new HashMap<>();
        map.put("parent",audioFileParentTypeList);
        map.put("child",audioFileChildTypeList);
        returnObj.setStatusEnum(StatusEnum.Status200);
        returnObj.setData(audioFileParentTypeList.size(),audioFileChildTypeList.size(),map);

        return returnObj;
    }

}
