package cn.cc.ccaudio.service.impl;

import cn.cc.ccaudio.constant.Constant_Common;
import cn.cc.ccaudio.constant.StatusEnum;
import cn.cc.ccaudio.dao.AudioFileMainMapper;
import cn.cc.ccaudio.dao.UserAudioHistoryMapper;
import cn.cc.ccaudio.dto.AudioFileMain;
import cn.cc.ccaudio.dto.UserAudioHistory;
import cn.cc.ccaudio.service.UserAudioHistoryService;
import cn.cc.ccaudio.utils.ReturnObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userAudioHistoryService")
@Transactional // 事务的注解
public class UserAudioHistoryServiceImpl implements UserAudioHistoryService {

    @Autowired
    UserAudioHistoryMapper userAudioHistoryMapper;
    @Autowired
    AudioFileMainMapper audioFileMainMapper;
    @Override
    public void modifyUserAudioHistory(String userName, String fileName) {
        // userAudioHistoryMapper.queryHistory()
    }

    @Override
    public void addUserAudioHistory(String userName, String fileName) {

    }

    // 查询历史
    @Override
    public String findUserAudioHistory(String userName, String fileName,int currentPage,int size) {
        ReturnObj returnObj;
        List<AudioFileMain> audioFileMainList = audioFileMainMapper.queryForHistory(userName,fileName,(currentPage - 1) * size,size);
        if(audioFileMainList==null){
            returnObj = new ReturnObj(StatusEnum.Status997);
        }else {
            int totalNum = userAudioHistoryMapper.queryHistoryCount(userName);
            int pageNum = (int) Math.ceil(totalNum/size);
            returnObj = new ReturnObj(StatusEnum.Status200);
            returnObj.setData(totalNum,pageNum,audioFileMainList);
        }
        return returnObj.toString();
    }
}
