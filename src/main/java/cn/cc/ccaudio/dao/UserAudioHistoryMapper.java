package cn.cc.ccaudio.dao;

import cn.cc.ccaudio.dto.UserAudioHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAudioHistoryMapper {
    //1.增加历史
    //2.更新历史
    //3.查询历史
    // 极限情况下可能会有的数据 用户数*文件数

    // 数据量太大，设计为json？但是查询排序又会出问题，
    // 每个人只保存十条?


    // 用户名和文件名组合唯一，先加个索引

    int insertHistory(UserAudioHistory userAudioHistory);

    int updateHistory(UserAudioHistory userAudioHistory);

    int queryHistoryCount(String userName);

    List<UserAudioHistory> queryHistory(@Param("userName")String userName,
                                               @Param("fileRealName")String fileRealName);
}
