package com.demo.spring.boot.application.list.comparator.comparator;

import com.demo.spring.boot.application.list.comparator.model.Student;

import java.util.Comparator;

/**
 * @author Vince Yuan
 * @date 12/31/2020
 */
public class LongComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getId().longValue() >= o2.getId().longValue() ? -1 : 1;
    }
}
