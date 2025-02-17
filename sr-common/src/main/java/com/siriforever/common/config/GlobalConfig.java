package com.siriforever.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "file")
public class GlobalConfig {
    // 上传文件路径
    private String upload;
    // 临时文件路径
    private String temp;
    // 文件大小限制
    private int maxSize;
    // 文件类型限制
    private String type;
    // 服务器域名
    private String domain;
    // 服务器端口
    private int port;
}