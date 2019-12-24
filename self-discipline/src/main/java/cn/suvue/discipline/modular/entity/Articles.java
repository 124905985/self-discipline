package cn.suvue.discipline.modular.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 博文表
 * </p>
 *
 * @author suvue
 * @since 2019-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sd_articles")
public class Articles implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 博文ID
     */
    @TableId(value = "article_id", type = IdType.AUTO)
    private Long articleId;

    /**
     * 发表用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 博文标题
     */
    @TableField(value = "article_title")
    private String articleTitle;

    /**
     * 博文内容
     */
    @TableField(value = "article_content")
    private String articleContent;

    /**
     * 浏览量
     */
    @TableField(value = "article_views")
    private Long articleViews;

    /**
     * 评论总数
     */
    @TableField(value = "article_comment_count")
    private Long articleCommentCount;

    /**
     * 发表时间
     */
    @TableField(value = "article_date")
    private LocalDateTime articleDate;

    /**
     * 点赞数
     */
    @TableField(value = "article_like_count")
    private Long articleLikeCount;


}
