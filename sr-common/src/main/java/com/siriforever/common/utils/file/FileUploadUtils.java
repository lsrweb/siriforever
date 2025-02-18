package com.siriforever.common.utils.file;

import com.siriforever.common.config.GlobalConfig;
import com.siriforever.common.utils.DateUtils;
import com.siriforever.common.utils.StringUtils;
import com.siriforever.common.utils.uuid.Seq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class FileUploadUtils {

    @Autowired
    private GlobalConfig globalConfig;

    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public String upload(MultipartFile file) throws IOException {
        return upload(null, file, globalConfig.getType().split(","));
    }

    /**
     * 使用指定路径进行文件上传
     *
     * @param baseDir 相对应用的基目录
     * @param file    上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public String upload(String baseDir, MultipartFile file) throws IOException {
        return upload(baseDir, file, globalConfig.getType().split(","));
    }

    /**
     * 文件上传
     *
     * @param baseDir          相对项目的根目录
     * @param file             上传的文件
     * @param allowedExtension 允许上传的文件类型
     * @return 返回上传成功的文件名
     * @throws IOException 如果baseDir为空 或 文件类型不全
     */
    public String upload(String baseDir, MultipartFile file, String[] allowedExtension) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IOException("上传的文件不能为空");
        }
        String filename = StringUtils.replaceBlank(file.getOriginalFilename()); // 获取文件名
        String extension = FileTypeUtils.getFileType(file.getOriginalFilename()); // 获取文件后缀
        if (allowedExtension != null && !FileTypeUtils.isAllowedExtension(extension, allowedExtension)) {
            throw new IOException("文件类型不正确，只允许上传 " + String.join(",", allowedExtension) + " 等类型");
        }
        String newFileName = extractFilename(file); // 生成新文件名
        File desc = getAbsoluteFile(baseDir, newFileName); // 获取文件绝对路径
        file.transferTo(desc); // 保存文件
        return newFileName;
    }

    private File getAbsoluteFile(String baseDir, String fileName) {
        // 获取项目根目录
        String projectRoot = System.getProperty("user.dir");
        // 始终使用项目根目录 + static/upload 作为基础路径
        String uploadPath = projectRoot + File.separator + "static" + File.separator + "upload";

        // 创建完整的文件路径
        File desc = new File(uploadPath + File.separator + fileName);

        // 输出实际的文件路径，用于调试
        System.out.println("项目根目录: " + projectRoot);
        System.out.println("文件实际保存路径: " + desc.getAbsolutePath());

        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        return desc;
    }

    /**
     * 处理文件名
     */
    private String extractFilename(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        // 清理文件名中的特殊字符
        String baseName = FileTypeUtils.getBaseName(originalFilename)
                .replaceAll("[\\s\\\\/:*?\"<>|]", "_") // 替换Windows文件系统不允许的字符
                .replaceAll("[^a-zA-Z0-9\\u4e00-\\u9fa5_.-]", "_") // 只保留字母数字中文和常用符号
                .replaceAll("_{2,}", "_"); // 将多个连续下划线替换为单个

        return StringUtils.format("{}/{}_{}.{}",
                DateUtils.datePath(),
                baseName,
                Seq.getId(Seq.uploadSeqType),
                FileTypeUtils.getExtension(file));
    }

    /**
     * 获取文件访问 URL
     *
     * @param filename 文件名
     * @return 文件 URL
     */
    public String getFileUrl(String filename) {
        return "http://" + globalConfig.getDomain() + ":" + globalConfig.getPort() +
                "/static/upload/" + filename.replace(File.separatorChar, '/');
    }

    /**
     * 生成以日期分割的文件路径
     *
     * @return 生成的文件路径
     */
    public String generateDatePath() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 使用UUID生成新文件名
     *
     * @param originalFilename 原始文件名
     * @return 新文件名
     */
    public String generateNewFileName(String originalFilename) {
        return UUID.randomUUID().toString().replaceAll("-", "")
                + originalFilename.substring(originalFilename.lastIndexOf("."));
    }
}
