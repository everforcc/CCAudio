package cn.cc.ccaudio.dao;

import cn.cc.ccaudio.dto.AudioSystem;

/**
 * @author c.c.
 * @date 2021/2/14
 */
public interface AudioSystemMapper {
    // 插入 更新 查询
    void insertAudioSystem(AudioSystem audioSystem);

    int queryFileCount();

    int updateAudioSystem(AudioSystem audioSystem);

    AudioSystem queryEffect();
}
