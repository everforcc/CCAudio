package cn.cc.ccaudio.dto;

public class AudioFilePath {
    private static final long serialVersionUID = 1L;
    // 管理文件路径，名称
    private int id;

    private String fileParent;

    private int fileDir;

    private int fileName;

    public AudioFilePath() {
    }

    public AudioFilePath(String fileParent, int fileDir, int fileName) {
        this.fileParent = fileParent;
        this.fileDir = fileDir;
        this.fileName = fileName;
    }

    public String getFileParent() {
        return fileParent;
    }

    public void setFileParent(String fileParent) {
        this.fileParent = fileParent;
    }

    public int getFileDir() {
        return fileDir;
    }

    public void setFileDir(int fileDir) {
        this.fileDir = fileDir;
    }

    public int getFileName() {
        return fileName;
    }

    public void setFileName(int fileName) {
        this.fileName = fileName;
    }
}

