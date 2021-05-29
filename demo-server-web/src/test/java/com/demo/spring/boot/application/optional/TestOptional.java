package com.demo.spring.boot.application.optional;

import com.demo.spring.boot.application.optional.model.Student;
import com.demo.spring.boot.web.DemoSpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * @author Vince Yuan
 * @date 02/01/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSpringBootApplication.class)
public class TestOptional {

    @Test
    public void test() {
        System.out.println("\ntestOptional:");
        testOptional();
    }

    private void testOptional() {
        Student student = createStudent(false, 1L, "Tom");
        System.out.println("Student: " + student);
        student = Optional.ofNullable(student).orElse(createStudent(true, 2L, "Jerry"));
        System.out.println("Student: " + student);
        student = Optional.ofNullable(student).orElseGet(() -> createStudent(true, 2L, "Jerry"));
        System.out.println("Student: " + student);
    }

    private Student createStudent(boolean flag, Long id, String name) {
        return flag ? Student.builder().id(id).name(name).build() : null;
    }
}
