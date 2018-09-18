package com.tortoise.quake;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * <pre>SpringBoot服务启动入口，使用了@SpringBootApplication注解会
 * 自动扫描该类子路径下所有@Controller，@Service，@Repository，@Component等注解类 </pre>
 * 
 * @author wangzhi
 *
 */
@SpringBootApplication
@MapperScan("com.tortoise.quake.dao")
public class BaseApplication extends SpringBootServletInitializer {
	
	/**
	   * 如果要发布到自己的Tomcat中的时候，需要继承SpringBootServletInitializer类，并且增加如下的configure方法。
	   * 如果不发布到自己的Tomcat中的时候，就无需上述的步骤
	   */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BaseApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
		
//		UmengPush.sendMessageDemo();
	}

	
	
}
