package com.zrc.springboottutorial.jpa.service;

import com.zrc.springboottutorial.jpa.entity.SysUserJPA;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/7/30 11:01
 * Description: No Description
 */
@Service
public class SysUserJPAService {
    @PersistenceContext
    protected EntityManager em;

    public SysUserJPA get(String id){
        return em.find(SysUserJPA.class,id);
    }

    @Transactional
    public SysUserJPA save(SysUserJPA user){
        em.persist(user);
        return user;
    }
}
