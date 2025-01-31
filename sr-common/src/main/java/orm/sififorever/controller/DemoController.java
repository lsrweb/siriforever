package orm.sififorever.controller;

import java.util.Map;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/hello")
    public String hello() {
        // 返回json数据
        // Map<String, Object> map = Map.of("name", "siriforever", "age", 18);
        // return map.toString();

        // 返回对象
//        User user = new User("siriforever", 18);
//        return user.toString();
        return "hello";
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class User {
        String name;
        int age;
    }

}
