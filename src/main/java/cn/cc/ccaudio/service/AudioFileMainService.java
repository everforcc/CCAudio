package cn.cc.ccaudio.service;

import cn.cc.ccaudio.dto.AudioFileMain;
import cn.cc.ccaudio.utils.ReturnObj;
import org.springframework.web.multipart.MultipartFile;

public interface AudioFileMainService {

    ReturnObj findAudio(String like, int currentPage, int size);

    ReturnObj saveAudioFile(MultipartFile[] fileList,String type);

    ReturnObj findAudioByRealName(String fileName,String userName);

    ReturnObj modifyAudioByRealName(AudioFileMain audioFileMain);
}
