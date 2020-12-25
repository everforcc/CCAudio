package cn.cc.ccaudio.controller;

import cn.cc.ccaudio.constant.Constant_Common;
import cn.cc.ccaudio.constant.Constant_File;
import cn.cc.ccaudio.constant.StatusEnum;
import cn.cc.ccaudio.service.AudioFileMainService;
import cn.cc.ccaudio.service.UserAudioHistoryService;
import cn.cc.ccaudio.service.UserAudioMarkService;
import cn.cc.ccaudio.utils.IOUtils;
import cn.cc.ccaudio.utils.ReturnObj;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;


@RestController
@RequestMapping("/file")
public class FileController {

    // 命名要统一
    // dao query service find
    // dao insert service add
    // dao update service modify
    // 要定义好所有情况的异常

    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    AudioFileMainService audioFileMainService;
    @Autowired
    UserAudioHistoryService userAudioHistoryService;
    @Autowired
    UserAudioMarkService userAudioMarkService;
    // 在controller里面做基础的数据校验和返回
    /*@PostMapping("/upload") //MultipartFile
    public String upload(@RequestParam("fileList") MultipartFile[] fileList,String fileType){

        logger.info("开始上传文件");
        //String result = IOUtils.saveFile(fileList,"dir/","rename.mp3");
        String result = audioFileMainService.saveAudioFile(fileList);
        logger.info("结束上传文件");
        System.out.println(result);
        //return "1";
        return result;
    }*/

    // 获取文件列表
    @GetMapping("/getFileList")
    public String getFileList(String userName,String fileName,int aduioType,int currentPage){

        //限定数字必须是数字的问题

        // 页面尺寸固定
        int size = 5;

        // fileName 也就是 like 字段
        logger.info("获取文件列表--userName>>" + userName + ",fileName>>" + fileName +",aduioType>>"+ aduioType + ",>>currentPage" + currentPage);
        // 在这里组织好文件信息返回给前端
        // aduioType 确定调用那个service
        ReturnObj returnObj = new ReturnObj(StatusEnum.Status999);
        String result = "";
        if(StringUtils.isNotEmpty(aduioType + "")){
           // 123 列表，收藏，历史
           if(aduioType==1){
           // 查询文件列表
               result = audioFileMainService.findAudio(fileName,currentPage,size);
           }else if(aduioType==2){
           //收藏列表
               result = userAudioMarkService.findUserAudioMark(userName,fileName,currentPage,size);
           }else if(aduioType==3){
           // 历史
               result = userAudioHistoryService.findUserAudioHistory(userName,fileName,currentPage,size);
           }else {
               returnObj.setData(Constant_Common.unknownType);
               result = returnObj.toString();
           }
        }else {
            returnObj.setData(Constant_Common.notAllowNull);
            result = returnObj.toString();
        }
        // 类型异常，应该不会，就返回查询参数错误
        logger.info("查询" + aduioType + "列表返回"+result);
        return result;
    }

    // 点击后传过来文件名和用户名
    @GetMapping("/getFilePath")
    public String getFilePath(String fileName,String userName){
        logger.info(userName + "获取文件路径:" + fileName);
        String result = "";
        //查询路径返回，记录播放历史
        if(StringUtils.isNotEmpty(fileName)&&StringUtils.isNotEmpty(userName)) {
            result = audioFileMainService.findAudioByRealName(fileName, userName);
        }else {
            ReturnObj returnObj = new ReturnObj(StatusEnum.Status998);
            returnObj.setData(Constant_Common.notAllowNull);
            result = returnObj.toString();
        }

        logger.info(userName + "返回数据" + result);
        return result;
    }

    // 收藏音频
    @GetMapping("/markFile")
    public String markFile(String userName,String fileName,int mark){
        logger.info("开始收藏,userName>>" + userName + ",fileName>>" + fileName + ",mark>>" + mark);
        // 数据校验
        String result = userAudioMarkService.addUserAudioMark(userName,fileName,mark);
        logger.info("收藏成功");
        ReturnObj returnObj = new ReturnObj();
        returnObj.setStatusEnum(StatusEnum.Status200);
        returnObj.setData("");// 设置文件的详细信息
        return result;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    // 这种最保险，是加密的安全，太慢，暂时先不用了
    /*@RequestMapping("/getBase")
    @ResponseBody*/
    @GetMapping("/getBase")
    public String getBase(String fileName) throws Exception { // 传个参数过来，所有交互全部都用json来操作
        logger.info("加密文件并返回");
        return IOUtils.loadByteEncode(fileName);
    }

    // 暂时不用了，还有点问题随后调整
    @RequestMapping("/getBase2")
    public ModelAndView getAudio(HttpServletRequest request, HttpServletResponse response) throws Exception {
        IOUtils.loadStream(request,response);
        return null;
    }

}
