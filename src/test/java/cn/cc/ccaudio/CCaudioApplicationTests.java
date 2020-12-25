package cn.cc.ccaudio;

import cn.cc.ccaudio.utils.ReturnObj;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
class CCaudioApplicationTests {

	@Autowired
	DataSource dataSource;

	@Test
	void contextLoads() {
		System.out.println(dataSource.getClass());
		try {
			System.out.println(dataSource.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void returnJSON(){
		Map<String,String> map = new HashMap<>();
		map.put("abc","abc");
		ReturnObj returnObj = new ReturnObj("200","成功");
		System.out.println(returnObj);
	}

	//记录器
	Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testLog() {
		//System.out.println();

		//日志的级别；
		//由低到高   trace<debug<info<warn<error
		//可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
		logger.trace("这是trace日志...");
		logger.debug("这是debug日志...");
		//SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
		logger.info("这是info日志...");
		logger.warn("这是warn日志...");
		logger.error("这是error日志...");


	}

	@Test
	void t1(){

		System.out.println( (int)Math.ceil(6d/5) );

		System.out.println(  );

	}

}
