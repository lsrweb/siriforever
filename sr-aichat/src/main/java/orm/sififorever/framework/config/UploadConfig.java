package orm.sififorever.framework.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import jakarta.servlet.MultipartConfigElement;

@Configuration
public class UploadConfig {

    private static final String UPLOAD_PATH = "/tmp/upload";
    private static final DataSize MAX_FILE_SIZE = DataSize.ofMegabytes(5);
    private static final DataSize MAX_REQUEST_SIZE = DataSize.ofMegabytes(10);

    @Bean
    public MultipartConfigElement getMultipartConfig() {
        MultipartConfigFactory config = new MultipartConfigFactory();
        config.setMaxFileSize(MAX_FILE_SIZE); // 设置单个文件大小限制
        config.setMaxRequestSize(MAX_REQUEST_SIZE); // 设置总上传数据大小
        config.setLocation("/"); // 设置临时保存目录
        return config.createMultipartConfig(); // 创建一个上传配置
    }
}
