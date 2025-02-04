package orm.sififorever.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.siriforever.common.annotaion.ApiRestController;
import com.siriforever.common.core.AjaxResult;
import orm.sififorever.entity.UserEntity;
import orm.sififorever.service.UserService;

import java.util.List;

@ApiRestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/all")
    public AjaxResult<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.findAllUsers();
        return AjaxResult.success(users);
    }
}
