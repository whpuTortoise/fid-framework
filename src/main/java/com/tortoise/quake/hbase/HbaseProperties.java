package com.tortoise.quake.hbase;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**  
 * @Project: quake
 * @Title: RedisProperties.java
 * @Package com.tortoise.quake.redis
 * @Description: hbase集群配置文件  
 * @author WangZhi
 * @date 2018年5月30日 下午1:38:20
 * @Copyright: 2018 
 * @version V1.0  
 */
@Component  
@ConfigurationProperties(prefix = "quake.hbase")  
public class HbaseProperties {
	/** 集群节点 */  
    private String quorum;  
    /** 端口 */  
    private String port;
    /**
     * 缓冲区大小
     */
    private int writeBuffer;
    
	public String getQuorum() {
		return quorum;
	}
	public void setQuorum(String quorum) {
		this.quorum = quorum;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public int getWriteBuffer() {
		return writeBuffer;
	}
	public void setWriteBuffer(int writeBuffer) {
		this.writeBuffer = writeBuffer;
	}  
    
    
}
