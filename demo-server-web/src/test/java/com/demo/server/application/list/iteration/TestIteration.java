package com.demo.server.application.list.iteration;

import com.demo.server.application.list.iteration.model.Student;
import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
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
        System.out.println("isEqualList:");
        isEqualList();
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

    private void isEqualList() {
        List<Long> list1 = Arrays.asList(1L, 2L, 3L);
        List<Long> list2 = Arrays.asList(2L, 1L);
        boolean isEqual = isEqualListWithoutOrder(list1, list2);
        System.out.println("是否相等: " + isEqual);
    }

    private boolean isEqualListWithoutOrder(List<Long> list1, List<Long> list2) {
        list1 = new ArrayList<>(list1);
        list2 = new ArrayList<>(list2);
        int list1Size = list1.size();
        for (int i = 0; i < list1Size; i++) {
            Long element = list1.get(i);
            System.out.println("element: " + element);
            if (list2.contains(element)) {
                list2.remove(element);
                System.out.println("list2: " + list2);
                if (i != list1Size - 1 && CollectionUtils.isEmpty(list2)) {
                    return false;
                }
            } else {

                return false;
            }
        }
        System.out.println("list2: " + list2);
        return CollectionUtils.isEmpty(list2);
    }
}
