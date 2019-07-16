package com.zrc.springboottutorial.controller;

import com.github.pagehelper.PageInfo;
import com.zrc.springboottutorial.error.BussinessException;
import com.zrc.springboottutorial.error.EmBusinessError;
import com.zrc.springboottutorial.model.SysUser;
import com.zrc.springboottutorial.model.SysUserCriteria;
import com.zrc.springboottutorial.model.ZrcResource;
import com.zrc.springboottutorial.response.CommonReturnType;
import com.zrc.springboottutorial.service.SysUserService;
import com.zrc.springboottutorial.utils.JsonUtils;
import com.zrc.springboottutorial.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "HelloController")
public class HelloController{

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisOperator redis;
    @Autowired
    private ZrcResource resource;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public CommonReturnType hello(String id) throws BussinessException {
        //@RequestParam(value="id") String id  如果用这个注解，则url中id必须存在否则报错
        String userID = "190709BX013WFZ7C";
        if(!StringUtils.isEmpty(id)){
            userID = id;
        }
        String userStr = redis.get("json:info:user:"+userID);
        SysUser user = null;
        if(StringUtils.isEmpty(userStr)) {
            System.out.println("查询数据库");
            user = sysUserService.getSysUserById(userID);

            if(user != null) {
                redis.set("json:info:user:"+userID, JsonUtils.objectToJson(user), 2000);
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

    @RequestMapping("/hello4")
    public CommonReturnType hello4(Integer page) {

        if (page == null) {
            page = 1;
        }
        int pageSize = 5;

        SysUser user = new SysUser();
//		user.setNickname("lee");

        PageInfo<SysUser> pageInfo = sysUserService.queryUserListPaged(user, page, pageSize);

        return CommonReturnType.create(pageInfo);

    }

    @RequestMapping("getResource")
    public CommonReturnType getResource(){
        return  CommonReturnType.create(resource.getName());
    }

    /**
     * 用户登录
     */
    @ApiOperation(value = "登录")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")})
    @PostMapping("/login")
    //@SystemControllerLog(description = "/admin/user/login")
    public CommonReturnType login(String userName, String password){
//        @Api： 描述 Controller
//        @ApiIgnore： 忽略该 Controller，指不对当前类做扫描
//        @ApiOperation： 描述 Controller类中的 method接口
//        @ApiParam： 单个参数描述，与 @ApiImplicitParam不同的是，他是写在参数左侧的。如（ @ApiParam(name="username",value="用户名")Stringusername）
//        @ApiModel： 描述 POJO对象
//        @ApiProperty： 描述 POJO对象中的属性值
//        @ApiImplicitParam： 描述单个入参信息
//        @ApiImplicitParams： 描述多个入参信息
//        @ApiResponse： 描述单个出参信息
//        @ApiResponses： 描述多个出参信息
//        @ApiError： 接口错误所返回的信息
        //http://localhost:8090/swagger-ui.html

        return CommonReturnType.create(userName);
    }
}
