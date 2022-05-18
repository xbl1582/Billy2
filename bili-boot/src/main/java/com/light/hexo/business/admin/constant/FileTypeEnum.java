package com.light.hexo.business.admin.constant;

import lombok.Getter;

/**
 * @ClassName: FileTypeEnum
 * @ProjectName: hexo-boot
 * @Description: 文件类型枚举
 */
@Getter
public enum FileTypeEnum {

    IMAGE(1, "image", "图片"),
    VIDEO(2, "video", "视频"),
    OTHER(3, "other", "其他");

    private Integer type;

    private String code;

    private String message;

    FileTypeEnum(int type, String code, String message) {
        this.type = type;
        this.code = code;
        this.message = message;
    }
}
