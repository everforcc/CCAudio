package cn.cc.ccaudio.service;

import cn.cc.ccaudio.dto.AudioFileMain;
import cn.cc.ccaudio.utils.ReturnObj;
import org.springframework.web.multipart.MultipartFile;

public interface AudioFileMainService {

    ReturnObj findAudio(String like, int currentPage, int size,String parentTypeFileType);

    ReturnObj saveAudioFile(MultipartFile[] fileList,String type,String child);

    ReturnObj findAudioByRealName(String fileName,String userName);

    ReturnObj modifyAudioByRealName(AudioFileMain audioFileMain);

    ReturnObj deleteAudioFileMain(AudioFileMain audioFileMain);

}
