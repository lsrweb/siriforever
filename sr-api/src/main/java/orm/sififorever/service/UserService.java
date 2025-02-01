package orm.sififorever.service;

import org.springframework.stereotype.Service;
import orm.sififorever.entity.UserEntity;

import java.util.List;

@Service
public interface UserService {
    // 查询所有用户
    List<UserEntity> selectAllUser();

}
