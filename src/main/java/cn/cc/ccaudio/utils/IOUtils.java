package cn.cc.ccaudio.utils;

import cn.cc.ccaudio.constant.Constant_File;
import cn.cc.ccaudio.constant.StatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.Encoder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IOUtils {

    private final static Logger logger = LoggerFactory.getLogger(IOUtils.class);

    /*  上传保存文件 */
    public static String saveFile(MultipartFile file, String dir,String rename) throws IOException {
        logger.info(Constant_File.filePath  + dir  + rename);
        byte[] bytes = file.getBytes();
        // 文件信息存数据库, 主表存文件数量
        File fileDir = new File(Constant_File.filePath + dir);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        Path path = Paths.get(Constant_File.filePath + dir + rename);
        Files.write(path, bytes);
        logger.info(Constant_File.uploadSuccess);
        if(rename.endsWith(".mp3")){
            return mp3Time(Constant_File.filePath + dir + rename);
        }else {
            return "";
        }
    }

    public static void delFile(String fileMapName){
        if(StringUtils.isNotEmpty(fileMapName)) {
            boolean flag;
            File file = new File(Constant_File.filePath + fileMapName);
            if (file.exists()) {
                flag = file.delete();
                logger.info("文件已经回滚");
            } else {
                logger.info("文件不存在");
            }
        }
        logger.info("文件不存在");
    }

    public static String mp3Time(String path){
        String strLen="";
        try {
            MP3File file = new MP3File(path);
            MP3AudioHeader audioHeader = (MP3AudioHeader)file.getAudioHeader();
            strLen = audioHeader.getTrackLengthAsString();
            System.out.println(strLen);
            int intLen = audioHeader.getTrackLength();
            System.out.println(intLen);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();
        }
        return strLen;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    //ReturnObj returnObj = new ReturnObj();
    /* 文件加密并返回，但是目前台太慢了，不用 */
    public static String loadByteEncode(String filePath){
        logger.info("加载filePath:" + filePath);
        // 打印 日志
        File file = new File(Constant_File.music_1);
        String str = null;
        try {
            FileInputStream inputFile = null;
            inputFile = new FileInputStream(file);
            //byte[] buffer = new byte[(int) (file.length() * 0.5)]; 试听，只给一半
            byte[] buffer = new byte[(int) (file.length())]; // 这个length就是Range
            inputFile.read(buffer);
            inputFile.close();
            str = new BASE64Encoder().encode(buffer);
        }catch (FileNotFoundException e){
            logger.warn(filePath + Constant_File.fileNotExist + e.toString());
            e.printStackTrace();
            // 文件未找到，这时候随机给返回一个? 还是咋处理呢
            return new ReturnObj(StatusEnum.Status007,Constant_File.fileNotExist).toString();
        } catch (IOException e) {
            logger.warn(filePath + Constant_File.fileLoadException + e.toString());
            // 读取流异常
            e.printStackTrace();
            return new ReturnObj(StatusEnum.Status007,Constant_File.fileLoadException).toString();
        }
        logger.info("成功加载filePath:" + filePath);
        return new ReturnObj(StatusEnum.Status200,str).toString();
    }

    public static String loadStream(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 这个位置有点问题，需要断点调整，但是目前不用这个方法了
        try {
            String range = request.getHeader("Range");
            String[] rs = range.split("\\=");
            range = rs[1].split("\\-")[0];

            File file = new File(Constant_File.music_1);
            OutputStream os = response.getOutputStream();
            FileInputStream fis = new FileInputStream(file);
            long length = file.length();
            int count = 0;  //播放进度
            //int percent = (int) (length * 0.4); //播放百分比.这里我们控制仅播放40%
            int percent = (int) (length * 1.0);
            int irange = Integer.parseInt(range);
            length = length - irange;

            response.addHeader("Accept-Ranges", "bytes");
            response.addHeader("Content-Length", length + "");
            response.addHeader("Content-Range", "bytes " + range + "-" + length + "/" + length);
            response.addHeader("Content-Type", "audio/mpeg;charset=UTF-8");

            int len = 0;
            byte[] b = new byte[1024];
            while ((len = fis.read(b)) != -1) {
                os.write(b, 0, len);
                count += len;
                if (count >= percent) {
                    break;
                }
            }
            System.out.println("count: " + count + ", percent: " + percent);
            fis.close();
            os.close();

        } catch (FileNotFoundException e) {
            logger.info("文件名:" + Constant_File.fileNotExist);
            e.printStackTrace();
            return Constant_File.fileNotExist;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
