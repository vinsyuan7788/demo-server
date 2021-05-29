package com.demo.spring.boot.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Vince Yuan
 * @date 03/02/2021
 */
@ApiModel("出参-示范记录")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoRecordResp implements Serializable {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updated;

    @ApiModelProperty("创建人")
    private Long creator;

    @ApiModelProperty("修改人")
    private Long modifier;

    @ApiModelProperty("是否删除")
    private String deleted;

    @ApiModelProperty("记录名称")
    private String demoName;
}
