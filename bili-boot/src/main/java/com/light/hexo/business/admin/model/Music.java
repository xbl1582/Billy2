package com.light.hexo.business.admin.model;

import com.light.hexo.common.component.mybatis.CreateTime;
import com.light.hexo.common.component.mybatis.UpdateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName: Music
 * @ProjectName hexo-boot
 * @Description: 音乐
 */
@Setter
@Getter
@Accessors(chain = true)
@ToString
@Table(name = "t_music")
public class Music implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 作者
     */
    private String artist;

    /**
     * 链接
     */
    private String url;

    /**
     * 封面
     */
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
    private Integer sort;

    @CreateTime
    private LocalDateTime createTime;

    @UpdateTime
    private LocalDateTime updateTime;
}
