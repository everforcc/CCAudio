package cn.cc.ccaudio.service;

import cn.cc.ccaudio.utils.ReturnObj;

public interface UserAudioHistoryService {

    void modifyUserAudioHistory(String userName,String fileName);

    void addUserAudioHistory(String userName,String fileName);

    ReturnObj findUserAudioHistory(String userName, String fileName, int currentPage, int size);
}
