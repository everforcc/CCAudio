package cn.cc.ccaudio.dao;

import cn.cc.ccaudio.dto.AudioFilePath;

public interface AudioFilePathMapper {

    int updateSequence(AudioFilePath audioFilePath);

    AudioFilePath querySequence(String id);

}
