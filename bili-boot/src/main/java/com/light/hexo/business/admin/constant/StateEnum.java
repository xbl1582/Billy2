package com.light.hexo.business.admin.constant;

import lombok.Getter;

/**
 * @ClassName: StateEnum
 * @ProjectName hexo-boot
 * @Description: 状态枚举
 */
@Getter
public enum StateEnum {

    ON(1, "开启"),
    OFF(0, "关闭");

    private Integer code;

    private String message;

    StateEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
