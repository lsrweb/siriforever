package orm.sififorever.service;

import org.springframework.stereotype.Service;
import orm.sififorever.entity.UserEntity;

@Service
public interface UserService {
    // 用户注册
    UserEntity register(UserEntity user);

    // 用户登录
    UserEntity login(String username, String password);

    // 根据ID查询用户
    UserEntity findById(Long id);

    // 根据用户名查询用户
    UserEntity findByUsername(String username);

    // 更新用户信息
    UserEntity updateUser(UserEntity user);

    // 删除用户
    void deleteUser(Long id);

    // 修改密码
    void changePassword(Long userId, String oldPassword, String newPassword);
}
