package com.zrc.springboottutorial.controller;

import com.zrc.springboottutorial.kafka.KafkaMessageSendService;
import com.zrc.springboottutorial.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/8/1 10:37
 * Description: No Description
 */

@RestController
@RequestMapping("kafka")
public class KafkaController {
    @Autowired
    private KafkaMessageSendService kafkaMessageSendService;

    @RequestMapping(value="/sendMessage",method= RequestMethod.POST)
    public String send(@RequestParam(required=true) String message){
        try {
            kafkaMessageSendService.send(message);
        } catch (Exception e) {
            return "send failed.";
        }
        return message;
    }
    @RequestMapping(value="/sendMessage2",method= RequestMethod.POST)
    public String send2(@RequestParam(required=true) String message){
        try {
            SysUser user = new SysUser();
            user.setName(message);
            user.setGender(1);
            user.setBirthday(new Date());
            kafkaMessageSendService.send(user);
        } catch (Exception e) {
            return "send failed.";
        }
        return message;
    }
}
