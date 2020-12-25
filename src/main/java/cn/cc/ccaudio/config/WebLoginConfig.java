package cn.cc.ccaudio.config;

import cn.cc.ccaudio.component.LoginHandlerInterceptor;
import cn.cc.ccaudio.constant.Constant_File;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebLoginConfig implements WebMvcConfigurer {

    // 最后还是要用这个，直接映射文件位置
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String filePath="";
        if(System.getProperty("os.name").startsWith("Windows")){
            filePath = "E:/java/audio/";
        }else {
            filePath = "/tmp/audio/";
        }
        registry.addResourceHandler(Constant_File.fileMapPath).addResourceLocations(Constant_File.fileLocalPath + filePath);
    }

    @Bean
    public LoginHandlerInterceptor authInterceptor() {
        return new LoginHandlerInterceptor();
    }

    // 将拦截器配置进 spring
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 放行路径
        List<String> patterns = new ArrayList();;
        patterns.add("/login/**"); //如果没有登录只给这个请求
        //patterns.add("/**"); //如果没有登录只给这个请求
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }


}
