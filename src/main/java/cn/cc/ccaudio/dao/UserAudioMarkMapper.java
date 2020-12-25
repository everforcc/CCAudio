package cn.cc.ccaudio.dao;

import cn.cc.ccaudio.dto.UserAudioMark;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAudioMarkMapper {

    public int insertMark(UserAudioMark userAudioHistory);

    public int updateMark(UserAudioMark userAudioHistory);

    public int queryMarkCount(String userName);

    public List<UserAudioMark> queryMark(@Param("userName")String userName,
                                            @Param("fileRealName")String fileRealName);

}
