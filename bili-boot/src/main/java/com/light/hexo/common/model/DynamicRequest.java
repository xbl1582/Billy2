package com.light.hexo.common.model;

import com.light.hexo.business.admin.model.Dynamic;
import com.light.hexo.common.base.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: DynamicRequest
 * @ProjectName hexo-boot
 * @Description: 动态请求对象
 */
@Setter
@Getter
public class DynamicRequest extends BaseRequest<Dynamic> {

    @NotNull(message = "主键不能为空", groups = {Update.class})
    private Integer id;

    /**
     * 内容
     */
    @NotEmpty(message = "动态内容不能为空", groups = {Save.class, Update.class})
    private String content;

    /**
     * 颜色
     */
    private String color;

    /**
     * 点赞数
     */
    private Integer praiseNum;


    public interface Send {}
}
