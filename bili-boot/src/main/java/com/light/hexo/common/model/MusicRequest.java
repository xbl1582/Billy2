package com.light.hexo.common.model;

import com.light.hexo.business.admin.model.Music;
import com.light.hexo.common.base.BaseRequest;
import com.light.hexo.common.component.valid.NumberValidator;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: MusicRequest
 * @ProjectName hexo-boot
 * @Description: 音乐请求对象
 */
@Setter
@Getter
public class MusicRequest extends BaseRequest<Music> {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = {Update.class})
    private Integer id;

    /**
     * 名称
     */
    @NotEmpty(message = "名称不能为空", groups = {Save.class, Update.class})
    private String name;

    /**
     * 作者
     */
    @NotEmpty(message = "作者不能为空", groups = {Save.class, Update.class})
    private String artist;

    /**
     * 链接
     */
    @NotEmpty(message = "链接不能为空", groups = {Save.class, Update.class})
    private String url;

    /**
     * 封面
     */
    @NotEmpty(message = "封面不能为空", groups = {Save.class, Update.class})
    private String cover;

    /**
     * 颜色（十六进制）
     */
    private String color;

    /**
     * 可用状态
     */
    private Boolean state;

    /**
     * 排序
     */
    @NumberValidator(message= "排序必须为数字", groups = {Save.class, Update.class})
    private String sort;
}
