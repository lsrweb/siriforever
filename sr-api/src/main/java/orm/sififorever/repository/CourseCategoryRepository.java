package orm.sififorever.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import orm.sififorever.entity.CourseCategoryEntity;

@Repository
public interface CourseCategoryRepository extends JpaRepository<CourseCategoryEntity, Long> {
    // 可以添加自定义查询方法
}
