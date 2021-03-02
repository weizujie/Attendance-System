package com.example.util;

import lombok.Data;

/**
 * ajax json 返回数据
 */
@Data
public class AjaxResult {

    private boolean success;

    private String message;

    private String type;

}
