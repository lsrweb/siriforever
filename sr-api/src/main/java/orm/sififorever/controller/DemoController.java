package orm.sififorever.controller;

import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siriforever.common.annotaion.ApiRestController;

import orm.sififorever.entity.UserEntity;
import orm.sififorever.mapper.AccountMapper;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "demo", description = "demo controller")
@Validated
@RestController
@ApiRestController
public class DemoController {

    @Resource
    AccountMapper accountMapper;

    @GetMapping("/hello")
    public List<UserEntity> hello() {
        return accountMapper.selectAllUser();
    }

    @GetMapping("/ok")
    public String getMethodName(@RequestParam String param) {
        return new String("ok");
    }

}
