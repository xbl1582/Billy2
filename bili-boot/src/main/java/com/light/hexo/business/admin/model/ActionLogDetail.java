package com.light.hexo.business.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName: ActionLogDetail
 * @ProjectName hexo-boot
 * @Description: 操作日志详情
 */
@Setter
@Getter
@Accessors(chain = true)
@ToString
@Table(name = "t_action_log_detail")
public class ActionLogDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 日志 id
     */
    private Integer logId;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 方法参数
     */
    private String methodParam;
}
