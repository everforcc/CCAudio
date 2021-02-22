package cn.cc.ccaudio.service;

import cn.cc.ccaudio.dto.AudioSystem;
import cn.cc.ccaudio.utils.ReturnObj;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author c.c.
 * @date 2021/2/14
 */
public interface AudioSystemService {

    ReturnObj saveAudioSystem(MultipartFile[] fileList);

    ReturnObj findAudioSystem();
}
