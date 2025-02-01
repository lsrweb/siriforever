package orm.sififorever.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    // 返回 resources/templates/index.html 页面
    @GetMapping("/")
    public String index() {
        return "index";
    }

}
