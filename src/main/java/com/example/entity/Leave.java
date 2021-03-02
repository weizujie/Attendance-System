package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.constant.LeaveConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * 请假 实体类
 *
 * @author weizujie
 * @date 2021/03/02
 */
@Data
@TableName("s_leave")
public class Leave implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请假 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 请假的学生 id
     */
    private Integer studentId;

    /**
     * 请假理由
     */
    private String info;

    /**
     * 请假条状态
     */
    private Integer status = LeaveConstant.LEAVE_STATUS_WAIT;

    /**
     * 批复内容
     */
    private String remark;
}
