package com.demo.server.application.tree;

import com.demo.server.application.tree.model.SchoolStaff;
import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author Vince Yuan
 * @date 01/14/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestTree {

    @Test
    public void test() {
        testBuildTree();
    }

    private void testBuildTree() {
        // Build testing data
        SchoolStaff s1 = SchoolStaff.builder().id(1L).name("Tom").children(new ArrayList<>()).build();
        SchoolStaff s2 = SchoolStaff.builder().id(2L).name("Cat").parentId(1L).build();
        SchoolStaff s3 = SchoolStaff.builder().id(3L).name("Jerry").children(new ArrayList<>()).build();
        SchoolStaff s4 = SchoolStaff.builder().id(4L).name("Mouse").parentId(3L).build();
        SchoolStaff s5 = SchoolStaff.builder().id(5L).name("Rat").parentId(3L).build();
        Map<Long, List<SchoolStaff>> parentMap = new HashMap<>();
        parentMap.put(1L, Arrays.asList(s1));
        parentMap.put(3L, Arrays.asList(s3));
        List<SchoolStaff> children = Arrays.asList(s2, s4, s5);
        // Perform testing
        children.forEach(child -> {
            Long parentId = child.getParentId();
            if (parentId != null) {
                List<SchoolStaff> parents = parentMap.get(parentId);
                parents.forEach(parent -> {
                    parent.addChild(child);
                });
            }
        });
        List<SchoolStaff> schoolStaffTree = parentMap.values().stream().collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
        System.out.println(schoolStaffTree);
    }
}
