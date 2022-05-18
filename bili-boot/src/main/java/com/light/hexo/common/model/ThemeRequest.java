package com.light.hexo.common.model;

import com.light.hexo.business.admin.model.Theme;
import com.light.hexo.common.base.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: ThemeRequest
 * @ProjectName hexo-boot
 * @Description: 主题请求对象
 */
@Setter
@Getter
public class ThemeRequest extends BaseRequest<Theme> {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = {Update.class})
    private Integer id;

    /**
     * 主题名称
     */
    private String name;

    /**
     * 状态
     */
    private Boolean state;
}
