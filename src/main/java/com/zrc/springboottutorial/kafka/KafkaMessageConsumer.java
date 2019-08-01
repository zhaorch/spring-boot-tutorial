package com.zrc.springboottutorial.kafka;

import com.zrc.springboottutorial.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/8/1 10:57
 * Description: No Description
 */

@Component
public class KafkaMessageConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    //关闭Kafka监听
    //@KafkaListener(topics={"${kafka.app.topic.zrc}"})
    public void receive(@Payload SysUser user, @Headers MessageHeaders headers){
        //LOG.info("KafkaMessageConsumer 接收到消息："+message);
        LOG.info("KafkaMessageConsumer 接收到消息："+user.toString());
        //ConsumerRecord record = (ConsumerRecord) message;
        //LOG.info("KafkaMessageConsumer 接收到消息："+((SysUser)record.value()).toString());
        //headers.keySet().forEach(key->LOG.info("{}: {}",key,headers.get(key)));
    }
}
