package com.siriforever.common.utils.file;

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

}
