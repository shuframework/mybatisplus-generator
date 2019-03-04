package com.shuframework.admin.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统表_用户
 * </p>
 *
 * @author shuheng
 * @since 2019-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 登录名
     */
    @TableField("login_name")
    private String loginName;
    /**
     * 名称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private String sex;
    /**
     * 头像
     */
    @TableField("icon_url")
    private String iconUrl;
    /**
     * 手机号
     */
    @TableField("phone_num")
    private String phoneNum;
    /**
     * 备用号
     */
    @TableField("phone_num2")
    private String phoneNum2;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 地址
     */
    private String address;
    /**
     * 部门id
     */
    @TableField("dept_code")
    private String deptCode;
    /**
     * 用户类型
     */
    @TableField("use_type")
    private String useType;
    /**
     * 离职时间
     */
    @TableField("leave_time")
    private Date leaveTime;
    /**
     * 排序号
     */
    private Integer sortno;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 状态, 0禁用 1可用 2删除
     */
    private String enabled;


}
