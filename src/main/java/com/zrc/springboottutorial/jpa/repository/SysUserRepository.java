package com.zrc.springboottutorial.jpa.repository;

import com.zrc.springboottutorial.jpa.entity.SysUserJPA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/7/30 9:56
 * Description: No Description
 */

public interface  SysUserRepository extends JpaRepository<SysUserJPA,String> {

}
