package com.zrc.springboottutorial.jpa.listener;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/7/30 13:43
 * Description: No Description
 */

public class SysUserListener {
    @PrePersist
    public void prePersist(Object source){
        System.out.println("@PrePersist:" + source);
    }

    @PostPersist
    public void postPersist(Object source){
        System.out.println("@PostPersist:" + source);
    }
}
