package com.demo.server.application.excel.easyexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vince Yuan
 * @date 06/04/2021
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectRecordTemplateResp extends BaseRowModel {

    @ExcelProperty(value = "委外机构名称", index = 0)
    @ApiModelProperty("委外机构名称：必填")
    private String outCompanyName;

//    @ExcelProperty(value = "案件编号", index = 1)
//    @ApiModelProperty("案件编号：必填")
//    private String billCode;
    @ExcelProperty(value = "借据编号", index = 1)
    @ApiModelProperty("借据编号：必填")
    private String iouCode;

    @ExcelProperty(value = "催记时间", index = 2)
//    @ExcelProperty(value = "催记时间", index = 2, format = "yyyy/MM/dd HH:mm:ss")
    @ApiModelProperty("催记时间：必填；必须得是年月日时分秒")
    private String collectionRecordDate;

    @ExcelProperty(value = "催收渠道", index = 3)
    @ApiModelProperty("催收渠道：必填；委外电催，委外上门")
    private String collectionSource;

    @ExcelProperty(value = "业务组", index = 4)
    @ApiModelProperty("业务组：选填")
    private String organName;

    @ExcelProperty(value = "催收员账号", index = 5)
    @ApiModelProperty("催收员账号：选填")
    private String agentAccount;

    @ExcelProperty(value = "拨打方式", index = 6)
    @ApiModelProperty("拨打方式：选填")
    private String callWay;

    @ExcelProperty(value = "催收对象", index = 7)
    @ApiModelProperty("催收对象：必填；本人、父母、子女、兄弟、同事、朋友中其中之一")
    private String collectionObject;

    @ExcelProperty(value = "拨打号码", index = 8)
    @ApiModelProperty("拨打号码：必填")
    private String callPhone;

    @ExcelProperty(value = "呼叫结果", index = 9)
    @ApiModelProperty("呼叫结果：必填")
    private String callResult;

    @ExcelProperty(value = "催收结果", index = 10)
    @ApiModelProperty("催收结果：必填")
    private String collectionResult;

    @ExcelProperty(value = "承诺还款金额", index = 11)
    @ApiModelProperty("承诺还款金额：选填")
    private String promiseRepayMoney;

    @ExcelProperty(value = "承诺还款日期", index = 12)
//    @ExcelProperty(value = "承诺还款日期", index = 12, format = "yyyy/MM/dd HH:mm:ss")
    @ApiModelProperty("承诺还款日期：选填")
    private String promiseRepayDate;

    @ExcelProperty(value = "备注", index = 13)
    @ApiModelProperty("备注")
    private String remark;
}
