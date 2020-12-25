package cn.cc.ccaudio.service;

import cn.cc.ccaudio.dto.AudioFilePath;

public interface AudioFilePathService {
    public AudioFilePath findSequence(String id);
    public int modifySequence(AudioFilePath audioFilePath);
}
