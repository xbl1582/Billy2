package com.light.hexo.common.model;

import com.light.hexo.business.admin.model.Category;
import com.light.hexo.common.base.BaseRequest;
import com.light.hexo.common.component.valid.NumberValidator;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**

 * @ClassName: CategoryRequest
 * @ProjectName hexo-boot
 * @Description: 分类请求对象
 */
@Setter
@Getter
public class CategoryRequest extends BaseRequest<Category> {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = {Update.class})
    private Integer id;

    /**
     * 分类名称
     */
    @NotEmpty(message = "分类名称不能为空", groups = {Save.class, Update.class})
    private String name;

    /**
     * 图片地址
     */
    private String coverUrl;

    /**
     * 可用状态
     */
    private Boolean state;

    /**
     * 排序
     */
    @NumberValidator(message= "排序必须为数字", groups = {Save.class, Update.class})
    private String sort;

    /**
     * 描述
     */
    private String remark;
}
