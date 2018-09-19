package com.tortoise.quake.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
@EnableWebMvc
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter implements
		ApplicationContextAware {

	private ApplicationContext mApplicationContext;
	
	@Value("${commconfig.excludeFilterLoginPath}")
    private String excludeFilterLoginPath;
	
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.mApplicationContext = arg0;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	/*	registry.addResourceHandler("/static/**").addResourceLocations(
				ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
		registry.addResourceHandler("/templates/**").addResourceLocations(
				ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");*/
		
		registry.addResourceHandler("/css/**").addResourceLocations(
				ResourceUtils.CLASSPATH_URL_PREFIX + "/static/css/");
		registry.addResourceHandler("/js/**").addResourceLocations(
				ResourceUtils.CLASSPATH_URL_PREFIX + "/static/js/");
		registry.addResourceHandler("/img/**").addResourceLocations(
				ResourceUtils.CLASSPATH_URL_PREFIX + "/static/img/");
		registry.addResourceHandler("/fonts/**").addResourceLocations(
				ResourceUtils.CLASSPATH_URL_PREFIX + "/static/fonts/");
		registry.addResourceHandler("/plugins/**").addResourceLocations(
				ResourceUtils.CLASSPATH_URL_PREFIX + "/static/plugins/");
		registry.addResourceHandler("/favicon.ico").addResourceLocations(
				ResourceUtils.CLASSPATH_URL_PREFIX + "/static/favicon.ico");
		
		super.addResourceHandlers(registry);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String[] paths = excludeFilterLoginPath.split(",");
		
		// 拦截规则：除了login，其他都拦截判断
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")
				.excludePathPatterns(paths);
		super.addInterceptors(registry);
	}

}
