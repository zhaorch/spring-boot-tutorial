package com.zrc.springboottutorial.controller;

import com.github.pagehelper.PageInfo;
import com.zrc.springboottutorial.annotation.SystemControllerLog;
import com.zrc.springboottutorial.error.BussinessException;
import com.zrc.springboottutorial.error.EmBusinessError;
import com.zrc.springboottutorial.model.SysUser;
import com.zrc.springboottutorial.model.SysUserCriteria;
import com.zrc.springboottutorial.model.ZrcResource;
import com.zrc.springboottutorial.response.CommonReturnType;
import com.zrc.springboottutorial.service.SysUserService;
import com.zrc.springboottutorial.utils.RedisClient4ProtoStuff;
import com.zrc.springboottutorial.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.MapUtils;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@Api(value = "HelloController")
@Validated
public class HelloController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisOperator redis;
    @Autowired
    private RedisClient4ProtoStuff redis2;
    @Autowired
    private ZrcResource resource;

    @Autowired
    private Validator validator;

    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @SystemControllerLog(description = "调用Hello")
    public CommonReturnType hello(String id) throws BussinessException {
        //@RequestParam(value="id") String id  如果用这个注解，则url中id必须存在否则报错
        String userID = "190709BX013WFZ7C";
        if (!StringUtils.isEmpty(id)) {
            userID = id;
        }

        SysUser user =  redis2.get("user:" + userID,SysUser.class);
        if (user == null) {
            System.out.println("查询数据库");
            user = sysUserService.getSysUserById(userID);
            if (user == null) {
                throw new BussinessException(EmBusinessError.USER_NOT_EXIST);
            }

            redis2.setWithExpire("user:" + userID, user,2000);
        } else {
            System.out.println("直接从缓存读");
        }

          //传统的json方法
//        String userStr = redis.get("json:info:user:" + userID);
//        SysUser user = null;
//        if (StringUtils.isEmpty(userStr)) {
//            System.out.println("查询数据库");
//            user = sysUserService.getSysUserById(userID);
//
//            if (user != null) {
//                //redis.set("json:info:user:" + userID, JsonUtils.objectToJson(user), 2000);
//            }
//        } else {
//            System.out.println("直接从缓存读");
//            user = JsonUtils.jsonToPojo(userStr, SysUser.class);
//        }
//        if (user == null) {
//            throw new BussinessException(EmBusinessError.USER_NOT_EXIST);
//        }


        //MapUtil 的使用
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","zrc");
        map.put("age",18);
        int age = MapUtils.getInteger(map,"age");

        logger.info("Hello " + user.getName());
        return CommonReturnType.create("Hello Spring boot 中国" + user.getName());
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
    public CommonReturnType getResource() {
        return CommonReturnType.create(resource.getName());
    }

    /**
     * 用户登录
     */
    @ApiOperation(value = "登录")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")})
    @PostMapping("/login")
    //@SystemControllerLog(description = "/admin/user/login")
    public CommonReturnType login(String userName, String password) {
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

    /**
     * 如果只有少数对象，直接把参数写到Controller层，然后在Controller层进行验证就可以了。
     */
    @RequestMapping(value = "/hibernateValidator", method = RequestMethod.GET)
    public CommonReturnType hibernateValidator(@Range(min = 1, max = 9, message = "年级只能从1-9")
                                               @RequestParam(name = "grade", required = true)
                                                       int grade,
                                               @Min(value = 1, message = "班级最小只能1")
                                               @Max(value = 99, message = "班级最大只能99")
                                               @RequestParam(name = "classroom", required = true)
                                                       int classroom) {
        //http://127.0.0.1:8090/hibernateValidator?grade=10&classroom=100

        System.out.println(grade + "," + classroom);
        return CommonReturnType.create(grade + "," + classroom);
    }

    @RequestMapping("/hibernateValidator2")
    public CommonReturnType hibernateValidator2() {

//        @Null 被注释的元素必须为 null
//        @NotNull    被注释的元素必须不为 null
//        @AssertTrue 被注释的元素必须为 true
//        @AssertFalse 被注释的元素必须为 false
//        @Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值
//        @Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值
//        @DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值
//        @DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值
//        @Size(max=, min=)   被注释的元素的大小必须在指定的范围内
//        @Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内
//        @Past   被注释的元素必须是一个过去的日期
//        @Future     被注释的元素必须是一个将来的日期
//        @Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式
//
//        Hibernate Validator 附加的 constraint
//        @NotBlank(message =)   验证字符串非null，且长度必须大于0
//        @Email  被注释的元素必须是电子邮箱地址
//        @Length(min=,max=)  被注释的字符串的大小必须在指定的范围内
//        @NotEmpty   被注释的字符串的必须非空
//        @Range(min=,max=,message=)  被注释的元素必须在合适的范围内

        //http://127.0.0.1:8090/hibernateValidator2
        SysUser user = new SysUser();
        user.setName("Zrc");
        user.setGender(2);
        Set<ConstraintViolation<SysUser>> violationSet = validator.validate(user);
        if (!violationSet.isEmpty()) {
            for (ConstraintViolation<SysUser> model : violationSet) {
                System.out.println(model.getMessage());
            }
            throw new ConstraintViolationException("ZRC", violationSet);
        }
        return CommonReturnType.create(user.getName());
    }


}
