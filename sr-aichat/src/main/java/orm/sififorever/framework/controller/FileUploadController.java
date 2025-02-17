package orm.sififorever.framework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.siriforever.common.annotaion.ApiRestController;
import com.siriforever.common.core.AjaxResult;
import com.siriforever.common.utils.file.FileTypeUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApiRestController
public class FileUploadController {

    private static final Set<String> ALLOWED_EXTENSIONS = new HashSet<>(Arrays.asList(
            "pdf", "xlsx", "md", "docx", "doc", "txt"));

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
            // 处理文件上传逻辑
            System.out.println("上传文件: " + fileName);

            return AjaxResult.success("文件上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.failed("文件上传失败");
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

            if (!ALLOWED_EXTENSIONS.contains(
                    FileTypeUtils.getFileType(fileName))) {
                return AjaxResult.failed("不支持的文件类型，只允许上传 pdf, xlsx, md, docx, doc, txt 文件");
            }

            try {
                // 在这里处理文件上传逻辑，例如保存到服务器或云存储
                // file.getBytes() 获取文件内容
                // 可以使用 file.transferTo(new File("path/to/save/" + fileName)); 保存文件
                System.out.println("上传文件: " + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return AjaxResult.failed("文件上传失败");
            }
        }

        return AjaxResult.success("文件上传成功");
    }
}
