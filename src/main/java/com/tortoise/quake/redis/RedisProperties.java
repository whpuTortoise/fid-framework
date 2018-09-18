package com.tortoise.quake.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**  
 * @Project: quake
 * @Title: RedisProperties.java
 * @Package com.tortoise.quake.redis
 * @Description: TODOredis集群配置文件  
 * @author WangZhi
 * @date 2018年5月30日 下午1:38:20
 * @Copyright: 2018 
 * @version V1.0  
 */
@Component  
@ConfigurationProperties(prefix = "quake.redis.pool")  
public class RedisProperties {
	/** redis集群节点 */  
    private String nodes;  
    /** 连接超时时间 */  
    private int timeout;  
    /** 重连次数 */  
    private int maxAttempts;  
    
    public String getNodes() {  
        return nodes;  
    }  
    public void setNodes(String nodes) {  
        this.nodes = nodes;  
    }  
    public int getTimeout() {  
        return timeout;  
    }  
    public void setTimeout(int timeout) {  
        this.timeout = timeout;  
    }  
    public int getMaxAttempts() {  
        return maxAttempts;  
    }  
    public void setMaxAttempts(int maxAttempts) {  
        this.maxAttempts = maxAttempts;  
    }  
}
