package com.demo.server.application.reflection;

import com.demo.server.dal.model.DemoRecord;
import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vince Yuan
 * @date 04/28/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestReflection {

    @Test
    public void test() throws Exception {
//        testReflection();
        testGetType();
    }

    /**
     *  Unable to get the actual type argument while list object has been instantiated <br/>
     *  -- Since the actual type argument has been erased by JVM
     *  -- Which means the actual type argument can be only retrieved before instantiation (e.g. during compilation)
     */
    private void testGetType() {

        List<String> list = Arrays.asList("hello, world!");
        Class<? extends List> clazz = list.getClass();
        log.info("=== XXX | class: {} ===", clazz);
        Class<?> componentType = clazz.getComponentType();
        log.info("=== XXX | componentType: {} ===", componentType);
        Type genericSuperclass = clazz.getGenericSuperclass();
        log.info("=== XXX | genericSuperclass: {} ===", genericSuperclass);
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        log.info("=== XXX | genericInterfaces: {} ===", Arrays.asList(genericInterfaces));

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Class<?> type = declaredField.getType();
            log.info("=== XXX | declaredField: {} | type: {} ===", declaredField, type);
            if (type == java.util.List.class) {
                // 如果是List类型，得到其Generic的类型
                Type genericType = declaredField.getGenericType();
                if (genericType != null) {
                    if (genericType instanceof ParameterizedType) {
                        ParameterizedType pt = (ParameterizedType) genericType;
                        Type[] actualTypeArguments = pt.getActualTypeArguments();
                        log.info("=== XXX | actualTypeArguments: {} ===", Arrays.asList(actualTypeArguments));
                    }
                }
            }
        }
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
