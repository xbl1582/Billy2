package com.light.hexo.common.model;

import com.light.hexo.business.admin.model.Tag;
import com.light.hexo.common.base.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: TagRequest
 * @ProjectName hexo-boot
 * @Description: 标签请求对象
 */
@Setter
@Getter
public class TagRequest extends BaseRequest<Tag> {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = {Update.class})
    private Integer id;

    /**
     * 标签名
     */
    @NotEmpty(message = "标签名称不能为空", groups = {Update.class})
    private String name;
}
