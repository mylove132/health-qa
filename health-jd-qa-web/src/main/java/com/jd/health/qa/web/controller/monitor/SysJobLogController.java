package com.jd.health.qa.web.controller.monitor;

import com.jd.health.qa.common.annotation.Log;
import com.jd.health.qa.common.base.AjaxResult;
import com.jd.health.qa.common.enums.BusinessType;
import com.jd.health.qa.common.page.TableDataInfo;
import com.jd.health.qa.common.utils.ExcelUtil;
import com.jd.health.qa.framework.web.base.BaseController;
import com.jd.health.qa.quartz.domain.SysJobLog;
import com.jd.health.qa.quartz.service.ISysJobLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 调度日志操作处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/jobLog")
public class SysJobLogController extends BaseController {
    private String prefix = "monitor/job";

    private final ISysJobLogService jobLogService;

    private static final String JOB_LOG = "jobLog";

    @Autowired
    public SysJobLogController(ISysJobLogService jobLogService) {
        this.jobLogService = jobLogService;
    }

    @RequiresPermissions("monitor:job:view")
    @GetMapping()
    public String jobLog() {
        return prefix + "/jobLog";
    }

    @RequiresPermissions("monitor:job:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysJobLog jobLog) {
        startPage();
        List<SysJobLog> list = jobLogService.selectJobLogList(jobLog);
        return getDataTable(list);
    }

    @Log(title = "调度日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:job:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysJobLog jobLog) {
        List<SysJobLog> list = jobLogService.selectJobLogList(jobLog);
        ExcelUtil<SysJobLog> util = new ExcelUtil<>(SysJobLog.class);
        return util.exportExcel(list, "调度日志");
    }

    @Log(title = "调度日志", businessType = BusinessType.DELETE)
    @RequiresPermissions("monitor:job:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(jobLogService.deleteJobLogByIds(ids));
    }

    @RequiresPermissions("monitor:job:detail")
    @GetMapping("/detail/{jobLogId}")
    public String detail(@PathVariable("jobLogId") Long jobLogId, ModelMap mmap) {
        mmap.put("name", JOB_LOG);
        mmap.put(JOB_LOG, jobLogService.selectJobLogById(jobLogId));
        return prefix + "/detail";
    }

    @Log(title = "调度日志", businessType = BusinessType.CLEAN)
    @RequiresPermissions("monitor:job:remove")
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean() {
        jobLogService.cleanJobLog();
        return success();
    }
}
