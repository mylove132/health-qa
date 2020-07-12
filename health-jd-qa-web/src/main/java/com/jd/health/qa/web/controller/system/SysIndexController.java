package com.jd.health.qa.web.controller.system;

import com.jd.health.qa.common.annotation.LoginAuth;
import com.jd.health.qa.common.config.Global;
import com.jd.health.qa.framework.web.base.BaseController;
import com.jd.health.qa.system.domain.SysMenu;
import com.jd.health.qa.system.domain.SysUser;
import com.jd.health.qa.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 首页 业务处理
 *
 * @author ruoyi
 */
@Controller
@LoginAuth
public class SysIndexController extends BaseController {

    private final ISysMenuService menuService;

    @Autowired
    public SysIndexController(ISysMenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 系统首页
     *
     * @param mmap ModelMap
     * @return
     */
    @GetMapping("/index")
    public String index(ModelMap mmap,SysUser sysUser) {
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(sysUser);
        mmap.put("menus", menus);
        mmap.put("user", sysUser);
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("demoEnabled", Global.isDemoEnabled());
        return "index";
    }


    /**
     * 系统介绍
     *
     * @param mmap ModelMap
     * @return
     */
    @GetMapping("/system/main")
    public String main(ModelMap mmap) {
        mmap.put("version", Global.getVersion());
        return "main";
    }

    /**
     * 统计模版首页
     *
     * @param mmap ModelMap
     * @return
     */
    @GetMapping("/system/mainV1")
    public String mainV1(ModelMap mmap) {
        mmap.put("version", Global.getVersion());
        return "main_v1";
    }
}
