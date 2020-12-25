package cn.cc.ccaudio.service.impl;

import cn.cc.ccaudio.constant.StatusEnum;
import cn.cc.ccaudio.dao.AudioFileMainMapper;
import cn.cc.ccaudio.dao.UserAudioMarkMapper;
import cn.cc.ccaudio.dto.AudioFileMain;
import cn.cc.ccaudio.dto.UserAudioMark;
import cn.cc.ccaudio.service.UserAudioMarkService;
import cn.cc.ccaudio.utils.ReturnObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service("userAudioMarkService")
@Transactional // 事务的注解
public class UserAudioMarkServiceImpl implements UserAudioMarkService {

    @Autowired
    UserAudioMarkMapper userAudioMarkMapper;
    @Autowired
    AudioFileMainMapper audioFileMainMapper;
    @Override
    public void modifyUserAudioMark(String userName, String fileName) {

    }

    @Override
    public String addUserAudioMark(String userName, String fileName,int mark) {
        // 标记 音乐
        List<UserAudioMark> userAudioMarkList = userAudioMarkMapper.queryMark(userName,fileName);
        UserAudioMark userAudioMark = new UserAudioMark();
        if(userAudioMarkList!=null&&userAudioMarkList.size()!=0){
            userAudioMark = userAudioMarkList.get(0);
            userAudioMark.setMark(mark);
            userAudioMark.setmarkDate(new Date());
            userAudioMarkMapper.updateMark(userAudioMark);
        }else {
            userAudioMark = new UserAudioMark(userName,fileName,mark);
            userAudioMark.setmarkDate(new Date());
            userAudioMarkMapper.insertMark(userAudioMark);
        }
        return new ReturnObj(StatusEnum.Status200).toString();
    }

    @Override
    public String findUserAudioMark(String userName, String fileName, int currentPage, int size) {
        ReturnObj returnObj;
        List<AudioFileMain> audioFileMainList = audioFileMainMapper.queryForMark(userName,fileName,(currentPage - 1) * size,size);
        if(audioFileMainList==null){
            returnObj = new ReturnObj(StatusEnum.Status997);
        }else {
            int totalNum = userAudioMarkMapper.queryMarkCount(userName);
            int pageNum = (int) Math.ceil(totalNum/size);
            returnObj = new ReturnObj(StatusEnum.Status200);
            returnObj.setData(totalNum,pageNum,audioFileMainList);
        }
        return returnObj.toString();
    }
}
