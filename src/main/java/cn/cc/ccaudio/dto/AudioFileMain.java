package cn.cc.ccaudio.dto;

import java.util.Date;

/**
 * 文件信息
 */
public class AudioFileMain {
    private static final long serialVersionUID = 1L;

    private int id;

    private String realName;
    private String name;
    private String path;
    private String parent;
    private String length;
    private long size;
    private String author;
    private Date uploadTime = new Date();
    private int effect = 1;
    private String remark;
    private String mark;

    public AudioFileMain() {
    }

    public AudioFileMain(int id, String realName, String name, String path, String parent, String length, long size, String author, Date uploadTime, int effect, String remark, String mark) {
        this.id = id;
        this.realName = realName;
        this.name = name;
        this.path = path;
        this.parent = parent;
        this.length = length;
        this.size = size;
        this.author = author;
        this.uploadTime = uploadTime;
        this.effect = effect;
        this.remark = remark;
        this.mark = mark;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AudioFileMain{" +
                "id=" + id +
                ", realName='" + realName + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", parent='" + parent + '\'' +
                ", length='" + length + '\'' +
                ", size=" + size +
                ", author='" + author + '\'' +
                ", uploadTime=" + uploadTime +
                ", effect=" + effect +
                ", remark='" + remark + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}
