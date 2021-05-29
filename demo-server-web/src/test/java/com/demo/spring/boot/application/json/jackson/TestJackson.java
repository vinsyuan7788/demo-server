package com.demo.spring.boot.application.json.jackson;

import com.demo.spring.boot.resp.GetEnvironmentResp;
import com.demo.spring.boot.web.DemoSpringBootApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Vince Yuan
 * @date 03/01/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSpringBootApplication.class)
public class TestJackson {

    @Autowired
    private Environment environment;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test() throws Exception {
        GetEnvironmentResp resp = GetEnvironmentResp.builder()
                .activeProfiles(environment.getActiveProfiles())
                .defaultProfiles(environment.getDefaultProfiles())
                .build();
        log.info("=== Resp: {} ===", resp);
        /**
         *  Below will throw an exception:
         *  -- No serializer found for class java.lang.Object and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
         *     -- Which is due to the lack of getter of Random-typed field in Spring-boot source code
         */
        String s = objectMapper.writeValueAsString(resp);
        System.out.println(s);
    }
}
