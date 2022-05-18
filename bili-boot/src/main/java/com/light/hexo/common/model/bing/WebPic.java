package com.light.hexo.common.model.bing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName: BingPic
 * @Description: bing 图片
 */
@Setter
@Getter
@ToString
public class WebPic {

    private String code;

    private String imgurl;

    private String width;

    private String height;

}
