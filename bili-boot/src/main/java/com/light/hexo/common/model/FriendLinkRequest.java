package com.light.hexo.common.model;

import com.light.hexo.business.admin.model.FriendLink;
import com.light.hexo.common.base.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: FriendLinkRequest
 * @ProjectName hexo-boot
 * @Description: 友链请求对象
 */
@Setter
@Getter
public class FriendLinkRequest extends BaseRequest<FriendLink> {

    @NotNull(message = "主键不能为空", groups = {Update.class})
    private Long id;

    /**
     * 标题
     */
    @NotEmpty(message = "标题不能为空", groups = {Save.class, Update.class})
    private String title;


    /**
     * Logo
     */
    private String logo;

    /**
     * 主页
     */
    @NotEmpty(message = "主页不能为空", groups = {Save.class, Update.class})
    private String homeUrl;

    /**
     * 作者
     */
    private String author;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 类型 1：博主主页 2：常用网址
     */
    private Integer linkType;

    /**
     * 背景颜色
     */
    private String backgroundColor;

    /**
     * 排序
     */
    private Integer sort = 1;

    /**
     * 备注
     */
    private String remark;
}
