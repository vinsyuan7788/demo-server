package com.demo.spring.boot.application.matcher;

import com.demo.spring.boot.web.DemoSpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Vince Yuan
 * @date 05/07/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSpringBootApplication.class)
public class TestMatcher {

    @Test
    public void test() {
        testMatcher();
    }

    private void testMatcher() {
        String content = "{提交人}提交的委外策略{委外策略名称}审核不通过，请悉知";
        Pattern pattern = Pattern.compile("\\{([^\\}]+)");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find() && matcher.groupCount() > 0){
            String description = matcher.group(1);
            log.info("=== testMatcher | description: {} ===", description);
//            //内容替换
//            String var = NewsVariableEnum.findCode(descrption);
//            String value="";
//            //如果没有值则为空
//            if(param.containsKey(var)){
//                value=param.get(var);
//            }
//            content=content.replace("{"+descrption+"}",value);
        }
    }
}
