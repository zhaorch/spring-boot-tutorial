package com.zrc.springboottutorial.service.impl;

import com.zrc.springboottutorial.mapper.SysUserMapper;
import com.zrc.springboottutorial.model.SysUser;
import com.zrc.springboottutorial.model.SysUserCriteria;
import com.zrc.springboottutorial.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public SysUser getSysUserById(String id) {
		// TODO Auto-generated method stub
		return sysUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SysUser> selectByExample(SysUserCriteria example) {
		return sysUserMapper.selectByExample(example);
	}

	@Override
	public List<SysUser> getSysUserByName(String name) {
		return sysUserMapper.selectByName(name);
	}
}
