package com.demo.spring.boot.application.list.comparator;

import com.demo.spring.boot.application.list.comparator.model.Student;
import com.demo.spring.boot.web.DemoSpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vince Yuan
 * @date 12/31/2020
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSpringBootApplication.class)
public class TestComparator {

    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void test() throws Exception {
        System.out.println("Test Number Comparator:");
        testNumberComparator();
        System.out.println("\nTest Date Comparator:");
        testDateComparator();
    }

    private void testNumberComparator() throws Exception {
        List<Student> students = new ArrayList<>();
        students.add(Student.builder().id(1L).birthDate(df.parse("2020-01-01 12:00:00")).build());
        students.add(Student.builder().id(2L).birthDate(df.parse("2020-06-01 12:00:00")).build());
        students.add(Student.builder().id(3L).birthDate(df.parse("2020-12-01 12:00:00")).build());
        System.out.println("Before | students: " + students);
        List<Student> sortedStudents = students.stream()
                // Sort in descending order and select the first N objects
                .sorted((o1, o2) -> o1.getId().compareTo(o2.getId()) >= 0 ? -1 : 1).limit(2)
                .collect(Collectors.toList());
        System.out.println("After | students: " + sortedStudents);
        sortedStudents = students.stream()
                // Sort in ascending order and select the first N objects
                .sorted((o1, o2) -> o1.getId().compareTo(o2.getId()) >= 0 ? 1 : -1).limit(2)
                .collect(Collectors.toList());
        System.out.println("After | students: " + sortedStudents);
        sortedStudents = students.stream()
                // Sort in ascending order and select the first N objects
                .sorted(Comparator.comparingLong(student -> student.getId())).limit(2)
                .collect(Collectors.toList());
        System.out.println("After | students: " + sortedStudents);
    }

    private void testDateComparator() throws Exception {
        List<Student> students = new ArrayList<>();
        students.add(Student.builder().id(1L).birthDate(df.parse("2020-01-01 12:00:00")).build());
        students.add(Student.builder().id(2L).birthDate(df.parse("2020-06-01 12:00:00")).build());
        students.add(Student.builder().id(3L).birthDate(df.parse("2020-12-01 12:00:00")).build());
        System.out.println("Before | students: " + students);
        List<Student> sortedStudents = students.stream()
                // Sort in descending order and select the first N objects
                .sorted((o1, o2) -> o1.getBirthDate().compareTo(o2.getBirthDate()) >= 0 ? -1 : 1).limit(2)
                .collect(Collectors.toList());
        System.out.println("After | students: " + sortedStudents);
        sortedStudents = students.stream()
                // Sort in ascending order and select the first N objects
                .sorted((o1, o2) -> o1.getBirthDate().compareTo(o2.getBirthDate()) >= 0 ? 1 : -1).limit(2)
                .collect(Collectors.toList());
        System.out.println("After | students: " + sortedStudents);
    }
}
