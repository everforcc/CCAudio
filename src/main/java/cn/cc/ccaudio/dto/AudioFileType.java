package cn.cc.ccaudio.dto;

/**
 * @author c.c.
 * @date 2021/2/8
 */
public class AudioFileType {
    private static final long serialVersionUID = 1L;
    /* 当前类型 */
    private String fileType;
    /* 归属类型 */
    private String parentType;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }
}
