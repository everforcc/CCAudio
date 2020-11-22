package cn.cc.ccaudio.controller;

import cn.cc.ccaudio.bean.UserEntity;
import cn.cc.ccaudio.dao.UserEntityMapper;
import cn.cc.ccaudio.dao.UserEntityXMLMapper;
import cn.cc.ccaudio.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController // 所有controller都用这个，没有视图
public class HelloController {
    // 最基本的数据库操作
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/query")
    public Map<String,Object> map(){
        List<Map<String,Object>> mapList = jdbcTemplate.queryForList("select * from userentity");
        return mapList.get(0);
    }

    @Autowired
    UserEntityMapper userEntityMapper;

    @GetMapping("/seluser/{id}")
    public UserEntity selectUser(@PathVariable("id")String id){
        System.out.println(id);
        return userEntityMapper.selectByUserName(id);
    }

    @GetMapping("/adduser/{id}")
    public UserEntity addUser(@PathVariable("id")String id){
        System.out.println(id);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(id);
        userEntityMapper.insertUserEntity(userEntity);
        return userEntity;
    }

    @Autowired
    UserEntityService userEntityService;
    @GetMapping("/se/{id}")
    public UserEntity selectUser2(@PathVariable("id")String id){
        System.out.println(id);
        return userEntityService.selectUser(id);
    }

}
