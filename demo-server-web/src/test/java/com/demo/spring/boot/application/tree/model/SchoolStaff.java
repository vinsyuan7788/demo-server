package com.demo.spring.boot.application.tree.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vince Yuan
 * @date 01/14/2021
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolStaff {

    private Long id;

    private String name;

    private Long parentId;

    private List<SchoolStaff> children;

    public void addChild(SchoolStaff schoolStaff) {
        if (CollectionUtils.isEmpty(children)) {
            children = new ArrayList<>();
        }
        children.add(schoolStaff);
    }
}
