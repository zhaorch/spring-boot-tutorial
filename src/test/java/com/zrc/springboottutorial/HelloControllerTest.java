package com.zrc.springboottutorial;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.assertj.core.api.Assertions.*;
/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/8/6 15:59
 * Description: No Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp (){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testHello () throws Exception{

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/helloworld2")
                .param("name","zrc")
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        System.out.println(result.getResponse().getContentAsString());

        Assert.assertEquals("Hello zrc,zrc",result.getResponse().getContentAsString());


        assertThat("abc").as("校验abc").isEqualTo("abc").contains("c");
    }
}
