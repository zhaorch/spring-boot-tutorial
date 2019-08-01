package com.zrc.springboottutorial.kafka.serializer;

import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/8/1 13:39
 * Description: No Description
 */

public class MyObjectDeSerializer implements Deserializer {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        Object object = null;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            object = objectInputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    @Override
    public void close() {

    }
}
