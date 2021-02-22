package cn.cc.ccaudio.controller;

import cn.cc.ccaudio.constant.Constant_Common;
import cn.cc.ccaudio.constant.StatusEnum;
import cn.cc.ccaudio.service.AudioFileMainService;
import cn.cc.ccaudio.service.AudioFileTypeService;
import cn.cc.ccaudio.service.UserAudioHistoryService;
import cn.cc.ccaudio.service.UserAudioMarkService;
import cn.cc.ccaudio.utils.IOUtils;
import cn.cc.ccaudio.utils.ReturnObj;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;


@RestController
@RequestMapping("/file")
public class FileController {

    // 要定义好所有情况的异常

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AudioFileMainService audioFileMainService;
    @Autowired
    UserAudioHistoryService userAudioHistoryService;
    @Autowired
    UserAudioMarkService userAudioMarkService;
    @Autowired
    AudioFileTypeService audioFileTypeService;

    // 获取文件列表
    @GetMapping("/getFileList")
    public ReturnObj getFileList(String userName,String fileName,int aduioType,int currentPage,String parentTypeFileType){ // 还得再加个目录
        //限定数字必须是数字的问题

        // 页面尺寸固定
        int size = 5;
        // fileName 也就是 like 字段
        logger.info("获取文件列表 start >>> :userName>>" + userName + ",fileName>>" + fileName +",aduioType>>"+ aduioType + ",currentPage>>" + currentPage);
        // 在这里组织好文件信息返回给前端
        ReturnObj returnObj = new ReturnObj(StatusEnum.Status999);

        // 儿童和家长分开，先修改文件吧,稍微麻烦点，查询收藏啥的，都要改

        if(StringUtils.isNotEmpty(aduioType + "")){
           // 123 列表，收藏，历史
           if(aduioType==1){
           // 查询文件列表
               returnObj = audioFileMainService.findAudio(fileName,currentPage,size,parentTypeFileType);
           }
           // 以下功能暂时不用
           else if(aduioType==2){
           //收藏列表
               returnObj = userAudioMarkService.findUserAudioMark(userName,fileName,currentPage,size);
           }else if(aduioType==3){
           // 历史
               returnObj = userAudioHistoryService.findUserAudioHistory(userName,fileName,currentPage,size);
           }else {
               returnObj.setData(Constant_Common.unknownType);
           }
        }else {
            returnObj.setData(Constant_Common.notAllowNull);
        }
        // 类型异常，应该不会，就返回查询参数错误
        logger.info("获取文件列表 end >>> :" + aduioType + ",列表返回,"+returnObj);
        return returnObj;
    }

    // 获取文件一级目录
    @GetMapping("/getFileDir")
    public ReturnObj getFileDir(String userName,String type,int currentPage){
        // 页面尺寸固定
        int size = 5;
        // fileName 也就是 like 字段
        logger.info("获取文件一级目录 start >>> :userName>>" + userName + ",type >> " + type + ",currentPage >> " + currentPage );
        // 在这里组织好文件信息返回给前端
        ReturnObj returnObj = new ReturnObj(StatusEnum.Status999);

        //两类, 1.父母必听; 2.儿童必听
        if(StringUtils.isNotEmpty(userName)&&StringUtils.isNotEmpty(type)){
            returnObj = audioFileTypeService.findUserAudioDir(type,currentPage,size);
        }

        logger.info("获取文件一级目录 end >>> :" + type + ",列表返回,"+returnObj);
        return returnObj;
    }

    // 获取文件二级目录
    @GetMapping("/getFileChildDir")
    public ReturnObj getFileChildDir(){
        // 页面尺寸固定
        int size = 5;
        // fileName 也就是 like 字段
        logger.info("获取文件二级目录 start >>> :userName>>"  + ",type >> "  + ",currentPage >> "  );
        // 在这里组织好文件信息返回给前端
        ReturnObj returnObj = new ReturnObj(StatusEnum.Status999);

        //两类, 1.父母必听; 2.儿童必听 的子集

        returnObj = audioFileTypeService.findUserAudioAllDir();

        logger.info("获取文件二级目录 end >>> :" + ",列表返回,"+returnObj);
        return returnObj;
    }

    // 点击后传过来文件名和用户名
    @GetMapping("/getFilePath")
    public ReturnObj getFilePath(String fileName,String userName){
        logger.info(userName + "获取文件路径 start >>> :" + fileName);
        ReturnObj returnObj = new ReturnObj(StatusEnum.Status998);
        //查询路径返回，记录播放历史
        if(StringUtils.isNotEmpty(fileName)&&StringUtils.isNotEmpty(userName)) {
            returnObj = audioFileMainService.findAudioByRealName(fileName, userName);
        }else {
            returnObj.setData(Constant_Common.notAllowNull);
        }

        logger.info(userName + "返回数据 end >>> :" + returnObj);
        return returnObj;
    }

    // 收藏音频
    @GetMapping("/markFile")
    public ReturnObj markFile(String userName,String fileName,int mark){
        logger.info("开始收藏,userName start >>> :" + userName + ",fileName>>" + fileName + ",mark>>" + mark);
        // 数据校验
        ReturnObj returnObj = new ReturnObj();
        returnObj = userAudioMarkService.addUserAudioMark(userName,fileName,mark);
        logger.info("收藏结果 end >>> :" + returnObj);
        return returnObj;
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
