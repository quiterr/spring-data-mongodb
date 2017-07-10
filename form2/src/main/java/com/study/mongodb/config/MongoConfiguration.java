package com.study.mongodb.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by szl on 2017/6/22.
 */
@Configuration
public class MongoConfiguration {

    /**
     * the server list of mongo db. mongodb://user:pass@r1.example.net:27017,r2.example.net:27017/aaa
     */
    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Bean
    public MongoClient mongoClient() {
        MongoClientURI mongoClientURI = new MongoClientURI(uri);
        List<String> hosts = mongoClientURI.getHosts();
        final List<ServerAddress> serverAddresses = new LinkedList<ServerAddress>();
        hosts.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                serverAddresses.add(new ServerAddress(s));
            }
        });
        MongoClient mongoClient = new MongoClient(serverAddresses);
        return mongoClient;
    }
}
