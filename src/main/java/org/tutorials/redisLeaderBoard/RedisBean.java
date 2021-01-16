package org.tutorials.redisLeaderBoard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/**
 * Value bean to read the Redis connection properties from Environment Variable
 * @author Sudeep
 *
 */
@Configuration
public class RedisBean {

    /*
     * Java buildpack translates Cloud Foundry VCAP_* environment variables
     * into usable property sources in the Spring Environment
     * [https://spring.io/blog/2015/04/27/binding-to-data-services-with-spring-boot-in-cloud-foundry]
     *
     * In this example, my-redis is the name of the Marketplace service I created
     */
    private String host="127.0.0.1";
    private String password;
    private String port="6379";

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }
    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }
    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }


}
