package cn.cc.ccaudio.constant;

public class Constant_File {
    //  随后可以修改为配置文件

    // 文件路径
    public static String filePath;
    public static String fileLocalPath = "file:";
    public static String fileMapPath = "/resource/**"; //映射路径

    public static String uploadSuccess = "文件上传成功";

    public static String uploadFail = "文件上传失败";

    public static String fileNotExist = "文件不存在";

    public static String fileLoadException = "文件加载异常";

    public static String music_1 = "F:\\resources\\4.音乐\\10.WYcache\\高梨康治 - 地狱少女.mp3";

    static {
        if(System.getProperty("os.name").startsWith("Windows")){
            filePath = "E:/java/audio/";
        }else {
            filePath = "/tmp/audio/";
        }
    }

}
