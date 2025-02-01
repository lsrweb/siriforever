package orm.sififorever.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orm.sififorever.entity.UserEntity;
import orm.sififorever.mapper.AccountMapper;
import orm.sififorever.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountMapper accountMapper;

    // 返回查询所有用户
    @Override
    public List<UserEntity> selectAllUser() {
        return accountMapper.selectAllUser();
    }

}
