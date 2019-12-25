package cn.suvue.discipline.modular.model.result;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 博文表参数封装
 *
 * @author suvue
 * @date 2019/12/24 22:10
 */
@Data
public class ArticlesResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 博文ID
     */
    private Long articleId;

    /**
     * 发表用户ID
     */
    private Long userId;

    /**
     * 博文标题
     */
    private String articleTitle;

    /**
     * 博文内容
     */
    private String articleContent;

    /**
     * 浏览量
     */
    private Long articleViews;

    /**
     * 评论总数
     */
    private Long articleCommentCount;

    /**
     * 发表时间
     */
    private LocalDateTime articleDate;

    /**
     * 点赞数
     */
    private Long articleLikeCount;

    /**
     * 博文状态
     */
    private String articleStatus;

    /**
     * 博文排序
     */
    private Integer articleSort;


}
