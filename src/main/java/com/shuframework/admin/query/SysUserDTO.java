package com.shuframework.admin.query;

import java.io.Serializable;
import java.util.Date;

import com.shuframework.commoncore.base.BaseDTO;
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
public class SysUserDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String loginName;
    private String nickName;
    private String password;
    private String sex;
    private String iconUrl;
    private String phoneNum;
    private String phoneNum2;
    private String email;
    private String address;
    private String deptCode;
    private String useType;
    private Date leaveTime;
    private Integer sortno;
    private String remarks;
    private String createBy;
    private Date createTime;
    private Date updateTime;
    private String enabled;

}
