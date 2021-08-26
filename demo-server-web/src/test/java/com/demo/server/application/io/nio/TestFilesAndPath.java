package com.demo.server.application.io.nio;

import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Vince Yuan
 * @date 08/18/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestFilesAndPath {

    @Test
    public void test() throws Exception {
        System.out.println("testWrite:");
        testWrite();
        System.out.println("testFileUtilsWrite:");
        testFileUtilsWrite();
    }

    private void testFileUtilsWrite() throws Exception {
        byte[] content = "IT金融管理三不误".getBytes();
        String filePath = "E:/test/testFileUtilsWrite/test_text_001.txt";
        File file = new File(filePath);
        FileUtils.writeByteArrayToFile(file, content);
    }

    private void testWrite() throws Exception {
        byte[] content = "IT金融管理三不误".getBytes();
        String filePath = "E:/test/testWrite/test_text_001.txt";
        Path path = Paths.get(filePath);
        System.out.println("path: " + path);
        Path parentPath = path.getParent();
        System.out.println("parent path: " + parentPath);
        if (parentPath != null) {
            String parentPathString = parentPath.toString();
            System.out.println("parent path string: " + parentPathString);
            if (!Files.exists(parentPath)) {
                Files.createDirectories(parentPath);
            }
        }
        if (Files.exists(path)) {
            Files.delete(path);
        }
        Files.write(path, content);
    }
}
