package orm.sififorever.entity;

import lombok.Data;

@Data
public class ArticleEntity {
    private Integer id;
    private String title;
    private String content;
    private String coverImg;
    private String state;
    private Integer categoryId;
    private Integer createUser;
    private String createTime;
    private String updateTime;

    private ArticleCateoryEntity category;
    private UserEntity user;

}
