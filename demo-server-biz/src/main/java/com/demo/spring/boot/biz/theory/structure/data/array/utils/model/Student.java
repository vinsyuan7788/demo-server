package com.demo.spring.boot.biz.theory.structure.data.array.utils.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Vince Yuan
 * @date 03/22/2021
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Comparable<Student>, Serializable {

    private Long id;

    private String studentName;

    private Integer studentAge;

    @Override
    public int compareTo(Student o) {
        return this.getStudentAge().compareTo(o.getStudentAge());
    }
}
