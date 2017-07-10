package com.study.mongdb;

import com.alibaba.fastjson.JSON;
import com.study.mongdb.model.Friend;
import com.study.mongdb.model.Parent;
import com.study.mongdb.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by szl on 2017/6/22.
 */
@SpringBootApplication
public class App implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MappingMongoConverter mongoConverter;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostConstruct
    public void init() {
        mongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
    }

    public void insert(String name) {
        User user = new User();
        user.setUserId(1L);
        user.setName(name);
        user.setAccount("iot.chinamobile.com");
        user.setAge(36);
        user.setPassword("123456");

        Parent parent = new Parent();
        parent.setMather("mama");
        parent.setFather("baba");

        Friend friend = new Friend();
        friend.setName("sam");
        friend.setAddr("jinkai road");
        friend.setAge(30);
        friend.setParent(parent);

        user.setExtend(friend);
        mongoTemplate.insert(user);
    }

    public List<User> queryUser(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.find(query, User.class);
    }

    public List<User> queryUserByFriend(String friendName){
        Query query = new Query(Criteria.where("extend.name").is("sam"));
        return mongoTemplate.find(query, User.class);
    }

    public void deleteUser(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        mongoTemplate.remove(query, User.class);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("app started!");
        insert("szl");
        insert("lvlv");
        List<User> users = queryUser("szl");
        String json = JSON.toJSONString(users, true);
        logger.info(json);

        users = queryUserByFriend("sam");
        json = JSON.toJSONString(users, true);
        logger.info(json);
        deleteUser("szl");
    }
}
