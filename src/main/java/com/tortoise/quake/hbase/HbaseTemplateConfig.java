package com.tortoise.quake.hbase;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

/**  
 * @Project: quake
 * @Title: JedisClusterConfig.java
 * @Package com.tortoise.quake.redis
 * @Description: 生成HbaseTemplate对象
 * @author WangZhi
 * @date 2018年5月30日 下午1:51:22
 * @Copyright: 2018 
 * @version V1.0  
 */
@Configuration
public class HbaseTemplateConfig {
	
	@Autowired  
    private HbaseProperties hbaseProperties;  
  
    /**  
     * 注意：  
     * 这里返回的HbaseTemplate是单例的，并且可以直接注入到其他类中去使用  
     * @return  
     */  
    @Bean
    public HbaseTemplate getHbaseTemplate() {  
    	HbaseTemplate hbaseTemplate = new HbaseTemplate();  
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();  
        conf.set("hbase.zookeeper.quorum", hbaseProperties.getQuorum());  
        conf.set("hbase.zookeeper.port", hbaseProperties.getPort());  
        conf.setInt("hbase.client.write.buffer", hbaseProperties.getWriteBuffer());  
        hbaseTemplate.setConfiguration(conf);  
        //将HTable的setAutoFlush设为false，可以支持客户端批量更新。即当Put填满客户端flush缓存时，才发送到服务端。默认2M
        hbaseTemplate.setAutoFlush(false);  
        return hbaseTemplate;  
    }  
    
}
