package com.demo.server.application.list.stream;

import com.demo.server.web.DemoServerApplication;
import com.demo.server.application.list.stream.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestStream {

    @Test
    public void test() {
        System.out.println("testFilter:");
        testFilter();
        System.out.println("\ntestGroup:");
        testGroup();
        System.out.println("\ntestListFlatten:");
        testListFlatten();
        System.out.println("\ntestEmptyListMap:");
        testEmptyListMap();
        System.out.println("\ntestEmptyListGroup:");
        testEmptyListGroup();
        System.out.println("\ntestMapAndFlatMap:");
        testMapAndFlatMap();
    }

    private void testFilter() {
        List<Long> listBefore = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        System.out.println(listBefore);
        List<Long> listAfter = listBefore.stream().filter(num -> num % 2 == 0).collect(Collectors.toList());
        System.out.println(listAfter);
    }

    private void testGroup() {
        Student s1 = Student.builder().level("level_one").name("Tom").build();
        Student s2 = Student.builder().level("level_one").name("Jerry").build();
        Student s3 = Student.builder().level("level_two").name("Yoyo").build();
        Student s4 = Student.builder().level("level_three").name("Jojo").build();
        List<Student> list = Arrays.asList(s1, s2, s3, s4);
        Map<String, List<Student>> studentsByLevel = list.stream().collect(Collectors.groupingBy(Student::getLevel));
        System.out.println(studentsByLevel);

        s1 = Student.builder().level("effect").name("Tom").build();
        s2 = Student.builder().level("effect").name("Jerry").build();
        s3 = Student.builder().level("history").name("Yoyo").build();
        s4 = Student.builder().level("history").name("Jojo").build();
        list = Arrays.asList(s1, s2, s3, s4);
        studentsByLevel = list.stream().collect(Collectors.groupingBy(Student::getLevel));
        System.out.println(studentsByLevel);
    }

    private void testListFlatten() {
        // Build test data
        Student s1 = Student.builder().level("level_one").name("Tom").build();
        Student s2 = Student.builder().level("level_one").name("Jerry").build();
        Student s3 = Student.builder().level("level_two").name("Yoyo").build();
        Student s4 = Student.builder().level("level_three").name("Jojo").build();
        List<Student> studentList1 = Arrays.asList(s1, s2);
        List<Student> studentList2 = Arrays.asList(s3, s4);
        Map<String, List<Student>> map = new HashMap<>();
        map.put("level_one", studentList1);
        map.put("level_else", studentList2);
        System.out.println(studentList1);
        System.out.println(studentList2);
        // Perform testing
        List<Student> students = map.values().stream().collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
        System.out.println(students);
        students = map.values().stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println(students);
    }

    private void testEmptyListMap() {
        List<Long> longNumbers = new ArrayList<>();
        List<Integer> integerNumbers = longNumbers.stream().map(longNumber -> {
            Integer integer = new Integer(String.valueOf(longNumber));
            return integer;
        }).collect(Collectors.toList());
        System.out.println(integerNumbers);
    }

    private void testEmptyListGroup() {
        List<Student> list = new ArrayList<>();
        System.out.println(list);
        Map<String, List<Student>> map = list.stream().collect(Collectors.groupingBy(Student::getLevel));
        System.out.println(map);
    }

    private void testMapAndFlatMap() {
        List<Long> longList1 = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        List<Long> longList2 = Arrays.asList(10L, 20L, 30L, 40L, 50L);
        List<Long> longList3 = Arrays.asList(100L, 200L, 300L, 400L, 500L);
        List<List<Long>> longLists = Arrays.asList(longList1, longList2, longList3);
        List<Long> longs1 = longLists.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println(longs1);
        List<Long> longs2 = longLists.stream().flatMap(longList -> longList.stream().map(longNum -> longNum * 2)).collect(Collectors.toList());
        System.out.println(longs2);
        List<ListIterator<Long>> longListIterators = longLists.stream().map(List::listIterator).collect(Collectors.toList());
        System.out.println(longListIterators);
    }
}
