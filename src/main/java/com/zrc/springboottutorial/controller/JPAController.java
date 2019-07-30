package com.zrc.springboottutorial.controller;

import com.zrc.springboottutorial.jpa.entity.SysUserJPA;
import com.zrc.springboottutorial.jpa.repository.SysUserRepository;
import com.zrc.springboottutorial.jpa.repository.SysUserRepository2;
import com.zrc.springboottutorial.jpa.service.SysUserJPAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/7/30 9:58
 * Description: No Description
 */


@RestController
@RequestMapping("jpa")
public class JPAController {
    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysUserRepository2 sysUserRepository2;

    @Autowired
    private SysUserJPAService sysUserJPAService;

    @PostMapping("/insert")
    public SysUserJPA insertUser(@RequestBody SysUserJPA user) {
       SysUserJPA save = sysUserRepository.save(user);

       // 第二种方式
        // SysUserJPA save =sysUserJPAService.save(user);
        return save;

    }
    @GetMapping(value = "/user/{id}")
    public SysUserJPA getUser(@PathVariable("id") String id){
        //http://localhost:8090/jpa/user/190709BX013WFZ7C

        SysUserJPA user = sysUserRepository.findById(id).get();

        Page<SysUserJPA> page = sysUserRepository.findAll(PageRequest.of(1,5));

        user = sysUserJPAService.get(user.getId());
        return user;
    }
    @GetMapping(value = "/users")
    public Page<SysUserJPA> getUser(){
        //http://localhost:8090/jpa/users

        Page<SysUserJPA> page = sysUserRepository.findAll(PageRequest.of(1,5));

        Page<SysUserJPA> page2 = sysUserRepository2.findAll(PageRequest.of(1,5));

        return page2;
    }
}
