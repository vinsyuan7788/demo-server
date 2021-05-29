package com.demo.spring.boot.application.list.memory;

import com.demo.spring.boot.web.DemoSpringBootApplication;
import com.demo.spring.boot.application.list.memory.model.Student;
import com.demo.spring.boot.application.list.memory.model.UpdateStudentReq;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author Vince Yuan
 * @date 01/18/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSpringBootApplication.class)
public class TestMemoryAllocation {

    @Test
    public void test() {
        testSetFieldForInstanceOfObjectType();
    }

    private void testSetFieldForInstanceOfObjectType() {
        // Build testing data
        Student s1 = Student.builder().name("Tom").build();
        Student s2 = Student.builder().name("Jerry").build();
        Student s3 = Student.builder().name("Coco").build();
        List<Student> students = Arrays.asList(s1, s2, s3);
        UpdateStudentReq req = UpdateStudentReq.builder().studentList(students).build();
        // Perform testing
        if (updateStudent(req)) {
            List<Student> studentList = req.getStudentList();
            // all students will have IDs
            System.out.println(studentList);
            // all students will have IDs
            System.out.println(students);
        }
    }

    private Boolean updateStudent(UpdateStudentReq req) {
        List<Student> studentList = req.getStudentList();
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            student.setId(i + 1L);
        }
        return true;
    }
}
