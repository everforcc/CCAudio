package cn.cc.ccaudio.service;

public interface UserAudioHistoryService {

    public void modifyUserAudioHistory(String userName,String fileName);

    public void addUserAudioHistory(String userName,String fileName);

    public String findUserAudioHistory(String userName,String fileName,int currentPage,int size);
}
