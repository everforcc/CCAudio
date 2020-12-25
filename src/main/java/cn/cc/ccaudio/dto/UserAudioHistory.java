package cn.cc.ccaudio.dto;

import java.util.Date;

// 收听历史记录
public class UserAudioHistory {

    private static final long serialVersionUID = 1L;

    private int id ;

    private String userName;

    private String fileRealName ;

    private Date lastDate = new Date();

    private int listenCount;

    public UserAudioHistory() {
    }

    public UserAudioHistory(String userName, String fileRealName, int listenCount) {
        this.userName = userName;
        this.fileRealName = fileRealName;
        this.listenCount = listenCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFileRealName() {
        return fileRealName;
    }

    public void setFileRealName(String fileRealName) {
        this.fileRealName = fileRealName;
    }

    public int getListenCount() {
        return listenCount;
    }

    public void setListenCount(int listenCount) {
        this.listenCount = listenCount;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }



}
