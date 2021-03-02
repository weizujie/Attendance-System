package com.example.entity;

import com.example.util.LeaveConstant;
import lombok.Data;

/**
 * 请假 实体类
 *
 * @author weizujie
 * @date 2021/03/02
 */
@Data
public class Leave {

    /**
     * 请假 id
     */
    private int id;

    /**
     * 请假的学生 id
     */
    private int studentId;

    /**
     * 请假理由
     */
    private String info;

    /**
     * 请假条状态
     */
    private int status = LeaveConstant.LEAVE_STATUS_WAIT;

    /**
     * 批复内容
     */
    private String remark;
}
