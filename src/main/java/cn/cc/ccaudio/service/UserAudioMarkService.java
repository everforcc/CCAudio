package cn.cc.ccaudio.service;

public interface UserAudioMarkService {

    public void modifyUserAudioMark(String userName,String fileName);

    public String addUserAudioMark(String userName,String fileName,int mark);

    public String findUserAudioMark(String userName,String fileName,int currentPage,int size);
}
