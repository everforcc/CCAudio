package cn.cc.ccaudio.component;


import cn.cc.ccaudio.dao.UserMainMapper;
import cn.cc.ccaudio.dto.UserMain;
import cn.cc.ccaudio.utils.HttpContextUtil;
import cn.cc.ccaudio.utils.Result;
import cn.cc.ccaudio.utils.TokenUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *  登录检查
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserMainMapper userMainMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 访问文件资源的拦截测试 看能不能获取请求路径
        logger.info("拦截器:" + request.getRequestURI());

        //防止  拿到 链接随意输入的错
        if("/error".equals(request.getRequestURI())){
            setReturn(response, 400, "恭喜你中奖了");
            return false;
        }

        String token = TokenUtil.getRequestToken(request);
        // 链接加时间戳 校验处理，只给10min时间试试
        if (StringUtils.isBlank(token)) {
            setReturn(response, 400, "用户未登录，请先登录");
            return false;
        }
        //1. 根据token，查询用户信息
        UserMain userMain = userMainMapper.queryUserByToken(token);
        //2. 若用户不存在,
        if (userMain == null) {
            setReturn(response, 400, "用户未登录，请先登录");
            return false;
        }
        //3. token失效 开始没有这个失效时间会报错，但是不关键
        if (userMain.getTokenExpireDate().compareTo(new Date())<1) {
            setReturn(response, 400, "用户登录凭证已失效，请重新登录");
            return false;
        }
        //4. 账户到期，也可以不做看情况

        // 正确的话不用返回，会出错
        //setReturn(response,200,"success");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        // 在处理过程中，执行拦截
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
       // 执行完毕，返回前拦截
    }

    // 这个位置返回一个代码让重新登录
    private static void setReturn(HttpServletResponse response, int status, String msg) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setStatus(status);
        response.setContentType("application/json;charset=utf-8");
        Result build = Result.build(status, msg);
        String json = JSON.toJSONString(build);
        httpResponse.getWriter().print(json);
    }

}
