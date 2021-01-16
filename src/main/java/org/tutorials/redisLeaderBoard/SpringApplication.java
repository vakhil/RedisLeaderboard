package org.tutorials.redisLeaderBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import java.io.IOException;


@SpringBootApplication
@EnableCaching
public class SpringApplication {

    @Autowired
    RedisOperationService redisOperations;

    @Autowired
    RedisUtil util;

    public static void main(String[] args) throws IOException {
        //Create Objects of
        ConfigurableApplicationContext applicationContext = org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }

    @PostConstruct
    private void initializeJedisPool() {

        util.initializeJedisPool();
        redisOperations.insertIntoRedis();


    }


}





