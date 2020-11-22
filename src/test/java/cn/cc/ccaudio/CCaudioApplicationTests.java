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

}
