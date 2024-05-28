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
 * 告警详情表
 * </p>
 *
 * @author ning
 * @since 2024-05-28 09:20:20
 */
@Getter
@Setter
@TableName("yw_alarm_detail")
public class AlarmDetailEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 告警主键id
     */
    @TableField("alarm_id")
    private Long alarmId;

    /**
     * 操作人
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 操作人告警时的ip地址
     */
    @TableField("ip")
    private String ip;

    /**
     * 应用主键id
     */
    @TableField("app_id")
    private Long appId;

    /**
     * 策略主键id
     */
    @TableField("strategy_id")
    private Long strategyId;

    /**
     * 行为主键id
     */
    @TableField("behavior_id")
    private Long behaviorId;

    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    @TableField(value = "updated_time", fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;


}
