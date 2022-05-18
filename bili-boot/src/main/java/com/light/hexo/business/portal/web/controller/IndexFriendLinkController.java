package com.light.hexo.business.portal.web.controller;

import com.light.hexo.business.admin.model.FriendLink;
import com.light.hexo.business.portal.common.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: IndexFriendLinkController
 * @ProjectName: hexo-boot
 * @Description: 友链控制器（首页）
 */
@Controller
public class IndexFriendLinkController extends CommonController {

    /**
     * 友链
     * @param resultMap
     * @return
     */
    @GetMapping(value = {"friendLinks", "friendLinks/", "friendLinks/index.html"})
    public String friendLinks(Map<String, Object> resultMap) {
        List<FriendLink> friendLinkList = this.friendLinkService.listFriendLinkByIndex();
        resultMap.put("friendLinkNum", friendLinkList.size());

        List<FriendLink> bloggerList = friendLinkList.stream()
                .filter(i -> i.getLinkType().equals(1))
                .sorted(Comparator.comparing(FriendLink::getSort)).collect(Collectors.toList());
        resultMap.put("bloggerList", bloggerList);

        List<FriendLink> webSiteList = friendLinkList.stream()
                .filter(i -> i.getLinkType().equals(2))
                .sorted(Comparator.comparing(FriendLink::getSort)).collect(Collectors.toList());
        resultMap.put("webSiteList", webSiteList);

        resultMap.put("currentNav", this.navService.findByLink("/friendLinks/"));
        return render("friendLinks", false, resultMap);
    }
}
