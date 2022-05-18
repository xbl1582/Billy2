package com.light.hexo.business.admin.web.controller;

import com.github.pagehelper.PageInfo;
import com.light.hexo.business.admin.model.Category;
import com.light.hexo.business.admin.model.Post;
import com.light.hexo.business.admin.model.PostTask;
import com.light.hexo.business.admin.service.CategoryService;
import com.light.hexo.business.admin.service.PostService;
import com.light.hexo.business.admin.service.PostTaskService;
import com.light.hexo.common.base.BaseController;
import com.light.hexo.common.base.BaseRequest;
import com.light.hexo.common.component.log.ActionEnum;
import com.light.hexo.common.component.log.OperateLog;
import com.light.hexo.common.exception.GlobalExceptionEnum;
import com.light.hexo.common.model.PostRequest;
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
 * @ClassName: PostController
 * @ProjectName hexo-boot
 * @Description: 文章控制器
 */
@RequestMapping("/admin/post")
@Controller
public class PostController extends BaseController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostTaskService postTaskService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 列表页
     * @param resultMap
     * @return
     */
    @RequestMapping("listUI.html")
    public String listUI(Map<String, Object> resultMap) {
        List<Category> categoryList = this.categoryService.findAll(true);
        resultMap.put("categoryList", categoryList);
        return this.render("listUI", resultMap);
    }

    /**
     * 新增页
     * @param resultMap
     * @return
     */
    @RequestMapping("addUI.html")
    public String addUI(Map<String, Object> resultMap) {
        List<Category> categoryList = this.categoryService.findAll(true);
        resultMap.put("categoryList", categoryList);
        return this.render("addUI", resultMap);
    }

    /**
     * 编辑页
     * @param resultMap
     * @return
     */
    @RequestMapping("editUI/{id}.html")
    public String editUI(@PathVariable Integer id, Map<String, Object> resultMap) {
        Post post = this.postService.findById(id);
        resultMap.put("vo", post);
        List<Category> categoryList = this.categoryService.findAll(true);
        resultMap.put("categoryList", categoryList);
        PostTask postTask = this.postTaskService.getPostTask(post.getId());
        resultMap.put("jobTime", postTask != null ? postTask.getJobTime() : "");
        return this.render("editUI", resultMap);
    }

    /**
     * 导入页
     * @param resultMap
     * @return
     */
    @RequestMapping("importUI.html")
    public String importUI(Map<String, Object> resultMap) {
        return this.render("importUI", resultMap);
    }

    /**
     * 新增文章
     * @param request
     * @return
     */
    @RequestMapping("add.json")
    @ResponseBody
    @OperateLog(value = "新增文章", actionType = ActionEnum.ADMIN_ADD)
    public Result add(@Validated(BaseRequest.Save.class) PostRequest request) {
        Post post = request.toDoModel();
        this.postService.savePost(post);
        return Result.success();
    }

    /**
     * 编辑文章
     * @param request
     * @return
     */
    @RequestMapping("edit.json")
    @ResponseBody
    @OperateLog(value = "编辑文章", actionType = ActionEnum.ADMIN_EDIT)
    public Result edit(@Validated(BaseRequest.Update.class) PostRequest request) {
        Post post = request.toDoModel();
        this.postService.editPost(post);
        return Result.success();
    }

    /**
     * 导入文章
     * @param path
     * @return
     */
    @RequestMapping("importPosts.json")
    @ResponseBody
    @OperateLog(value = "导入文章", actionType = ActionEnum.ADMIN_ADD)
    public Result importPosts(@RequestParam String path, @RequestParam String type) {
        if ("md".equals(type)) {
            this.postService.importPostsByMd(path);
        } else if ("json".equals(type)) {
            this.postService.importPostsByJson(path);
        }
        return Result.success();
    }

    /**
     * 修改开关
     * @param request
     * @return
     */
    @RequestMapping("updateSwitch.json")
    @ResponseBody
    @OperateLog(value = "修改文章状态", actionType = ActionEnum.ADMIN_EDIT)
    public Result updateState(PostRequest request) {
        this.postService.updateState(request.toDoModel());
        return Result.success();
    }

    /**
     * 删除文章
     * @param idStr
     * @return
     */
    @RequestMapping("remove.json")
    @ResponseBody
    @OperateLog(value = "删除文章", actionType = ActionEnum.ADMIN_REMOVE)
    public Result remove(@RequestParam String idStr) {
        if (StringUtils.isBlank(idStr)) {
            ExceptionUtil.throwEx(GlobalExceptionEnum.ERROR_PARAM);
        }

        this.postService.removePostBatch(Arrays.asList(idStr.split(",")));
        return Result.success();
    }

    /**
     * 发表文章
     * @param id
     * @return
     */
    @RequestMapping("publishPost.json")
    @ResponseBody
    @OperateLog(value = "发表文章", actionType = ActionEnum.ADMIN_EDIT)
    public Result publishPost(Integer id) {
        this.postService.publishPost(id);
        return Result.success();
    }

    /**
     * 列表查询
     * @param request
     * @return
     */
    @RequestMapping("list.json")
    @ResponseBody
    public Result list(PostRequest request) {
        PageInfo<Post> pageInfo = this.postService.findPage(request);
        return Result.success(pageInfo);
    }
}
