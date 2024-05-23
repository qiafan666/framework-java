package com.ning.web.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ning
 * @since 2024-05-23 05:30:43
 */
@Getter
@Setter
@TableName("sys_user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * uuid
     */
    @TableField("uuid")
    private String uuid;

    /**
     * 账号
     */
    @TableField("account")
    private String account;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 卡类型
     */
    @TableField("card_style")
    private String cardStyle;

    /**
     * 卡号
     */
    @TableField("card_number")
    private String cardNumber;

    /**
     * 部门id
     */
    @TableField("department_id")
    private Long departmentId;

    /**
     * 职位id
     */
    @TableField("function_id")
    private Long functionId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 风险指数
     */
    @TableField("risk_index")
    private Float riskIndex;

    /**
     * 在线状态（online offline）
     */
    @TableField("online_status")
    private String onlineStatus;

    /**
     * 常用ip
     */
    @TableField("ip_data")
    private String ipData;

    /**
     * 常用设备
     */
    @TableField("device")
    private String device;

    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    @TableField(value = "updated_time", fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;


}
