package com.shuframework.admin.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.shuframework.admin.model.SysUser;
import com.shuframework.admin.query.SysUserDTO;
import com.shuframework.admin.service.SysUserService;
import com.shuframework.commonbase.enums.FailureEnum;
import com.shuframework.commonbase.result.Result;
import com.shuframework.commonbase.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 系统表_用户 前端控制器
 * </p>
 *
 * @author shuheng
 * @since 2019-03-04
 */
@RestController
@RequestMapping("/admin/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    /**
     * 获取数据列表
     */
    @RequestMapping("/selectPage")
    public Result selectPage(@RequestBody SysUserDTO sysUserDTO){
        Page page = new Page();
        sysUserService.selectPage(page);
        return ResultUtil.success(page);
    }

    /**
     * 获取全部数据
     */
    @RequestMapping("/findAll")
    public Result findAll(@RequestBody SysUserDTO sysUserDTO){
        List<SysUser> list = sysUserService.selectList(null);
        return ResultUtil.success(list);
    }

    /**
     * 根据ID查找数据
     */
    @RequestMapping("/detail")
    public Result detail(@RequestParam("id") Long id){
        SysUser sysUser = sysUserService.selectById(id);
        if(sysUser == null){
            return ResultUtil.failure(FailureEnum.NOTEXIST_FAILURE);
        }
        return ResultUtil.success(sysUser);
    }

    /**
     * 添加数据
     */
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody SysUser sysUser){
        boolean flag = sysUserService.insert(sysUser);
        if(flag){
            return ResultUtil.successOfInsert(sysUser);
        }
        return ResultUtil.failureOfInsert(sysUser);
    }

    /**
     * 更新数据
     */
    @PostMapping(value = "/update")
    public Result update(@RequestBody SysUser sysUser){
        boolean isOk = sysUserService.updateAllColumnById(sysUser);
        if(isOk){
            return ResultUtil.successOfUpdate(sysUser);
        }
        return ResultUtil.failureOfUpdate(sysUser);
    }

    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public Result delete(@RequestParam("ids") List<Long> ids){
        boolean isOk = sysUserService.deleteBatchIds(ids);
        if(isOk){
            return ResultUtil.successOfDelete(ids);
        }
        return ResultUtil.failureOfDelete(ids);
    }

}
