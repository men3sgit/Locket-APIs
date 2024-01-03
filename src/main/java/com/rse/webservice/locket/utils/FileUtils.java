package com.rse.webservice.locket.utils;

import com.rse.webservice.locket.exception.ApiRequestException;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * Utility class for handling file-related operations.
 *
 * @author MENES
 * @version 0.0.1
 * @since 01 Jan 2024
 */
public class FileUtils {

    /**
     * Interface for representing file size units and a constant for indicating files that are too large to display.
     */
    public interface Unit {
        String BYTES = "B";
        String KILOBYTES = "KB";
        String MEGABYTES = "MB";
        String GIGABYTES = "GB";
        String TOO_LARGE = "Too large to display";
    }

    /**
     * Interface for defining file types.
     */
    public interface Type {
        String TEXT = "Text";
        String DOCUMENT = "Document";
        String SPREADSHEET = "Spreadsheet";
        String IMAGE = "Image";
        String AUDIO = "Audio";
        String VIDEO = "Video";
        String EXECUTABLE = "Executable";
        String ARCHIVE = "Archive";
        String DATABASE = "Database";
        String PROGRAMMING = "Programming";
        String CONFIGURATION = "Configuration";
    }

    /**
     * Interface for file extensions.
     */
    public interface Extension {
        // Text Files
        String TXT = ".txt";
        String CSV = ".csv";
        String XML = ".xml";
        String JSON = ".json";

        // Document Files
        String DOC = ".doc";
        String DOCX = ".docx";
        String PDF = ".pdf";

        // Spreadsheet Files
        String XLS = ".xls";
        String XLSX = ".xlsx";
        String ODS = ".ods";

        // Image Files
        String JPG = ".jpg";
        String JPEG = ".jpeg";
        String PNG = ".png";
        String GIF = ".gif";
        String BMP = ".bmp";
        String SVG = ".svg";

        // Audio Files
        String MP3 = ".mp3";
        String WAV = ".wav";
        String FLAC = ".flac";

        // Video Files
        String MP4 = ".mp4";
        String AVI = ".avi";
        String MKV = ".mkv";

        // Executable Files
        String EXE = ".exe";
        String APP = ".app";
        String SH = ".sh";

        // Archive Files
        String ZIP = ".zip";
        String TAR = ".tar";
        String GZ = ".gz";
        String TAR_GZ = ".tar.gz";

        // Database Files
        String DB = ".db";
        String SQLITE = ".sqlite";
        String SQL = ".sql";

        // Programming Files
        String JAVA = ".java";
        String PY = ".py";
        String HTML = ".html";
        String CSS = ".css";
        String JS = ".js";

        // Configuration Files
        String INI = ".ini";
        String PROPERTIES = ".properties";
        String YAML = ".yaml";
        String YML = ".yml";
    }

    /**
     * Enum representing file types along with their associated extensions.
     */
    public enum FileType {
        // Text Files
        TEXT(".txt", ".csv", ".xml", ".json"),

        // Document Files
        DOCUMENT(".doc", ".docx", ".pdf"),

        // Spreadsheet Files
        SPREADSHEET(".xls", ".xlsx", ".ods"),

        // Image Files
        IMAGE(".jpg", ".jpeg", ".png", ".gif", ".bmp", ".svg"),

        // Audio Files
        AUDIO(".mp3", ".wav", ".flac"),

        // Video Files
        VIDEO(".mp4", ".avi", ".mkv"),

        // Executable Files
        EXECUTABLE(".exe", ".app", ".sh"),

        // Archive Files
        ARCHIVE(".zip", ".tar", ".gz", ".tar.gz"),

        // Database Files
        DATABASE(".db", ".sqlite", ".sql"),

        // Programming Files
        PROGRAMMING(".java", ".py", ".html", ".css", ".js"),

        // Configuration Files
        CONFIGURATION(".ini", ".yaml", ".yml"),

        // Default
        UNKNOWN("default");

        private final String[] extensions;

        /**
         * Constructor for FileType enum, initializing file extensions.
         *
         * @param extensions The extensions associated with the file type.
         */
        FileType(String... extensions) {
            this.extensions = extensions;
        }

