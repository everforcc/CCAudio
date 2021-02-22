package cn.cc.ccaudio.service.impl;

import cn.cc.ccaudio.constant.Constant_Common;
import cn.cc.ccaudio.constant.StatusEnum;
import cn.cc.ccaudio.dao.AudioFileMainMapper;
import cn.cc.ccaudio.dao.AudioFilePathMapper;
import cn.cc.ccaudio.dao.AudioFileTypeMapper;
import cn.cc.ccaudio.dao.UserAudioHistoryMapper;
import cn.cc.ccaudio.dto.AudioFileMain;
import cn.cc.ccaudio.dto.AudioFilePath;
import cn.cc.ccaudio.dto.AudioFileType;
import cn.cc.ccaudio.dto.UserAudioHistory;
import cn.cc.ccaudio.exception.DefiFileSaveException;
import cn.cc.ccaudio.service.AudioFileMainService;
import cn.cc.ccaudio.utils.IOUtils;
import cn.cc.ccaudio.utils.ReturnObj;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("audioFileMainService")
@Transactional // 事务的注解
public class AudioFileMainServiceImpl implements AudioFileMainService {

    // 每个文件夹上限320000个
    // 目前系统不可能又那么多文件，所以每个文件夹就给1024个就行
    static final int MAXIMUM_CAPACITY = 1 << 10;
    static final DecimalFormat df = new DecimalFormat("0000");
    static final String separator = "/";

    Logger logger = LoggerFactory.getLogger(AudioFileMainServiceImpl.class);

    @Resource
    AudioFileMainMapper audioFileMainMapper;

    @Resource
    AudioFilePathMapper audioFilePathMapper;

    @Resource
    UserAudioHistoryMapper userAudioHistoryMapper;

    @Resource
    AudioFileTypeMapper audioFileTypeMapper;

    // 查总数

    // 模糊分页查询
    @Override
    public ReturnObj findAudio(String like, int currentPage, int size,String parentTypeFileType) {
        logger.info("");
        ReturnObj returnObj = new ReturnObj();
        logger.info(like + "," + currentPage + "," + size);
        List<AudioFileMain> audioFileMains = audioFileMainMapper.queryAudioLike(like, (currentPage -1) * size, size,parentTypeFileType);

        if (audioFileMains != null && audioFileMains.size() > 0) {
            int totalNum = audioFileMainMapper.queryFileCount();
            int pageNum = (int) Math.ceil(totalNum/size);
            returnObj.setStatusEnum(StatusEnum.Status200);
            returnObj.setData(totalNum,pageNum,audioFileMains);

        } else {
            returnObj.setCode("00");
            returnObj.setValue("没查到数据");
        }
        logger.info("" + returnObj);
        return returnObj;
    }




    //存文件只有后台一人，不可以同时操作
    @Override
    public synchronized ReturnObj saveAudioFile(MultipartFile[] fileList,String type,String child) {
        StringBuffer returnMsg = new StringBuffer("");
        // 可以同时上传文件，但是建议不要，可能会出错，出错会给提示，
        // 但是逻辑完美的话应该也不会出错

        String path = "";
        String rename = "";
        for (MultipartFile file : fileList) { // 实际上每次只会过来一个请求，但是也没必要删

            try {
                String realName = file.getOriginalFilename();
                logger.info("开始保存文件:" + realName);
                // 需要先检查不存在
                List<AudioFileMain> audioFileMainList = audioFileMainMapper.queryAudioByRealName(realName);
                if (audioFileMainList.size() == 0) {

                    AudioFilePath audioFilePath = audioFilePathMapper.querySequence(type);

                    if(audioFilePath.getFileName() < MAXIMUM_CAPACITY){
                        audioFilePath.setFileName(audioFilePath.getFileName() + 1);
                    }else {
                        audioFilePath.setFileDir(audioFilePath.getFileDir() + 1);
                        audioFilePath.setFileName(0);
                    }
                    // 成功后，更新序列
                    audioFilePathMapper.updateSequence(audioFilePath);

                    path = audioFilePath.getFileParent() + separator + df.format(audioFilePath.getFileDir()) + separator;
                    rename = df.format(audioFilePath.getFileName()) + realName.substring(realName.lastIndexOf("."),realName.length());
                    // 保存文件
                    String mp3Time = IOUtils.saveFile(file, path, rename);

                    if("1".equals(type)){
                        type = "parent";
                    }else {
                        type = "child";
                    }

                    AudioFileType audioFileType = audioFileTypeMapper.queryAduioType(type,child);
                    if(audioFileType==null){
                        logger.info("type:" + type + " >>> child:" + child);
                        audioFileTypeMapper.insertAduioType(type,child);
                    }

                    AudioFileMain audioFileMain = new AudioFileMain();
                    audioFileMain.setPath(path);
                    audioFileMain.setRealName(realName);
                    audioFileMain.setName(rename);
                    audioFileMain.setSize(file.getSize());
                    audioFileMain.setLength(mp3Time);
                    audioFileMain.setParent(type + child);
                    audioFileMain.setRemark("");
                    // 自定义异常
                    audioFileMainMapper.insertAudioFile(audioFileMain);
                    logger.info("文件" + realName + "上传成功");
                    //returnMsg.append(realName + "上传成功 \r\n");
                } else {
                    // 文件 aaa 已经存在
                    returnMsg.append(realName + "已经存在 \r\n");
                    logger.info(realName + ":已经存在"  );
                }
                // 在外面处理异常，sql可以回滚，文件也要删掉

            } catch (IOException e) {
                // 在这个位置抛错就会触发回滚,还需要手动删除文件
                // 假如是数据库报错,就清除文件

                e.printStackTrace();
                returnMsg.append(file.getOriginalFilename() + "上传失败 \r\n");
            } catch (DefiFileSaveException e) {
                // 文件数据库保存异常,保存的文件要删掉
                IOUtils.delFile(path + rename);
                logger.warn(file.getOriginalFilename() + ":入库异常" + e.toString());
                returnMsg.append(file.getOriginalFilename() + "入库异常 \r\n");
            }
        }
        ReturnObj returnObj = new ReturnObj(StatusEnum.Status200,returnMsg.toString());
        return returnObj;
    }

