package com.rse.webservice.locket.utils;


import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;

/**
 * @author MENES
 * @version 0.0.1
 * @since 01 Jan 2024
 */
public class FileUtils {

    private static final String BYTES = "B";
    private static final String KILOBYTES = "KB";
    private static final String MEGABYTES = "MB";
    private static final String GIGABYTES = "GB";
    private static final String TOO_LARGE = "Too large to display";

    /**
     * Get the file extension of a given file.
     *
     * @param fileName The file name whose extension needs to be determined.
     * @return The file extension or an empty string if the file has no extension.
     */
    public static String getFileExtension(String fileName) {


        int lastDotIndex = fileName.lastIndexOf('.');

        if (lastDotIndex == -1 || lastDotIndex == 0 || lastDotIndex == fileName.length() - 1) {
            // No dot or dot at the beginning or end of the file name
            return "";
        }

        return fileName.substring(lastDotIndex + 1);
    }

    public static String getFileExtension(File file) {
        if (file == null || !file.isFile()) {
            return "";
        }
        return getFileExtension(file.getName());
    }

    /**
     * Get the size of a file in different units (KB, MB, GB).
     *
     * @param file The file whose size needs to be determined.
     * @return A formatted string with the file size in KB, MB, or GB.
     * Returns an empty string if the file doesn't exist.
     */
    public static String getFileSizeFormatted(File file) {
        long fileSize = getFileSize(file);

        if (fileSize == -1) {
            return ""; // File doesn't exist
        }

        int sizeUnit = (int) (Math.log(fileSize) / Math.log(1024));
        return switch (sizeUnit) {
            case 0 -> fileSize + BYTES;
            case 1 -> String.format("%.2f %s", fileSize / 1024.0, KILOBYTES);
            case 2 -> String.format("%.2f %s", fileSize / (1024.0 * 1024), MEGABYTES);
            case 3 -> String.format("%.2f %s", fileSize / (1024.0 * 1024 * 1024), GIGABYTES);
            default -> TOO_LARGE; // Handle extremely large files
        };
    }

    /**
     * Get the size of a file in bytes.
     *
     * @param file The file whose size needs to be determined.
     * @return The size of the file in bytes, or -1 if the file doesn't exist.
     */
    public static long getFileSize(File file) {
        if (fileExists(file)) {
            return file.length();
        } else {
            return -1;
        }
    }

    /**
     * Check if a file exists.
     *
     * @param file The file to check for existence.
     * @return true if the file exists, false otherwise.
     */
    public static boolean fileExists(File file) {
        return file != null && file.exists() && file.isFile();
    }


    /**
     * Copy a file from the source location to the destination location using buffered streams.
     *
     * @param sourceFile      The file to be copied.
     * @param destinationFile The destination where the file should be copied to.
     * @return true if the file was successfully copied, false otherwise.
     */
    public static boolean copyFile(File sourceFile, File destinationFile) {
        return copyFile(sourceFile, destinationFile, true);
    }

    /**
     * Copy data from an InputStream to an OutputStream.
     *
     * @param inputStream  The InputStream to read from.
     * @param outputStream The OutputStream to write to.
     * @return true if the data was successfully copied, false otherwise.
     */
    public static boolean copyStream(InputStream inputStream, OutputStream outputStream) {
        try {
            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Copy a file from the source location to the destination location using buffered streams.
     * Allows specifying whether to overwrite an existing destination file.
     *
     * @param sourceFile      The file to be copied.
     * @param destinationFile The destination where the file should be copied to.
     * @param overwrite       true to overwrite an existing destination file, false otherwise.
     * @return true if the file was successfully copied, false otherwise.
     */
    public static boolean copyFile(File sourceFile, File destinationFile, boolean overwrite) {
        if (!overwrite && destinationFile.exists()) {
            return false; // Do not overwrite existing file
        }

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(sourceFile));
             OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destinationFile))) {

            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    //====================================== 01 Jan 2024 released ==========================================================================

    public static String generateNewFileName(String originalFileName, String newFileName) {
        return newFileName + getFileExtension(originalFileName);
    }

    public static String generateNewFileName(File file, String newFileName) {
        return generateNewFileName(file.getName(), newFileName);
    }

    public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        var fileName = Objects.requireNonNull(multipartFile.getOriginalFilename());
        File file = new File(fileName);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            throw new IOException("Error converting MultipartFile to File", e);
        }

        return file;
    }

}
