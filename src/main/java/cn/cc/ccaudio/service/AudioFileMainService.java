package cn.cc.ccaudio.service;

import org.springframework.web.multipart.MultipartFile;

public interface AudioFileMainService {

    public String findAudio(String like,int currentPage,int size);

    public String saveAudioFile(MultipartFile[] fileList);

    public String findAudioByRealName(String fileName,String userName);
}
