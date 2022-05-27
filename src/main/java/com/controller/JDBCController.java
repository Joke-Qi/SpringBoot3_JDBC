package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //没有实体类获取数据库的信息
    //查询所有信息
    @GetMapping("/wordList")
    public List<Map<String,Object>> wordsList(){
        String sql = "select * from words";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    //增加信息
    @GetMapping("/addWord")
    public String addWord(){
        String sql = "insert into words(id,word,mean) values(7,'猫','cat')";
        jdbcTemplate.update(sql);
        return "ok";
    }

    //修改信息
    @GetMapping("/updateWord/{id}")
    public String updateWord(@PathVariable("id")int id){
        String sql = "update words set word=?, mean=? where id="+id;
        Object[] objects = new Object[2];
        objects[0] = "cat";
        objects[1] = "猫";
        jdbcTemplate.update(sql,objects);
        return "ok";
    }

    //删除信息
    @GetMapping("/deleteWord/{id}")
    public String deleteWord(@PathVariable("id")int id){
        String sql = "delete from words where id=?";
        jdbcTemplate.update(sql,id);
        return "ok";
    }

}
