package com.zrc.springboottutorial.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/7/24 18:30
 * Description: No Description
 */

public class Greeting extends ResourceSupport {

    private final String content;

    @JsonCreator
    public Greeting(@JsonProperty("content") String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
