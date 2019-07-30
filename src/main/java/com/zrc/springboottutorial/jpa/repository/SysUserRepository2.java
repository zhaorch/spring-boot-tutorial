package com.zrc.springboottutorial.jpa.repository;

import com.zrc.springboottutorial.jpa.entity.SysUserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/7/30 14:24
 * Description: No Description
 */

@Repository
@Transactional(readOnly = false)
public class SysUserRepository2 extends SimpleJpaRepository<SysUserJPA,String> {

    @Autowired
    public SysUserRepository2(EntityManager entityManager) {
        super(SysUserJPA.class,entityManager);
    }
}
