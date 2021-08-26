package com.demo.server.application.clazz;

import com.alibaba.fastjson.JSONObject;
import com.demo.server.application.clazz.bean.BaseClass;
import com.demo.server.application.list.stream.model.Student;
import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.test.context.junit4.SpringRunner;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;

/**
 * @author Vince Yuan
 * @date 05/15/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestClass {

    @Test
    public void test() throws Exception {
        System.out.println("=== testClass() ===");
        testClass();
        System.out.println("=== testDynamicField ===");
        testDynamicField();
    }

    private void testDynamicField() throws Exception {
        // Fields pending to be added
        String[] fieldName = new String[] { "overdueMoney", "overdueStage" };
        // Get information regarding bean
        BaseClass baseClass = new BaseClass();
        Class<? extends BaseClass> clazz = baseClass.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        System.out.println("bean info: " + JSONObject.toJSONString(beanInfo));
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        System.out.println("number of property descriptors: " + propertyDescriptors.length);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            System.out.println("property descriptor: " + JSONObject.toJSONString(propertyDescriptor));
        }
        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        System.out.println("number of method descriptors: " + methodDescriptors.length);
        for (MethodDescriptor methodDescriptor : methodDescriptors) {
            System.out.println("method descriptor: " + JSONObject.toJSONString(methodDescriptor));
        }
        // The underlying mechanism is implemented by ASM
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.setSuperclass(clazz);
        beanGenerator.addProperty("overdueMoney", BigDecimal.class);
        beanGenerator.addProperty("overdueStage", Integer.class);
        BaseClass bean = (BaseClass) beanGenerator.create();
        System.out.println("bean: " + JSONObject.toJSONString(bean));
        BeanMap beanMap = BeanMap.create(bean);
        System.out.println("bean map: " + JSONObject.toJSONString(beanMap));
        beanMap.put("id", 10086L);
        beanMap.put("overdueMoney", BigDecimal.ONE);
        beanMap.put("overdueStage", 1);
        System.out.println("bean: " + JSONObject.toJSONString(bean));
        System.out.println("bean map: " + JSONObject.toJSONString(beanMap));
    }

    private void testClass() {
        Class clazz = Student.class;
        log.info("=== test | 是否是相同的类: {} ===", clazz.equals(Student.class));
    }
}
