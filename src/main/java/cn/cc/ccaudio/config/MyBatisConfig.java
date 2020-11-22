package cn.cc.ccaudio.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class MyBatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return new ConfigurationCustomizer(){

            @Override
            public void customize(Configuration configuration) {
                // 开启驼峰命名法支持，不需要，开始就定义好名字
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
