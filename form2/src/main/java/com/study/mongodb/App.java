package com.study.mongodb;

import com.study.mongodb.config.MongoTemplateFactory;
import com.study.mongodb.model.Friend;
import com.study.mongodb.model.Parent;
import com.study.mongodb.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created by szl on 2017/6/22.
 */
@SpringBootApplication
public class App implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    @Autowired
    private MongoTemplateFactory mongoTemplateFactory;

    private MongoTemplate mongoTemplate1;
    private MongoTemplate mongoTemplate2;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
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
        friend.setName("lvlv");
        friend.setAddr("jinkai road");
        friend.setAge(30);
        friend.setParent(parent);

        user.setExtend(friend);
        mongoTemplate1.insert(user);
        mongoTemplate2.insert(user);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("app started!");
        mongoTemplate1 = mongoTemplateFactory.getMongoTemplate("form2_1");
        mongoTemplate2 = mongoTemplateFactory.getMongoTemplate("form2_2");

        insert("szl");
        insert("lvlv");

        Query query = new Query(Criteria.where("name").is("szl"));
        User userSzl = mongoTemplate1.findOne(query, User.class);
        User userLvlv = mongoTemplate2.findOne(query, User.class);
    }
}
