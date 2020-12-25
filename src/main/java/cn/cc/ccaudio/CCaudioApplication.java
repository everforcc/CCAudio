package cn.cc.ccaudio;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "cn.cc.ccaudio.dao") //扫描包
@SpringBootApplication
public class CCaudioApplication {

	// 做完后修改前端所有文件名，防止被猜出来
	public static void main(String[] args) {
		SpringApplication.run(CCaudioApplication.class, args);
	}

}
