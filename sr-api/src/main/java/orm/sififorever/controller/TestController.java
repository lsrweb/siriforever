package orm.sififorever.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.siriforever.common.annotaion.ApiRestController;
import com.siriforever.common.exception.AppRuntimeException;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import orm.sififorever.validator.UserRequest;

@ApiRestController
public class TestController {
    @GetMapping("/test-image")
    public String testImage() {
        // 返回一个包含图片的HTML页面
        return "<img src='/api/images/test.jpg' />";
    }

    @GetMapping("/test-error")
    public String test() {
        throw new AppRuntimeException("TEST_ERROR", "测试异常", "Runtime");
    }

    @GetMapping("/test-validate")
    public String createUser(@RequestBody @Valid UserRequest user) {

        return "success";
    }

}
