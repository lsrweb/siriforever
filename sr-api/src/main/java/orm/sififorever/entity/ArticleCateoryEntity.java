package orm.sififorever.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ArticleCateoryEntity {
    private Integer id;
    private String categoryName;
    private String categoryAlias;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private UserEntity user;

}
