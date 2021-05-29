package com.demo.server.application.list.iteration;

import com.demo.server.web.DemoServerApplication;
import com.demo.server.application.list.iteration.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestIteration {

    @Test
    public void test() {
        System.out.println("testOrder:");
        testOrder();
        System.out.println("testSetField:");
        testSetField();
    }

    private void testOrder() {
        List<Integer> originList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> newList = new ArrayList<>(originList.size());
        for (Integer integer : originList) {
            System.out.println(integer);
            newList.add(integer);
        }
        System.out.println(newList);
    }

    private void testSetField() {
        Student s1 = new Student("Tom");
        Student s2 = new Student("Jerry");
        List<Student> studentList = new ArrayList<>();
        studentList.add(s1); studentList.add(s2);
        System.out.println(studentList);

        long id = 10086L;
        studentList.forEach(
                student -> {
                    System.out.println("Before | " + student);
                    student.setId(id);
                    System.out.println("After | " + student);
                }
        );

        System.out.println(studentList);
    }
}