    // 返回文件路径 这个位置只能查出来一条，前面逻辑严谨的话，这里不可能出错，但是为了防止随便搞，所以加判断
    @Override
    public ReturnObj findAudioByRealName(String fileName, String userName) {
        ReturnObj returnObj = new ReturnObj();
        logger.info("获取文件路径:" + fileName);
        List<AudioFileMain> audioFileMainList = audioFileMainMapper.queryAudioByRealName(fileName);
        if(audioFileMainList!=null){
            // 只有一条
            if(audioFileMainList.size()==1){
                returnObj.setStatusEnum(StatusEnum.Status200);
                returnObj.setData(audioFileMainList.get(0).getPath() + audioFileMainList.get(0).getName());
                //保存历史记录
                logger.info("保存历史记录开始---");
                saveHistory(userName,fileName);
                logger.info("保存历史记录结束---");
            // 多条？不可能
            }else {
                returnObj.setStatusEnum(StatusEnum.Status998);
                returnObj.setData(Constant_Common.unknownType);
            }
            // 从页面点过来没问题，万一乱给参数呢?
        }else {
            returnObj.setStatusEnum(StatusEnum.Status998);
            returnObj.setData(Constant_Common.parameterError);
        }
        return returnObj;
    }

    @Override
    public ReturnObj modifyAudioByRealName(AudioFileMain audioFileMain) {
        ReturnObj returnObj = new ReturnObj();
        if (audioFileMain != null && StringUtils.isNotEmpty(audioFileMain.getRealName())) {
            List<AudioFileMain> audioFileMainList = audioFileMainMapper.queryAudioByRealName(audioFileMain.getRealName());
            if (audioFileMainList.size() == 1) {
                audioFileMainMapper.updateAudioFileMain(audioFileMain);
                returnObj.setStatusEnum(StatusEnum.Status200);
                return returnObj;
            }
        }
        returnObj.setStatusEnum(StatusEnum.Status998);
        return returnObj;
    }

    @Override
    public ReturnObj deleteAudioFileMain(AudioFileMain audioFileMain) {
        ReturnObj returnObj = new ReturnObj();
        if (audioFileMain != null && StringUtils.isNotEmpty(audioFileMain.getRealName())) {
            List<AudioFileMain> audioFileMainList = audioFileMainMapper.queryAudioByRealName(audioFileMain.getRealName());
            if (audioFileMainList.size() == 1) {
                audioFileMainMapper.deleteAudioFileMain(audioFileMain);
                returnObj.setStatusEnum(StatusEnum.Status200);
                return returnObj;
            }
        }
        returnObj.setStatusEnum(StatusEnum.Status998);
        return returnObj;
    }

    // 更新历史记录
    private void saveHistory(String userName,String fileRealName){
        //1.查询是否存在
        List<UserAudioHistory> userAudioHistoryList = userAudioHistoryMapper.queryHistory(userName,fileRealName);
        UserAudioHistory userAudioHistory = new UserAudioHistory();
        if(userAudioHistoryList==null||userAudioHistoryList.size()==0) {
            //2.不存在就插入
            userAudioHistory = new UserAudioHistory(userName,fileRealName,1);
            userAudioHistoryMapper.insertHistory(userAudioHistory);
            logger.info(userName+"-"+fileRealName+"不存在，已插入");
        }else {
            //3.存在就更新
            userAudioHistory.setListenCount(userAudioHistoryList.get(0).getListenCount()+1);
            userAudioHistory.setLastDate(new Date());
            userAudioHistoryMapper.updateHistory(userAudioHistory);
            logger.info(userName+"-"+fileRealName+"存在，已更新"+ userAudioHistory.getLastDate());
        }

    }

}
