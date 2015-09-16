package com.github.star45.shiro.chapter16.web.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.star45.shiro.chapter16.entity.Resource;
import com.github.star45.shiro.chapter16.entity.User;
import com.github.star45.shiro.chapter16.service.ResourceService;
import com.github.star45.shiro.chapter16.service.UserService;
import com.github.star45.shiro.chapter16.web.bind.annotation.CurrentUser;

/**
 * 
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月16日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */
@Controller
public class IndexController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(@CurrentUser User loginUser, Model model) {
        Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        List<Resource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


}
