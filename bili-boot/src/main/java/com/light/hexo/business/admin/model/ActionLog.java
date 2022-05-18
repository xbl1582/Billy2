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
 * @ClassName: ActionLog
 * @ProjectName hexo-boot
 * @Description: 操作日志
 */
@Setter
@Getter
@Accessors(chain = true)
@ToString
@Table(name = "t_action_log")
public class ActionLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * ip 地址
     */
    private String ipAddress;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 日志说明
     */
    private String remark;

    /**
     * 操作类型，参考 ActionEnum
     */
    private Integer actionType;

    /**
     * 创建时间
     */
    @CreateTime
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @UpdateTime
    private LocalDateTime updateTime;

    @Transient
    private ActionLogDetail actionLogDetail;
}
