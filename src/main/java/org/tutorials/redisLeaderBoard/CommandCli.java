package org.tutorials.redisLeaderBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Set;

@ShellComponent
public class CommandCli {

    @Autowired
    RedisOperationService redisOperationService;

    @ShellMethod("Loads data into Redis Sorted Sets")
    public String insert(String name, double score ) {
        return redisOperationService.insertDataToRedis(name,score);
    }



    @ShellMethod("Retreives top 5 highest scoreres from Redis")
    public Set<String> load() {
        return redisOperationService.loadTopGamersFromRedis();
    }
}

