package org.tutorials.redisLeaderBoard;

import lombok.Data;

@Data
public class Score{
    String userName;
    int highScore = 0;


}
