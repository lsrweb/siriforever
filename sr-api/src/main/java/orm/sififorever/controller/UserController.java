package orm.sififorever.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siriforever.common.core.AjaxResult;
import orm.sififorever.entity.UserEntity;
import orm.sififorever.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public AjaxResult<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.findAllUsers();
        return AjaxResult.success(users);
    }
}
