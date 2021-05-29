package com.demo.server.application.list.memory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vince Yuan
 * @date 01/18/2021
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Long id;

    private String name;
}
