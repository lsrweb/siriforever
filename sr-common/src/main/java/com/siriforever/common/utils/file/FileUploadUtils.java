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
     * @param baseDir          相对应用的基目录
     * @param file             上传的文件
     * @param allowedExtension 允许上传的文件类型
     * @return 返回上传成功的文件名
     * @throws IOException 如果baseDir为空 或 文件类型不全
     */
    public String upload(String baseDir, MultipartFile file, String[] allowedExtension) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IOException("上传的文件不能为空");
        }
        String filename = file.getOriginalFilename();
        String extension = FileTypeUtils.getFileType(file.getOriginalFilename());
        if (allowedExtension != null && !FileTypeUtils.isAllowedExtension(extension, allowedExtension)) {
            throw new IOException("文件类型不正确，只允许上传 " + String.join(",", allowedExtension) + " 等类型");
        }

        String fileName = extractFilename(file);

        File desc = getAbsoluteFile(baseDir, fileName);
        try {
            file.transferTo(desc);
        } catch (IOException e) {
            throw new IOException("文件上传失败", e);
        }
        return fileName;
    }

    /**
     * 提取上传的文件名
     *
     * @param file 上传的文件
     * @return 如果名称为null 采用uuid作为文件名
     */
    private String extractFilename(MultipartFile file) {
        return StringUtils.format("{}/{}_{}.{}", DateUtils.datePath(),
                FileTypeUtils.getBaseName(file.getOriginalFilename()), Seq.getId(Seq.uploadSeqType),
                FileTypeUtils.getExtension(file));
    }

    private File getAbsoluteFile(String baseDir, String filename) throws IOException {
        String uploadPath;
        if (baseDir == null) {
            uploadPath = globalConfig.getUpload();
        } else {
            uploadPath = globalConfig.getUpload() + File.separator + baseDir;
        }
        File desc = new File(uploadPath + File.separator + filename);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * 获取文件访问 URL
     *
     * @param filename 文件名
     * @return 文件 URL
     */
    public String getFileUrl(String filename) {
        return "http://" + globalConfig.getDomain() + ":" + globalConfig.getPort() + "/static/upload/" + filename;
    }
}
