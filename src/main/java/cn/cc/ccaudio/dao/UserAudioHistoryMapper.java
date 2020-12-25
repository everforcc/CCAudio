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

    public int insertHistory(UserAudioHistory userAudioHistory);

    public int updateHistory(UserAudioHistory userAudioHistory);

    public int queryHistoryCount(String userName);

    public List<UserAudioHistory> queryHistory(@Param("userName")String userName,
                                               @Param("fileRealName")String fileRealName);
}
