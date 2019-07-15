package com.zrc.springboottutorial.service;

import com.zrc.springboottutorial.model.SysUser;
import com.zrc.springboottutorial.model.SysUserCriteria;

import java.util.List;

public interface SysUserService {
	public SysUser getSysUserById(String id);
	public List<SysUser> getSysUserByName(String name);
	public List<SysUser> selectByExample(SysUserCriteria example);
}
