package cn.cc.ccaudio.service;

import cn.cc.ccaudio.utils.ReturnObj;

public interface UserAudioMarkService {

    void modifyUserAudioMark(String userName,String fileName);

    ReturnObj addUserAudioMark(String userName, String fileName, int mark);

    ReturnObj findUserAudioMark(String userName,String fileName,int currentPage,int size);
}
