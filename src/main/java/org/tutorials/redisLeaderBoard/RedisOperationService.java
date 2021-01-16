package org.tutorials.redisLeaderBoard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.Set;

@Service
public class RedisOperationService {

    private static Logger log = LoggerFactory.getLogger(RedisOperationService.class);


    @Autowired
    private RedisUtil util;

    private JedisPool jedisPool = null;

    public String testConnection() {
        String message = null;

        jedisPool = util.getJedisPool();
        try (Jedis jedis = jedisPool.getResource()){
            if(jedis.isConnected()) {
                message = "connection successful";
            } else {
                message = "connection failed";
            }
        } catch (Exception e) {
            message = "connection failed";
        }

        return message;
    }

    public String insertDataToRedis(String userName, Double score){
        jedisPool = util.getJedisPool();
        String returnMsg;
        try (Jedis jedis = jedisPool.getResource()) {
            String keySet = "leaders:exp";
            Long returnCode = jedis.zadd(keySet,score,userName);
            log.info("Data Inserted/Updated Successfully for "+userName);

            if(returnCode == 1) {
                returnMsg = "Data Inserted";
            } else if (returnCode == 0) {
                returnMsg = "Data Updated";
            } else {
                returnMsg = "failure";
            }
            return returnMsg;
        }
    }

    public Set<String> loadTopGamersFromRedis(){
        jedisPool = util.getJedisPool();
        String returnMsg;

        try (Jedis jedis = jedisPool.getResource()) {
            String keySet = "leaders:exp";
            Set<String> rankedScores = jedis.zrevrange(keySet,0,4);
            log.info(rankedScores.toString());
            return rankedScores;
        }
    }


    public String insertIntoRedis(){
        String returnMsg = null;
        Long returnCode = new Long(0);
        testConnection();
        ArrayList<Score> jedisEntries = new ArrayList<>();
        Score akhil = ScoreFactory.getScore("Akhil",35);
        Score nikhil = ScoreFactory.getScore("Nikhil",80);
        Score mummy = ScoreFactory.getScore("Mummy",50);
        Score daddy = ScoreFactory.getScore("Daddy",70);
        jedisEntries.add(akhil);
        jedisEntries.add(nikhil);
        jedisEntries.add(mummy);
        jedisEntries.add(daddy);

        jedisPool = util.getJedisPool();

        try (Jedis jedis = jedisPool.getResource()) {
            String keySet = "leaders:exp";
            for (Score scoreData : jedisEntries) {
                returnCode = jedis.zadd(keySet,scoreData.getHighScore(),scoreData.getUserName());
                log.info("Data Inserted/Updated Successfully for "+scoreData.toString());

            }
        }


        try (Jedis jedis = jedisPool.getResource()) {
            String keySet = "leaders:exp";
            Set<String> rankedScores = jedis.zrevrange(keySet,0,3);
            log.info(rankedScores.toString());
        }


        if(returnCode == 1) {
            returnMsg = "Data Inserted";
        } else if (returnCode == 0) {
            returnMsg = "Data Updated";
        } else {
            returnMsg = "failure";
        }
        return returnMsg;
    }
}
