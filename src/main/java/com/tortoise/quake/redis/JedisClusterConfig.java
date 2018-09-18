package com.tortoise.quake.redis;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**  
 * @Project: quake
 * @Title: JedisClusterConfig.java
 * @Package com.tortoise.quake.redis
 * @Description: TODO生成JedisCluster对象
 * @author WangZhi
 * @date 2018年5月30日 下午1:51:22
 * @Copyright: 2018 
 * @version V1.0  
 */
@Configuration
public class JedisClusterConfig {
	@Autowired  
    private RedisProperties redisProperties;  
  
    /**  
     * 注意：  
     * 这里返回的JedisCluster是单例的，并且可以直接注入到其他类中去使用  
     * @return  
     */  
//    @Bean
//    public JedisCluster getJedisCluster() {  
//        String[] serverArray = redisProperties.getNodes().split(",");//获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)  
//        Set<HostAndPort> nodes = new HashSet<>();  
//  
//        for (String ipPort : serverArray) {  
//            String[] ipPortPair = ipPort.split(":");  
//            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));  
//        }  
//  
//        return new JedisCluster(nodes, redisProperties.getTimeout(), redisProperties.getMaxAttempts());  
//    }  
    
}
