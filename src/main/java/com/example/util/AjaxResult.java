package com.example.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ajax json 返回数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AjaxResult {

    private boolean success;

    private String message;

    private String type;
}
