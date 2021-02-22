package cn.cc.ccaudio.service;

import cn.cc.ccaudio.utils.ReturnObj;

/**
 * @author c.c.
 * @date 2021/2/8
 */
public interface AudioFileTypeService {

    ReturnObj findUserAudioDir(String parentType, int currentPage, int size);

    ReturnObj findUserAudioAllDir();

}
