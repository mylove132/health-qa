package com.jd.health.qa.web.controller.monitor;

import com.jd.health.qa.framework.web.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * druid 监控
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/data")
@ApiIgnore(value = "druid数据源监控")
public class DruidController extends BaseController {

    @RequiresPermissions("monitor:data:view")
    @GetMapping()
    public String index() {
        String url = "/monitor/druid/index";
        return redirect(url);
    }
}
