package com.tortoise.quake.hbase;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Component;

import com.tortoise.quake.hbase.model.HbasePutData;

/**  
 * @Project: quake
 * @Title: QuakeRedisTemplate.java
 * @Package com.tortoise.quake.redis
 * @Description: 自定义HbaseTemplate
 * @author WangZhi
 * @date 2018年5月30日 下午1:59:58
 * @Copyright: 2018 
 * @version V1.0  
 */
@Component
public class HbaseClient {
	private static final Logger LOGGER    = LoggerFactory.getLogger(HbaseClient.class);  
	
    @Autowired
    private HbaseTemplate  hbaseTemplate;  
  
    @Autowired  
    private HbaseProperties hbaseProperties;  
  
    public void put(String tableName, HbasePutData data) throws IOException{
    	hbaseTemplate.put(tableName, data.getRowName(), data.getFamilyName(), data.getQualifier(), data.getValue());
    	
    }
    
    
    
  
   
}
