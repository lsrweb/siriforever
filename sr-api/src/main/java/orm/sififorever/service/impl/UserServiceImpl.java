package orm.sififorever.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import orm.sififorever.entity.UserEntity;
import orm.sififorever.repository.UserRepository;
import orm.sififorever.service.UserService;
import orm.sififorever.exception.UserException;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserEntity register(UserEntity user) {
        // 检查用户名是否已存在
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserException("用户名已存在");
        }

        // 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

        return userRepository.save(user);
    }

    @Override
    public UserEntity login(String username, String password) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserException("用户不存在");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserException("密码错误");
        }

        return user;
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException("用户不存在"));
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserEntity updateUser(UserEntity user) {
        UserEntity existingUser = findById(user.getId());

        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setBio(user.getBio());
        existingUser.setUpdatedAt(new Date());

        return userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        UserEntity user = findById(userId);

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new UserException("原密码错误");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdatedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }
}
