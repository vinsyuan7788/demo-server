package com.demo.server.application.list.comparator.comparator;

import com.demo.server.application.list.comparator.model.Student;

import java.util.Comparator;

/**
 * @author Vince Yuan
 * @date 12/31/2020
 */
public class DateComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getBirthDate().compareTo(o2.getBirthDate()) >= 0 ? -1 : 1;
    }
}
