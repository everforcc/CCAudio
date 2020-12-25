package cn.cc.ccaudio.dto;

import java.util.Date;

// 用户收藏
public class UserAudioMark {
    private static final long serialVersionUID = 1L;

    private int id ;

    private String userName;

    private String fileRealName ;

    private Date markDate = new Date();

    private int mark;

    public UserAudioMark() {
    }

    public UserAudioMark(String userName, String fileRealName, int mark) {
        this.userName = userName;
        this.fileRealName = fileRealName;
        this.mark = mark;
    }

    public UserAudioMark(String userName, String fileRealName, Date markDate, int mark) {
        this.userName = userName;
        this.fileRealName = fileRealName;
        this.markDate = markDate;
        this.mark = mark;
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

    public Date getmarkDate() {
        return markDate;
    }

    public void setmarkDate(Date markDate) {
        this.markDate = markDate;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

}
