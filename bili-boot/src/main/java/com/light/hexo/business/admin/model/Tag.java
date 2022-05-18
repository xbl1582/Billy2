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
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName: Tag
 * @ProjectName hexo-boot
 * @Description: 标签
 */
@Setter
@Getter
@Accessors(chain = true)
@ToString
@Table(name = "t_tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 标签名称
     */
    private String name;

    public Tag() {}

    public Tag(String name) {
        this.name = name;
    }

    @CreateTime
    private LocalDateTime createTime;

    @UpdateTime
    private LocalDateTime updateTime;

    /**
     * 样式
     */
    @Transient
    private String style;

    @Transient
    private String url;
}
