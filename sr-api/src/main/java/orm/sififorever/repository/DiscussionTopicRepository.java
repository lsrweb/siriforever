package orm.sififorever.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import orm.sififorever.entity.DiscussionTopicEntity;

@Repository
public interface DiscussionTopicRepository extends JpaRepository<DiscussionTopicEntity, Long> {
    // 可以添加自定义查询方法
}
