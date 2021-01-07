package cn.cc.ccaudio.service;

import cn.cc.ccaudio.dto.AudioFilePath;

public interface AudioFilePathService {
    AudioFilePath findSequence(String id);
    int modifySequence(AudioFilePath audioFilePath);
}
