package cn.cc.ccaudio.dao;

import cn.cc.ccaudio.dto.UserAudioMark;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAudioMarkMapper {

    int insertMark(UserAudioMark userAudioHistory);

    int updateMark(UserAudioMark userAudioHistory);

    int queryMarkCount(String userName);

    List<UserAudioMark> queryMark(@Param("userName")String userName,
                                            @Param("fileRealName")String fileRealName);

}
