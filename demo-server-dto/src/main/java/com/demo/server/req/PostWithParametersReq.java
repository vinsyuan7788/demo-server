package com.demo.server.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Vince Yuan
 * @date 04/11/2021
 */
@ApiModel("入参-处理Post请求-有入参-Form形式-对象接收")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostWithParametersReq implements Serializable {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("分数")
    private Double score;

    @ApiModelProperty("课程")
    private String[] courses;

    @ApiModelProperty("老师")
    private List<String> teachers;
}
