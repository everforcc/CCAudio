package cn.cc.ccaudio.dto;

/**
 * @author c.c.
 * @date 2021/2/11
 */
public class AudioSystem {
    private static final long serialVersionUID = 1L;
    // 封面
    private String type;
    // 文件路径
    private String path;
    // 真实名字
    private String realName;
    // 重置名称
    private String name;
    // 是否有效
    private String effect;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}
