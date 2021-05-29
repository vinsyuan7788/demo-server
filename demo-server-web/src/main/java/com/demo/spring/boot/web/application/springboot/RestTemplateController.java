package com.demo.spring.boot.web.application.springboot;

import com.alibaba.fastjson.JSONObject;
import com.demo.base.common.response.bean.CommonResponse;
import com.demo.base.common.response.enums.ResponseEnum;
import com.demo.base.common.utils.LogUtils;
import com.demo.base.common.utils.utils.ParametersToLog;
import com.demo.spring.boot.common.utils.RestTemplateUtils;
import com.demo.spring.boot.common.utils.SystemUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Vince Yuan
 * @date 04/11/2021
 */
@Api(value = "应用-SpringBoot-RestTemplate", tags = "应用-SpringBoot-RestTemplate")
@Slf4j
@RestController
@RequestMapping("/spring/boot/rest/template")
public class RestTemplateController {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    private Environment environment;

    private final String logInfo = "Please see the log for more info";

    @ApiOperation(value = "发送Get请求", notes = "发送Get请求")
    @PostMapping("/sendGetRequest")
    public CommonResponse sendGetRequest() {
        // Get rest template
        RestTemplate restTemplate = RestTemplateUtils.getRestTemplate(restTemplateBuilder);
        // Send GET request and return response entity
        ResponseEntity<CommonResponse> responseEntity = restTemplate.getForEntity(RestTemplateUtils.getURL(RestTemplateUtils.HTTP_PROTOCOL, "/spring/boot/request/get", environment), CommonResponse.class);
        log.info(LogUtils.getLogMessage("sendGetRequest", new ParametersToLog()
                .addParameter("response entity", responseEntity), true));
        responseEntity = restTemplate.getForEntity(RestTemplateUtils.getURL(RestTemplateUtils.HTTP_PROTOCOL, "/spring/boot/request/getWithParametersInRestfulAndReceivedByField/1/vince/98.27", environment), CommonResponse.class);
        log.info(LogUtils.getLogMessage("sendGetRequest", new ParametersToLog()
                .addParameter("response entity", responseEntity), true));
        // Send GET request and return response content
        CommonResponse responseContent = restTemplate.getForObject(RestTemplateUtils.getURL(RestTemplateUtils.HTTP_PROTOCOL, "/spring/boot/request/getWithParametersInQueryStringAndReceivedByField" + getQueryString(), environment), CommonResponse.class);
        log.info(LogUtils.getLogMessage("sendGetRequest", new ParametersToLog()
                .addParameter("response content", responseContent), true));
        responseContent = restTemplate.getForObject(RestTemplateUtils.getURL(RestTemplateUtils.HTTP_PROTOCOL, "/spring/boot/request/getWithParametersInQueryStringAndReceivedByBean" + getQueryString(), environment), CommonResponse.class);
        log.info(LogUtils.getLogMessage("sendGetRequest", new ParametersToLog()
                .addParameter("response content", responseContent), true));
        responseContent = restTemplate.getForObject(RestTemplateUtils.getURL(RestTemplateUtils.HTTP_PROTOCOL, "/spring/boot/request/getWithParametersInQueryStringAndReceivedByMap" + getQueryString(), environment), CommonResponse.class);
        log.info(LogUtils.getLogMessage("sendGetRequest", new ParametersToLog()
                .addParameter("response content", responseContent), true));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "发送Post请求", notes = "发送Post请求")
    @PostMapping("/sendPostRequest")
    public CommonResponse sendPostRequest() {
        // Get rest template and URl to send requestS
        RestTemplate restTemplate = RestTemplateUtils.getRestTemplate(restTemplateBuilder);
        // Send POST request and return response entity
        ResponseEntity<CommonResponse> responseEntity = restTemplate.postForEntity(RestTemplateUtils.getURL(RestTemplateUtils.HTTP_PROTOCOL, "/request/post", environment), getJSONRequest(), CommonResponse.class);
        log.info(LogUtils.getLogMessage("sendPostRequest", new ParametersToLog()
                .addParameter("response entity", responseEntity), true));
        responseEntity = restTemplate.postForEntity(RestTemplateUtils.getURL(RestTemplateUtils.HTTP_PROTOCOL, "/request/postWithParametersInJSONAndReceivedByBean", environment), getJSONRequest(), CommonResponse.class);
        log.info(LogUtils.getLogMessage("sendPostRequest", new ParametersToLog()
                .addParameter("response entity", responseEntity), true));
        responseEntity = restTemplate.postForEntity(RestTemplateUtils.getURL(RestTemplateUtils.HTTP_PROTOCOL, "/request/postWithParametersInJSONAndReceivedByMap", environment), getJSONRequest(), CommonResponse.class);
        log.info(LogUtils.getLogMessage("sendPostRequest", new ParametersToLog()
                .addParameter("response entity", responseEntity), true));
        // Send POST request and return response content
        CommonResponse responseContent = restTemplate.postForObject(RestTemplateUtils.getURL(RestTemplateUtils.HTTP_PROTOCOL, "/request/postWithParametersInFormAndReceivedByField", environment), getFormRequest(), CommonResponse.class);
        log.info(LogUtils.getLogMessage("sendPostRequest", new ParametersToLog()
                .addParameter("response content", responseContent), true));
        responseContent = restTemplate.postForObject(RestTemplateUtils.getURL(RestTemplateUtils.HTTP_PROTOCOL, "/request/postWithParametersInFormAndReceivedByBean", environment), getFormRequest(), CommonResponse.class);
        log.info(LogUtils.getLogMessage("sendPostRequest", new ParametersToLog()
                .addParameter("response content", responseContent), true));
        responseContent = restTemplate.postForObject(RestTemplateUtils.getURL(RestTemplateUtils.HTTP_PROTOCOL, "/request/postWithParametersInFormAndReceivedByMap", environment), getFormRequest(), CommonResponse.class);
        log.info(LogUtils.getLogMessage("sendPostRequest", new ParametersToLog()
                .addParameter("response content", responseContent), true));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    /**
     *  Get query string
     *
     * @return
     */
    private String getQueryString() {
        return "?id=1" +
                "&name=vince" +
                "&score=98.27" +
                "&courses=cook,drive,sport" +
                "&teachers=violet,yoki,ck";
    }

    /**
     *  Get JSON request
     *
     * @return
     */
    private HttpEntity<String> getJSONRequest() {
        JSONObject parameters = new JSONObject();
        parameters.put("id", 1L);
        parameters.put("name", "vince");
        parameters.put("score", 98.27);
        parameters.put("courses", new String[] { "cook", "drive", "sport" } );
        parameters.put("teachers", Arrays.asList("violet", "yoki", "ck"));
        return RestTemplateUtils.getJSONRequest(parameters);
    }

    /**
     *  Get form request
     *
     * @return
     */
    private HttpEntity<MultiValueMap<String, String>> getFormRequest() {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("id", "1");
        parameters.add("name", "vince");
        parameters.add("score", "98.27");
        return RestTemplateUtils.getFormRequest(parameters);
    }
}
