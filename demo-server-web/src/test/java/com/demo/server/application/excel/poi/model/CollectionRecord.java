package com.demo.server.application.excel.poi.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vince Yuan
 * @date 06/08/2021
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionRecord extends BaseRowModel {

    @ExcelProperty(value = "编号", index = 0)
    private String code;

    @ExcelProperty(value = "日期", index = 0)
    private String date;

    @ExcelProperty(value = "金额", index = 0)
    private String money;
}
