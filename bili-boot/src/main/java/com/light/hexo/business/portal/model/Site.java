package com.light.hexo.business.portal.model;

import com.light.hexo.business.admin.model.Category;
import com.light.hexo.business.admin.model.Post;
import com.light.hexo.business.admin.model.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: Site
 * @ProjectName hexo-boot
 * @Description: 站点
 */
@Setter
@Getter
@ToString
public class Site implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章列表
     */
    private List<Post> posts;

    /**
     * 所有分类
     */
    private List<Category> categories;

    /**
     * 所有标签
     */
    private List<Tag> tags;

    /**
     * 所有页面
     */
    private List<Page> pages;
}