package orm.sififorever.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/test-image")
    public String testImage() {
        // 返回一个包含图片的HTML页面
        return "<img src='/api/images/test.jpg' />";
    }
}
