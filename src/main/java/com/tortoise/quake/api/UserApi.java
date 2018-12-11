package com.tortoise.quake.api;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tortoise.quake.model.User;

/**
 * API使用RestController，默认返回json格式，无需ResponseBody注解
 * @author wangzhi
 *
 */
@RestController
@RequestMapping(value="/users")
public class UserApi {
	
	@RequestMapping(value="/", method=RequestMethod.GET) 
    public List<User> getUserList() { 
        return null; 
    } 
	
	@RequestMapping(value="/", method=RequestMethod.POST) 
    public String postUser(@ModelAttribute User user) { 
        return "success"; 
    } 
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) 
    public String deleteUser(@PathVariable Long id) { 
        return "success"; 
    } 
	
}
