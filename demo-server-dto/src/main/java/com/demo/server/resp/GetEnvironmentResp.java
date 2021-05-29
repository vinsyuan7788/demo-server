package com.demo.server.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vince Yuan
 * @date 12/30/2020
 */
@ApiModel("出参-获取SpringBoot环境")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetEnvironmentResp {

    @ApiModelProperty("默认配置文件")
    private String[] defaultProfiles;

    @ApiModelProperty("激活配置文件")
    private String[] activeProfiles;

    @ApiModelProperty("应用的服务端口")
    private String serverPort;

    @ApiModelProperty("应用名")
    private String springApplicationName;

    @ApiModelProperty("应用上下文路径")
    private String contextPath;

    @ApiModelProperty("Servlet分配器匹配路径")
    private String servletPath;

    @ApiModelProperty("应用展示的名称")
    private String displayName;
}
