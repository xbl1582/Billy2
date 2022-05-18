package com.light.hexo.common.component.file;

import lombok.Getter;

/**
 * @ClassName: FileManageEnum
 * @ProjectName hexo-boot
 * @Description: 文件管理模式枚举
 */
@Getter
public enum FileManageEnum {

    LOCAL(1, "本地"),
    QI_NIU(2, "阿里云"),
    OSS(3, "OSS"),
    COS(4, "腾讯云")
    ;

    private int code;

    private String message;

    FileManageEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessageByCode(int code) {
        for (FileManageEnum modeEnum : FileManageEnum.values()) {
            if (modeEnum.getCode() == code) {
                return modeEnum.getMessage();
            }
        }
        return "";
    }
}
