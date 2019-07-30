package com.zrc.springboottutorial.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrc.springboottutorial.jpa.listener.SysUserListener;
import com.zrc.springboottutorial.validation.custom.CaseMode;
import com.zrc.springboottutorial.validation.custom.CheckCase;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/7/30 8:43
 * Description: No Description
 */

@Entity
@Table(name = "SYS_USER")
@EntityListeners(value = {SysUserListener.class})
public class SysUserJPA {
    @Id
    //@JsonIgnore
    private String id;

    @Column(name = "name", length = 32, nullable = false)
    @NotBlank(message="用户名不能为空")
    @CheckCase(value = CaseMode.LOWER,message = "name必须是小写")
    private String name;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")
    private Date birthday;

    @Column
    @Range(min=0,max=1,message="性别只能为0/1")
    private Integer gender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "SysUserJPA{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                '}';
    }
}
