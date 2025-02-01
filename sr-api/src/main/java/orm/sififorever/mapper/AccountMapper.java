package orm.sififorever.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import orm.sififorever.entity.UserEntity;

import java.util.List;

@Repository
public interface AccountMapper {

    // 查询所有用户
    List<UserEntity> selectAllUser();
}
