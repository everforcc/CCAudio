package cn.cc.ccaudio.dao;

import cn.cc.ccaudio.dto.AudioFilePath;

public interface AudioFilePathMapper {

    public int updateSequence(AudioFilePath audioFilePath);

    public AudioFilePath querySequence(String id);

}
