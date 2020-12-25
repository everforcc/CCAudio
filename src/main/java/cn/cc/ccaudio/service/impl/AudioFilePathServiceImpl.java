package cn.cc.ccaudio.service.impl;

import cn.cc.ccaudio.dao.AudioFilePathMapper;
import cn.cc.ccaudio.dto.AudioFilePath;
import cn.cc.ccaudio.service.AudioFilePathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("audioFilePathService")
@Transactional // 事务的注解
public class AudioFilePathServiceImpl implements AudioFilePathService {

    // 还没用到

    @Autowired
    AudioFilePathMapper audioFilePathMapper;

    @Override
    public AudioFilePath findSequence(String id) {

        return audioFilePathMapper.querySequence(id);
    }

    @Override
    public int modifySequence(AudioFilePath audioFilePath) {
        int result = audioFilePathMapper.updateSequence(audioFilePath);
        return result;
    }
}
