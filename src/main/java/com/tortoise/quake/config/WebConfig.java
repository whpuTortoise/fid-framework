package com.tortoise.quake.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tortoise.quake.interceptor.MyInterceptor;

/**
 * @Project: quake
 * @Title: WebConfig.java
 * @Package com.tortoise.quake.config
 * @Description: Web配置
 * @author WangZhi
 * @date 2018年5月11日 下午3:15:39
 * @Copyright: 2018
 * @version V1.0
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${commconfig.excludeFilterLoginPath}")
    private String excludeFilterLoginPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations(
		ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String[] paths = excludeFilterLoginPath.split(",");
		
		// 拦截规则：除了login，其他都拦截判断
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**")
				.excludePathPatterns(paths);
	}

}
