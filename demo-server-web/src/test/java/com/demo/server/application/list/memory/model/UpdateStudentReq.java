package com.demo.server.application.list.memory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Vince Yuan
 * @date 01/18/2021
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentReq {

    List<Student> studentList;
}
