package cn.cc.ccaudio.dao;

import cn.cc.ccaudio.dto.AudioFileMain;
import cn.cc.ccaudio.exception.DefiFileSaveException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AudioFileMainMapper {

    public List<AudioFileMain> queryAudioLike(@Param("like")String like,
                                         @Param("start")int start,
                                         @Param("size")int size);

    public List<AudioFileMain> queryForHistory(@Param("userName")String userName,
                                               @Param("fileRealName")String fileRealName,
                                              @Param("start")int start,
                                              @Param("size")int size);

    public List<AudioFileMain> queryForMark(@Param("userName")String userName,
                                               @Param("fileRealName")String fileRealName,
                                               @Param("start")int start,
                                               @Param("size")int size);

    public int queryFileCount();

    public List<AudioFileMain> queryAudioByRealName(@Param("fileName")String fileName);

    public void insertAudioFile(AudioFileMain audioFileMain) throws DefiFileSaveException;

}
