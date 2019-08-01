package com.zrc.springboottutorial.kafka.serializer;

import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/8/1 13:29
 * Description: No Description
 */

public class MyObjectSerializer implements Serializer<Object> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Object data) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] dataArray = null;
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(data);
            dataArray = outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dataArray;
    }

    @Override
    public void close() {

    }
}
