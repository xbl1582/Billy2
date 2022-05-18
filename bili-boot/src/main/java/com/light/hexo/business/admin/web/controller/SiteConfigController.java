package com.light.hexo.business.admin.web.controller;

import com.light.hexo.business.admin.service.ConfigService;
import com.light.hexo.common.base.BaseController;
import com.light.hexo.common.component.log.ActionEnum;
import com.light.hexo.common.component.log.OperateLog;
import com.light.hexo.common.exception.GlobalException;
import com.light.hexo.common.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @ClassName: SiteConfigController
 * @ProjectName hexo-boot
 * @Description: 网站配置控制器
 */
@RequestMapping("/admin/siteConfig")
@Controller
public class SiteConfigController extends BaseController {

    @Autowired
    private ConfigService configService;

    /**
     * 基础配置页
     * @param resultMap
     * @return
     */
    @RequestMapping("basicUI.html")
    public String basicUI(Map<String, Object> resultMap) {
        Map<String, String> configMap = this.configService.getConfigMap();
        resultMap.put("configMap", configMap);
        return super.render("basicUI", resultMap);
    }

    /**
     * 个性配置页
     * @param resultMap
     * @return
     */
    @RequestMapping("specialUI.html")
    public String specialUI(Map<String, Object> resultMap) {
        Map<String, String> configMap = this.configService.getConfigMap();
        resultMap.put("configMap", configMap);
        return super.render("specialUI", resultMap);
    }

    /**
     * 保存系统配置
     * @param map
     * @return
     * @throws GlobalException
     */
    @PostMapping("/save.json")
    @ResponseBody
    @OperateLog(value = "保存系统配置", actionType = ActionEnum.ADMIN_EDIT)
    public Result save(@RequestParam Map<String, String> map) {
        return this.configService.saveConfig(map) ? Result.success() : Result.fail();
    }
}
