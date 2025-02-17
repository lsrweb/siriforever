package com.siriforever.common.utils.file;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
public class FileUploadUtils {
    // 默认大小 50M
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;
    // 默认文件名最大长度 100
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;
    // 默认上传地址

    @Value("${file.upload}")
    public static String defaultBaseDir;

}
