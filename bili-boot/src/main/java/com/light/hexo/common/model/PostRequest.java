package com.light.hexo.common.model;

import com.light.hexo.business.admin.model.Post;
import com.light.hexo.common.base.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @ClassName: PostRequest
 * @ProjectName hexo-boot
 * @Description: 文章请求对象
 */
@Setter
@Getter
public class PostRequest extends BaseRequest<Post> {

    @NotNull(message = "主键不能为空", groups = {Update.class})
    private Integer id;

    /**
     * 文章标题
     */
    @NotEmpty(message = "文章标题不能为空", groups = {Save.class, Update.class})
    private String title;

    /**
     * 文章内容（原始数据，markdown 或 html 格式）
     */
    @NotEmpty(message = "文章内容不能为空", groups = {Save.class, Update.class})
    private String content;

    /**
     * 作者
     */
    private String author;

    /**
     * 发布日期
     */
    private String publishDate;

    /**
     * 封面图片地址
     */
    private String coverUrl;

    /**
     * 封面类型
     */
    private Integer coverType;

    /**
     * 是否发布
     */
    private Boolean publish;

    /**
     * 是否允许评论
     */
    private Boolean comment;

    /**
     * 是否置顶
     */
    private Boolean top;

    /**
     * 是否转载
     */
    private Boolean reprint;

    /**
     * 转载链接
     */
    private String reprintLink;

    /**
     * 分类
     */
    @NotNull(message = "分类不能为空", groups = {Save.class, Update.class})
    private Integer categoryId;

    /**
     * 标签，多个标签使用逗号拼接
     */
    private String tags;

    /**
     * 自定义文章链接
     */
    private String customLink;

    /**
     * 访问密码
     */
    private String authCode;

    /**
     * 定时发布时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime jobTime;
}
