package com.tortoise.framework.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;

/**
 * Jackson类库的封装工具类，专门负责解析json数据</br>
 */
public class JsonUtil {
	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	/**
	 * 默认的日期格式
	 */
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 单例模式
	 */
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	/**
	 * 单例模式
	 */
	static {
		OBJECT_MAPPER.setDateFormat(new SimpleDateFormat(DEFAULT_DATE_PATTERN));
		// 设置输出时包含属性的风格
		OBJECT_MAPPER.setSerializationInclusion(Include.NON_EMPTY);
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		OBJECT_MAPPER.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		OBJECT_MAPPER.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		OBJECT_MAPPER.addHandler(new DeserializationProblemHandler() {
		});

	}

	/**
	 * Object转换为json.
	 * 
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		if (object == null) {
			return "";
		}
		try {
			return OBJECT_MAPPER.writeValueAsString(object);
		} catch (IOException e) {
			logger.error("write to json string error:" + object, e);
			return null;
		}
	}

	/**
	 * json转换map
	 * 
	 * @param jsonString
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map toMap(String jsonString) {
		return fromJson(jsonString, Map.class);
	}

	/**
	 * json转换map
	 * 
	 * @param jsonString
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List toList(String jsonString) {
		return fromJson(jsonString, List.class);
	}
	
	/**
	 * json转换为对象.
	 * 
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		if (jsonString == null || jsonString.trim().equals("")) {
			return null;
		}

		try {
			return OBJECT_MAPPER.readValue(jsonString, clazz);
		} catch (IOException e) {
			logger.error("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * json转换为对象.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String jsonString, JavaType javaType) {
		if (jsonString == null || jsonString.trim().equals("")) {
			return null;
		}
		try {
			return (T) OBJECT_MAPPER.readValue(jsonString, javaType);
		} catch (IOException e) {
			logger.error("parse json string error:" + jsonString, e);
			return null;
		}
	}
	

	/**
	 * json转换为对象.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T fromJson(String jsonString, TypeReference typeReference) {
		if (jsonString == null || jsonString.trim().equals("")) {
			return null;
		}
		try {
			return (T) OBJECT_MAPPER.readValue(jsonString, typeReference);
		} catch (IOException e) {
			logger.error("parse json string error:" + jsonString, e);
			return null;
		}
	}

	public static JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
		return OBJECT_MAPPER.getTypeFactory().constructParametricType(parametrized, parameterClasses);
	}

	

}