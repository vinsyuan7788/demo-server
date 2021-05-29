package com.demo.spring.boot.application.utils;

import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class MatchUtils {

    public static void findLinesContainedInBothFiles(String filePath1, String filePath2, String outputFilePath) throws Exception {
        List<String> linesFromFile1 = Files.readAllLines(Paths.get(filePath1)).stream().map(line -> line.trim()).collect(Collectors.toList());
        List<String> linesFromFile2 = Files.readAllLines(Paths.get(filePath2)).stream().map(line -> line.trim()).collect(Collectors.toList());
        List<String> linesInBothFiles = linesFromFile1.stream().filter(lineFromFile1 -> linesFromFile2.contains(lineFromFile1)).collect(Collectors.toList());
        Path outputPath = writeOrUpdate(outputFilePath, linesInBothFiles);
        System.out.println("The lines contained in both files are written to " + outputPath.toString());
    }

    private static Path writeOrUpdate(String outputFilePath, List<String> linesInBothFiles) throws Exception {
        Path path = Paths.get(outputFilePath);
        if (!Files.exists(path)) Files.delete(path);
        return Files.write(Paths.get(outputFilePath), linesInBothFiles, StandardOpenOption.WRITE);
    }
}
