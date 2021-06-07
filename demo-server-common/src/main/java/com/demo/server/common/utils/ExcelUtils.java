package com.demo.server.common.utils;

/**
 * @author Vince Yuan
 * @date 06/04/2021
 */

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  This class is used for Excel manipulation
 *
 * @author Vince Yuan
 * @date 04/23/2021
 */
@Slf4j
public class ExcelUtils {

    /**
     *  Privatize the constructor
     */
    private ExcelUtils() {}

    /**
     *  Export data to an output stream (with multiple sheets)
     *
     * @param outputStream the output stream to export the data
     * @param sheetNameAndDataMap the mapping between the name of the sheet and the data of the sheet
     * @param dataType the type of the data
     * @return if the data is exported successfully
     */
    public static boolean exportData(OutputStream outputStream, Map<String, List<? extends BaseRowModel>> sheetNameAndDataMap, Class<? extends BaseRowModel> dataType) {

        /**
         *  Check the input argument
         *  -- If no output stream is provided, return directly
         *  -- If there is no data to export, return directly
         *  -- If no data type is provided, return directly
         */
        if (outputStream == null) {
            log.error("=== export | No output stream is provided, hence data is unable to be exported ===");
            return false;
        }
        if (MapUtils.isEmpty(sheetNameAndDataMap)) {
            log.info("=== export | There is no data to export ===");
            return true;
        }
        if (dataType == null) {
            log.error("=== export | No data type is provided, hence data is unable to be exported ===");
            return false;
        }

        // Initialize necessary I/O streams
        BufferedOutputStream bos = null;
        try {
            // Get the Excel writer
            bos = new BufferedOutputStream(outputStream);
            ExcelWriter writer = new ExcelWriter(bos, ExcelTypeEnum.XLSX, true);
            // Iterate all sheets and data
            for (Map.Entry<String, List<? extends BaseRowModel>> sheetNameAndData : sheetNameAndDataMap.entrySet()) {
                // Get the name of each sheet and the data to be written to the sheet
                String sheetName = sheetNameAndData.getKey();
                List<? extends BaseRowModel> data = sheetNameAndData.getValue();
                // Write the data to the sheet
                Sheet sheet = new Sheet(1, 0, dataType);
                sheet.setSheetName(sheetName);
                writer.write(data, sheet);
            }
            // Finish the writer
            writer.finish();
        } catch (Exception e) {
            log.error("=== export | Exception is found when data is being exported ===", e);
            return false;
        } finally {
            // Close the I/O streams
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception ex) {
                    log.error("=== export | Exception is found when I/O streams are being closed ===", ex);
                }
            }
        }
        return true;
    }

    /**
     *  Read data from an input stream
     *
     * @param inputStream the input stream to import the data
     * @param sheetNo the numbering of the sheet
     * @param startLine the start line of the sheet
     * @param endLine the end line of the sheet (which can be null if all the data needs to be read)
     * @param clazz the model that encapsulate the data row
     * @return the data that has been read
     */
    public static List<Object> importData(InputStream inputStream, int sheetNo, int startLine, Integer endLine, Class<? extends BaseRowModel> clazz) {
        if (endLine != null && endLine <= startLine) {
            log.info("=== importData | The number of lines to be read is 0, return directly ===");
            return new ArrayList<>();
        }
        Integer totalLinesToRead = endLine != null ? endLine - startLine : null;
        final List<Object> rows = new ArrayList<Object>();
        new ExcelReader(inputStream, null, new AnalysisEventListener() {
            @Override
            public void invoke(Object object, AnalysisContext context) {
                if ((totalLinesToRead != null && totalLinesToRead > rows.size()) || totalLinesToRead == null) {
                    rows.add(object);
                }
            }
            @Override
            public void doAfterAllAnalysed(AnalysisContext context) { }
        }, false).read(new Sheet(sheetNo, startLine, clazz));
        return rows;
    }

    /**
     *  Write data to an Excel file on the disk
     *
     * @param filePath the path of the Excel file on the disk
     * @param sheetNameAndDataMap the mapping between the name of the sheet and the data of the sheet
     * @param dataType the type of the data
     * @return if the data is written successfully
     */
    public static boolean writeToDisk(String filePath, Map<String, List<? extends BaseRowModel>> sheetNameAndDataMap, Class<? extends BaseRowModel> dataType) {
        try {
            return exportData(new FileOutputStream(filePath), sheetNameAndDataMap, dataType);
        } catch (Exception e) {
            log.error("=== writeToDisk | Exception is found when data is being written to disk ===", e);
            return false;
        }
    }

    /**
     *  Read data from an Excel file on the disk
     *
     * @param filePath the path of the Excel file on the disk
     * @param sheetNo the numbering of the sheet
     * @param startLine the start line of the sheet (inclusive)
     * @param endLine the end line of the sheet (exclusive, which can be null if all the data needs to be read)
     * @param clazz the model that encapsulate the data row
     * @return the data that has been read
     */
    public static List<Object> readFromDisk(String filePath, int sheetNo, int startLine, Integer endLine, Class<? extends BaseRowModel> clazz) {
        try {
            return importData(new BufferedInputStream(new FileInputStream(filePath)), sheetNo, startLine, endLine, clazz);
        } catch (Exception e) {
            log.error("=== readFromDisk | Exception is found when data is being read from disk ===", e);
            return new ArrayList<>();
        }
    }
}
