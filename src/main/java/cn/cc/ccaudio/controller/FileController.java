package cn.cc.ccaudio.controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@RestController
public class FileController {

    // 这种最保险，是加密的安全，就用这种了
    /*@RequestMapping("/getBase")
    @ResponseBody*/
    @GetMapping("/getBase")
    public String getBase(String fileName) throws Exception { // 传个参数过来，所有交互全部都用json来操作
        System.out.println(fileName);
        System.out.println("-----------------------------------");
        String filePaht ="";
        if("1".equals(fileName)) {
            filePaht = "F:\\resources\\4.音乐\\10.WYcache\\高梨康治 - 地狱少女.mp3";
        }else {
            filePaht = "E:\\bilibili\\io\\ffmpeg\\鹿鸣\\out.mp3";
        }
        File file = new File(filePaht);
        FileInputStream inputFile = new FileInputStream(file);
        //byte[] buffer = new byte[(int) (file.length() * 0.5)];
        byte[] buffer = new byte[(int) (file.length())]; // 这个length就是Range
        inputFile.read(buffer);
        inputFile.close();
        String str = new BASE64Encoder().encode(buffer);
        //System.out.println("长度： " + str.length());
        return str;
    }

    // 这个也可以，还需要理解
    @RequestMapping("/getBase2")
    public ModelAndView getAudio(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ModelAndView 是旧的

        // String range = request.getHeader("Range");
        /*System.out.println("range1-----:"+range);
        // 如果没有range 和请求头 就给404 不给数据
        String[] rs = range.split("\\=");
        range = rs[1].split("\\-")[0]; // 首个字段应该是0，不给分段的机会
        System.out.println("range2-----:"+range);*/
        File file = new File("E:\\bilibili\\io\\ffmpeg\\鹿鸣\\out.mp3");
        if (!file.exists()) throw new RuntimeException("音频文件不存在 --> 404");
        OutputStream os = response.getOutputStream();
        FileInputStream fis = new FileInputStream(file);
        long length = file.length();
        int count = 0;  //播放进度
        int percent = (int) (length * 1.0); //播放百分比.这里我们控制仅播放40%
        // int percent = (int) (length); // 也没必要给 转型，long也可以
        //int irange = Integer.parseInt(range);
        //length = length - irange;
        System.out.println("length = length - irange:"+length);

        response.addHeader("Accept-Ranges", "bytes");
        response.addHeader("Content-Length", length + "");
        //response.addHeader("Content-Range", "bytes " + range + "-" + length + "/" + length);
        response.addHeader("Content-Type", "audio/mpeg;charset=UTF-8");

        //System.out.println("-----range3:"+range);
        System.out.println("-----length4:"+length);

        int len = 0;
        byte[] b = new byte[1024];
        while ((len = fis.read(b)) != -1) {
            os.write(b, 0, len);
            // count += len;
            // 试听功能
            /*if (count >= percent) {
                break;
            }*/
        }
        System.out.println("-----count: " + count + ", percent: " + percent);
        fis.close();
        os.close();
        return null;
    }

}
