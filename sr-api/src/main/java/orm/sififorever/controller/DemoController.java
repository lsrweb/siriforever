package orm.sififorever.controller;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import orm.sififorever.entity.UserEntity;
import orm.sififorever.mapper.AccountMapper;

@Tag(name = "demo", description = "demo controller")
@Validated
@RestController()
public class DemoController {

    @Resource
    AccountMapper accountMapper;

    @GetMapping("/hello")
    public List<UserEntity> hello() {
        // 返回json数据
        // Map<String, Object> map = Map.of("name", "siriforever", "age", 18);
        // return map.toString();

        // 返回对象
//        User user = new User("siriforever", 18);
//        return user.toString();
//        accountMapper.selectAllUser();
        return accountMapper.selectAllUser();
    }


}
