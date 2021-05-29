package com.demo.server.common.utils;

import com.demo.base.common.utils.LogUtils;
import com.demo.base.common.utils.utils.ParametersToLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.ListUtils;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *  This class is used for Zip manipulation
 *
 * @author Vince Yuan
 * @date 04/24/2021
 */
@Slf4j
public class ZipUtils {

    /**
     *  Privatize the constructor
     */
    private ZipUtils() {}

    /**
     *  Compress the source files to a Zip file <br/>
     *  -- If the source file cannot be found, it will be skipped automatically <br/>
     *  -- If the output zip file does not exist, it will be created automatically
     *
     * @param sourceFilePaths the paths of files that need to be zipped
     * @param zipFilePath the output zip file path
     * @return if zip operation is performed successfully
     */
    public static boolean zip(List<String> sourceFilePaths, String zipFilePath) {
        // Initialize necessary streams
        FileInputStream fileInputStream = null;
        WritableByteChannel zipOutputChannel = null;
        try {
            // Check arguments
            if (CollectionUtils.isEmpty(sourceFilePaths)) {
                log.info(LogUtils.getLogMessage("zip", "Source files are empty, hence there is no need to zip"));
                return true;
            }
            // Filter those source files that do exist
            List<String> existingSourceFilePaths = sourceFilePaths.stream()
                    .filter(sourceFilePath -> new File(sourceFilePath).exists())
                    .collect(Collectors.toList());
            if (CollectionUtils.isEmpty(existingSourceFilePaths)) {
                log.info(LogUtils.getLogMessage("zip", "All source files do not exist, hence there is no need to zip"));
                return true;
            }
            if (existingSourceFilePaths.size() < sourceFilePaths.size()) {
                List<String> missingSourceFilePaths = ListUtils.subtract(sourceFilePaths, existingSourceFilePaths);
                log.info(LogUtils.getLogMessage("zip", "Missing source files are found", new ParametersToLog().addParameter("missing source file paths", missingSourceFilePaths)));
            }
            // Create the output zip file if it does not exist
            File zipFile = new File(zipFilePath);
            if (!zipFile.exists()) {
                if (!zipFile.createNewFile()) {
                    log.error(LogUtils.getLogMessage("zip", "The output zip file cannot be created", new ParametersToLog().addParameter("zip file path", zipFilePath)));
                }
            }
            // Get output stream and channel
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
            zipOutputChannel = Channels.newChannel(zipOutputStream);
            // Iterate all source files and write them to a Zip file
            List<File> existingSourceFiles = existingSourceFilePaths.stream().map(File::new).collect(Collectors.toList());
            for (File existingSourceFile : existingSourceFiles) {
                fileInputStream = new FileInputStream(existingSourceFile);
                FileChannel fileInputChannel = fileInputStream.getChannel();
                zipOutputStream.putNextEntry(new ZipEntry(existingSourceFile.getName()));
                fileInputChannel.transferTo(0, existingSourceFile.length(), zipOutputChannel);
                fileInputStream.close();
            }
        } catch (Exception e) {
            log.error(LogUtils.getLogMessage("zip", "Exception is found when zip operation is being performed"), e);
            return false;
        } finally {
            // Close the I/O streams and channels
            try {
                // Closing the stream will close the associated channel as well (see FileInputStream#close())
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                // Closing the channel will in turn cause the stream to be closed (see Channels#newChannel(outputStream))
                if (zipOutputChannel != null) {
                    zipOutputChannel.close();
                }
            } catch (Exception ex) {
                log.error(LogUtils.getLogMessage("zip", "Exception is found when I/O streams and channels are being closed"), ex);
                return false;
            }
        }
        return true;
    }
}
