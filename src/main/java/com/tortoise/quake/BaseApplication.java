package com.tortoise.quake;

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
public class BaseApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BaseApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}

	
	
}