        /**
         * Get the type of file based on its extension.
         *
         * @param fileName The name of the file.
         * @return The type of the file.
         */
        public static String getType(String fileName) {
            String fileExtension = "." + FileUtils.getFileExtension(fileName);
            String lowerCaseExtension = fileExtension.toLowerCase();
            for (FileType fileType : values()) {
                if (Arrays.asList(fileType.extensions).contains(lowerCaseExtension)) {
                    return fileType.name();
                }
            }
            return UNKNOWN.name();
        }

        /**
         * Get the type of file based on its extension.
         *
         * @param file The file.
         * @return The type of the file.
         */
        public static String getType(File file) {
            return getType(FileUtils.getFileExtension(file));
        }
    }

    /**
     * Get the file extension of a given file name.
     *
     * @param fileName The file name.
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

    /**
     * Get the file extension of a given file.
     *
     * @param file The file.
     * @return The file extension or an empty string if the file has no extension.
     */
    public static String getFileExtension(File file) {
        if (file == null || !file.isFile()) {
            return "";
        }
        return getFileExtension(file.getName());
    }

    /**
     * Get the size of a file in different units (KB, MB, GB).
     *
     * @param file The file.
     * @return A formatted string with the file size in KB, MB, or GB.
     * Returns an empty string if the file doesn't exist.
     */
    public static String getFileSizeFormatted(File file) {
        long fileSize = getFileSize(file);

        if (fileSize == -1) {
            return ""; // File doesn't exist
        }

        return generateFileSizeString(fileSize);
    }

    /**
     * Get the formatted file size string.
     *
     * @param fileSize The file size.
     * @return The formatted file size string.
     */
    public static String getFileSizeFormatted(Long fileSize) {
        return generateFileSizeString(fileSize);
    }

    private static String generateFileSizeString(Long fileSize) {
        int sizeUnit = (int) (Math.log(fileSize) / Math.log(1024));
        return switch (sizeUnit) {
            case 0 -> fileSize + Unit.BYTES;
            case 1 -> String.format("%.2f %s", fileSize / 1024.0, Unit.KILOBYTES);
            case 2 -> String.format("%.2f %s", fileSize / (1024.0 * 1024), Unit.MEGABYTES);
            case 3 -> String.format("%.2f %s", fileSize / (1024.0 * 1024 * 1024), Unit.GIGABYTES);
            default -> Unit.TOO_LARGE; // Handle extremely large files
        };
    }

    /**
     * Get the size of a file in bytes.
     *
     * @param file The file.
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
     * @param file The file.
     * @return true if the file exists, false otherwise.
     */
    public static boolean fileExists(File file) {
        return file != null && file.exists() && file.isFile();
    }

    /**
     * Copy a file from the source location to the destination location using buffered streams.
     *
     * @param sourceFile      The source file.
     * @param destinationFile The destination file.
     * @return true if the file was successfully copied, false otherwise.
     */
    public static boolean copyFile(File sourceFile, File destinationFile) {
        return copyFile(sourceFile, destinationFile, true);
    }

    /**
     * Copy data from an InputStream to an OutputStream.
     *
     * @param inputStream  The InputStream.
     * @param outputStream The OutputStream.
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
     * @param sourceFile      The source file.
     * @param destinationFile The destination file.
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

    /**
     * Generate a new file name based on the original file name and a specified new file name.
     *
     * @param originalFileName The original file name.
     * @param newFileName      The new file name.
     * @return The generated new file name.
     */
    public static String generateNewFileName(String originalFileName, String newFileName) {
        return remoteExtension(newFileName) + "." + getFileExtension(originalFileName);
    }

    private static String remoteExtension(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }

    /**
     * Generate a new file name based on an original file and a specified new file name.
     *
     * @param file        The original file.
     * @param newFileName The new file name.
     * @return The generated new file name.
     */
    public static String generateNewFileName(File file, String newFileName) {
        return generateNewFileName(file.getName(), newFileName);
    }

    /**
     * Convert a Spring MultipartFile to a regular File.
     *
     * @param multipartFile The MultipartFile to be converted.
     * @return The converted File.
     * @throws ApiRequestException If an error occurs during the conversion.
     */
    public static File convertMultipartFileToFile(MultipartFile multipartFile) {
        var fileName = Objects.requireNonNull(multipartFile.getOriginalFilename());
        File file = new File(fileName);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            throw new ApiRequestException("Error converting MultipartFile to File");
        }

        return file;
    }

    //====================================== 02 Jan 2024 released ==========================================================================

}
