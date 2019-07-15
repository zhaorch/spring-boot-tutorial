package com.zrc.springboottutorial.controller;

import com.zrc.springboottutorial.error.BussinessException;
import com.zrc.springboottutorial.error.EmBusinessError;
import com.zrc.springboottutorial.model.SysUser;
import com.zrc.springboottutorial.model.SysUserCriteria;
import com.zrc.springboottutorial.response.CommonReturnType;
import com.zrc.springboottutorial.service.SysUserService;
import com.zrc.springboottutorial.utils.JsonUtils;
import com.zrc.springboottutorial.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisOperator redis;

    @RequestMapping("/hello")
    public CommonReturnType hello() throws BussinessException {

        String userStr = redis.get("json:info:user:190709BX013WFZ7C");
        SysUser user = null;
        if(StringUtils.isEmpty(userStr)) {
            System.out.println("查询数据库");
            user = sysUserService.getSysUserById("190709BX013WFZ7C");

            if(user != null) {
                redis.set("json:info:user:190709BX013WFZ7C", JsonUtils.objectToJson(user), 2000);
            }
        }
        else{
            System.out.println("直接从缓存读");
            user = JsonUtils.jsonToPojo(userStr,SysUser.class);
        }

        if(user == null){
            throw new BussinessException(EmBusinessError.USER_NOT_EXIST);
        }

        return  CommonReturnType.create("Hello Spring boot 中国" + user.getName());
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
    public CommonReturnType hello3() {

        List<SysUser> userList = sysUserService.getSysUserByName("黑%");

        return CommonReturnType.create(userList);
    }
}
