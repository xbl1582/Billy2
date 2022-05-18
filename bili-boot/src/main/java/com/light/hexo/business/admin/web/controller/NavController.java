package com.light.hexo.business.admin.web.controller;

import com.github.pagehelper.PageInfo;
import com.light.hexo.business.admin.model.Nav;
import com.light.hexo.business.admin.service.NavService;
import com.light.hexo.common.base.BaseController;
import com.light.hexo.common.base.BaseRequest;
import com.light.hexo.common.component.log.ActionEnum;
import com.light.hexo.common.component.log.OperateLog;
import com.light.hexo.common.exception.GlobalExceptionEnum;
import com.light.hexo.common.model.NavRequest;
import com.light.hexo.common.model.Result;
import com.light.hexo.common.util.ExceptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: NavController
 * @ProjectName hexo-boot
 * @Description: 自定义页面控制器
 */
@RequestMapping("/admin/nav")
@Controller
public class NavController extends BaseController {

    @Autowired
    private NavService navService;

    /**
     * 新增页
     * @param resultMap
     * @return
     */
    @RequestMapping("addUI.html")
    public String addUI(Map<String, Object> resultMap) {
        List<Nav> navList =  this.navService.listParentNav();
        resultMap.put("navList", navList);
        return this.render("addUI", resultMap);
    }

    /**
     * 编辑页
     * @param resultMap
     * @return
     */
    @RequestMapping("editUI/{id}.html")
    public String editUI(@PathVariable Integer id, Map<String, Object> resultMap) {
        Nav nav = this.navService.findById(id);
        resultMap.put("vo", nav);
        List<Nav> navList =  this.navService.listParentNav();
        resultMap.put("navList", navList);
        return this.render("editUI", resultMap);
    }

    /**
     * 新增导航
     * @param request
     * @return
     */
    @RequestMapping("add.json")
    @ResponseBody
    @OperateLog(value = "新增导航", actionType = ActionEnum.ADMIN_ADD)
    public Result add(@Validated(BaseRequest.Save.class) NavRequest request) {
        Nav nav = request.toDoModel();
        nav.setName(nav.getName().trim());
        nav.setSort(Integer.valueOf(request.getSort()));
        this.navService.saveNav(nav);
        return Result.success();
    }

    /**
     * 编辑导航
     * @param request
     * @return
     */
    @RequestMapping("edit.json")
    @ResponseBody
    @OperateLog(value = "编辑导航", actionType = ActionEnum.ADMIN_EDIT)
    public Result edit(@Validated(BaseRequest.Update.class) NavRequest request) {
        Nav nav = request.toDoModel();
        nav.setName(nav.getName().trim());
        nav.setSort(Integer.valueOf(request.getSort()));
        this.navService.updateNav(nav);
        return Result.success();
    }

    /**
     * 修改状态
     * @param request
     * @return
     */
    @RequestMapping("updateState.json")
    @ResponseBody
    @OperateLog(value = "修改导航状态", actionType = ActionEnum.ADMIN_EDIT)
    public Result updateState(NavRequest request) {
        this.navService.updateModel(request.toDoModel());
        return Result.success();
    }

    /**
     * 删除导航
     * @param idStr
     * @return
     */
    @RequestMapping("remove.json")
    @ResponseBody
    @OperateLog(value = "删除导航", actionType = ActionEnum.ADMIN_REMOVE)
    public Result remove(@RequestParam String idStr) {
        if (StringUtils.isBlank(idStr)) {
            ExceptionUtil.throwEx(GlobalExceptionEnum.ERROR_PARAM);
        }

        this.navService.removeNavBatch(Arrays.asList(idStr.split(",")));
        return Result.success();
    }

    /**
     * 分页查询
     * @param request
     * @return
     */
    @RequestMapping("list.json")
    @ResponseBody
    public Result list(NavRequest request) {
        PageInfo<Nav> pageInfo = this.navService.findPage(request);
        return Result.success(pageInfo);
    }
}
