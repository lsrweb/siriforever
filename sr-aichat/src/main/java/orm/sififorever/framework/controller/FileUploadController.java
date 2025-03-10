package orm.sififorever.framework.controller;

import com.siriforever.common.annotaion.ApiRestController;
import com.siriforever.common.config.GlobalConfig;
import com.siriforever.common.core.AjaxResult;
import com.siriforever.common.utils.file.FileTypeUtils;
import com.siriforever.common.utils.file.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApiRestController
public class FileUploadController {

    private static final Set<String> ALLOWED_EXTENSIONS = new HashSet<>(Arrays.asList(
            "pdf", "xlsx", "md", "docx", "doc", "txt"));

    @Autowired
    private GlobalConfig globalConfig;

    @Autowired
    private FileUploadUtils fileUploadUtils;

    @PostMapping("/upload")
    public AjaxResult<Object> uploadFile(@RequestParam(value = "file", required = false) MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return AjaxResult.failed("请选择要上传的文件");
        }

        String fileName = file.getOriginalFilename();

        if (!ALLOWED_EXTENSIONS.contains(FileTypeUtils.getFileType(fileName))) {
            return AjaxResult.failed("不支持的文件类型，只允许上传 pdf, xlsx, md, docx, doc, txt 文件");
        }

        try {
            // 生成新文件名
            String newFileName = fileUploadUtils.generateNewFileName(fileName);
            // 生成以日期分割的文件路径
            String datePath = fileUploadUtils.generateDatePath();
            // 上传文件
            String uploadFileName = fileUploadUtils.upload(datePath, file);
            String fileUrl = fileUploadUtils.getFileUrl(uploadFileName);

            return AjaxResult.success("文件上传成功")
                    .put("url", fileUrl)
                    .put("fileName", uploadFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.failed("文件上传失败：" + e.getMessage());
        }
    }

    @PostMapping("/batchUpload")
    public AjaxResult<Object> batchUploadFiles(@RequestParam("files") List<MultipartFile> files) {
        if (files == null || files.isEmpty()) {
            return AjaxResult.failed("请选择要上传的文件");
        }

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; // 忽略空文件
            }

            String fileName = file.getOriginalFilename();

            if (!ALLOWED_EXTENSIONS.contains(FileTypeUtils.getFileType(fileName))) {
                return AjaxResult.failed("不支持的文件类型，只允许上传 pdf, xlsx, md, docx, doc, txt 文件");
            }

            try {
                // 生成新文件名
                String newFileName = fileUploadUtils.generateNewFileName(fileName);
                // 生成以日期分割的文件路径
                String datePath = fileUploadUtils.generateDatePath();
                // 上传文件
                String uploadFileName = fileUploadUtils.upload(datePath, file);
                String fileUrl = fileUploadUtils.getFileUrl(uploadFileName);

                System.out.println("上传文件: " + fileName);
                System.out.println("Upload path: " + globalConfig.getUpload());
                System.out.println("保存的文件名: " + uploadFileName);
                System.out.println("文件访问 URL: " + fileUrl);
            } catch (IOException e) {
                e.printStackTrace();
                return AjaxResult.failed("文件上传失败");
            }
        }

        return AjaxResult.success("文件上传成功");
    }
}
