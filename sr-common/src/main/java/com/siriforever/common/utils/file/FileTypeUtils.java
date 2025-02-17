package com.siriforever.common.utils.file;

import org.springframework.web.multipart.MultipartFile;

public class FileTypeUtils {
    // 获取文件类型
    public static String getFileType(String fileName) {
        String fileExtension = "";
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            fileExtension = fileName.substring(dotIndex + 1).toLowerCase();
        }
        return fileExtension;
    }

    public static boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String s : allowedExtension) {
            if (s.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    // getBaseName 获取文件名
    public static String getBaseName(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1 ? fileName : fileName.substring(0, dotIndex));
    }

    // 获取文件后缀
    public static String getExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1 ? "" : fileName.substring(dotIndex + 1));
    }

}
