package com.demo.spring.boot.application.reflection;

import com.demo.spring.boot.dal.model.DemoRecord;
import com.demo.spring.boot.web.DemoSpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vince Yuan
 * @date 04/28/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSpringBootApplication.class)
public class TestReflection {

    @Test
    public void test() throws Exception {
        testReflection();
    }

    private void testReflection() throws Exception {
        DemoRecord demoRecord = new DemoRecord();
        demoRecord.setId(1L);
        demoRecord.setDemoName("vinsy");
        log.info("=== testReflection | demoRecord: {} ===", demoRecord);
        demoRecord = emptyFieldsIfNecessary(demoRecord, new String[] { "demoName", "alibaba", "common", "base" });
        log.info("=== testReflection | demoRecord: {} ===", demoRecord);
    }

    private DemoRecord emptyFieldsIfNecessary(DemoRecord demoRecord, String[] fieldsToEmpty) throws IllegalAccessException {
        List<String> fieldNames = Arrays.asList(fieldsToEmpty);
        Field[] declaredFields = demoRecord.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String declaredFieldName = declaredField.getName();
            if (fieldNames.contains(declaredFieldName)) {
                declaredField.setAccessible(true);
                declaredField.set(demoRecord, null);
            }
        }
        return demoRecord;
    }
}
