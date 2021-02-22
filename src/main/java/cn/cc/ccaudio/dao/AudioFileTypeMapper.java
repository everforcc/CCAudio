package cn.cc.ccaudio.dao;

import cn.cc.ccaudio.dto.AudioFileType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author c.c.
 * @date 2021/2/8
 */
public interface AudioFileTypeMapper {

    // 查询子集
    List<AudioFileType> queryAudioTypeList(@Param("parentType")String parentType,
                                           @Param("start")int start,
                                           @Param("size")int size);

    int queryTypeCount(String parentType);


    AudioFileType queryAduioType(@Param("parentType")String parentType,
                                 @Param("childType")String childType);

    void insertAduioType(@Param("parentType")String parentType,
                                 @Param("childType")String childType);
}
