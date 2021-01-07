package cn.cc.ccaudio.dao;

import cn.cc.ccaudio.dto.AudioFileMain;
import cn.cc.ccaudio.exception.DefiFileSaveException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AudioFileMainMapper {

    List<AudioFileMain> queryAudioLike(@Param("like")String like,
                                         @Param("start")int start,
                                         @Param("size")int size);

    List<AudioFileMain> queryForHistory(@Param("userName")String userName,
                                               @Param("fileRealName")String fileRealName,
                                              @Param("start")int start,
                                              @Param("size")int size);

    List<AudioFileMain> queryForMark(@Param("userName")String userName,
                                               @Param("fileRealName")String fileRealName,
                                               @Param("start")int start,
                                               @Param("size")int size);

    int queryFileCount();

    List<AudioFileMain> queryAudioByRealName(@Param("fileName")String fileName);

    void insertAudioFile(AudioFileMain audioFileMain) throws DefiFileSaveException;

    int updateAudioFileMain(AudioFileMain audioFileMain);
}
