package org.tutorials.redisLeaderBoard;

public class ScoreFactory {

    public static Score getScore(String name, int score){
        Score scores = new Score();
        scores.setUserName(name);
        scores.setHighScore(score);
        return scores;
    }
}
