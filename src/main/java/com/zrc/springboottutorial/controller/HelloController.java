package com.zrc.springboottutorial.controller;

import com.zrc.springboottutorial.model.SysUser;
import com.zrc.springboottutorial.model.SysUserCriteria;
import com.zrc.springboottutorial.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/hello")
	public String hello() {
		
		SysUser user = sysUserService.getSysUserById("190709BWZCYT18X4");

		return "Hello Spring boot 中国"+user.getName();
	}
	
	@RequestMapping("/hello2")
	public List<SysUser> helloExample() {
		
		SysUserCriteria sucCriteria = new SysUserCriteria();
		sucCriteria.createCriteria()
					.andNameLike("ZRC%")
					.andNameLike("%3%");
		
		return sysUserService.selectByExample(sucCriteria);
	}
	
	@RequestMapping("/hello3")
	public List<SysUser> hello3() {
		
		List<SysUser> userList = sysUserService.getSysUserByName("黑%");

		return userList;
	}
}
