package com.demo.server.web.application.springboot;

import com.demo.base.common.response.bean.CommonResponse;
import com.demo.base.common.response.enums.ResponseEnum;
import com.demo.server.common.utils.SystemUtils;
import com.demo.server.req.GetWithParametersReq;
import com.demo.server.req.PostWithParametersReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Vince Yuan
 * @date 12/30/2020
 */
@Api(value = "应用-SpringBoot-处理请求", tags = "应用-SpringBoot-处理请求")
@Slf4j
@RestController
@RequestMapping("/spring/boot/request")
public class RuquestController {

    @ApiOperation(value = "处理Get请求-无入参", notes = "处理Get请求-无入参")
    @GetMapping("/get")
    public CommonResponse get() {
        String data = "Hello GET request";
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(data))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "处理Get请求-有入参-Restful风格-字段接收", notes = "处理Get请求-有入参-Restful风格-字段接收")
    @GetMapping("/getWithParametersInRestfulAndReceivedByField/{id}/{name}/{score}")
    public CommonResponse getWithParametersInRestfulAndReceivedByField(@PathVariable(name = "id", required = false) Long userId,
                                                                       @PathVariable(name = "name", required = false) String userName,
                                                                       @PathVariable(name = "score", required = false) Double userScore) {
        String data = "Hello GET request" +
                " | id: " + userId +
                " | name: " + userName +
                " | score: " + userScore;
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(data))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "处理Get请求-有入参-QueryString形式-字段接收", notes = "处理Get请求-有入参-QueryString形式-字段接收")
    @GetMapping("/getWithParametersInQueryStringAndReceivedByField")
    public CommonResponse getWithParametersInQueryStringAndReceivedByField(@RequestParam(name = "id", required = false) Long userId,
                                                                           @RequestParam(name = "name", required = false) String userName,
                                                                           @RequestParam(name = "score", required = false) Double userScore,
                                                                           @RequestParam(name = "courses", required = false) String[] userCourses,
                                                                           @RequestParam(name = "teachers", required = false) List<String> userTeachers) {
        String data = "Hello GET request" +
                " | id: " + userId +
                " | name: " + userName +
                " | score: " + userScore +
                " | courses: " + userCourses == null ? null : Arrays.asList(userCourses) +
                " | userTeachers: " + userTeachers;
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(data))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "处理Get请求-有入参-QueryString形式-对象接收", notes = "处理Get请求-有入参-QueryString形式-对象接收")
    @GetMapping("/getWithParametersInQueryStringAndReceivedByBean")
    public CommonResponse getWithParametersInQueryStringAndReceivedByBean(GetWithParametersReq req) {
        String data = "Hello GET request" +
                " | id: " + req.getId() +
                " | name: " + req.getName() +
                " | score: " + req.getScore() +
                " | courses: " + (req.getCourses() != null ? Arrays.asList(req.getCourses()).toString() : null) +
                " | userTeachers: " + req.getTeachers();
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(data))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "处理Get请求-有入参-QueryString形式-映射接收", notes = "处理Get请求-有入参-QueryString形式-映射接收")
    @GetMapping("/getWithParametersInQueryStringAndReceivedByMap")
    public CommonResponse getWithParametersInQueryStringAndReceivedByMap(@RequestParam(required = false) Map<String, Object> parameters) {
        String data = "Hello GET request" +
                " | parameters: " + parameters;
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(data))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "处理Post请求-无入参", notes = "处理Post请求-无入参")
    @PostMapping("/post")
    public CommonResponse post() {
        String data = "Hello Post";
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(data))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "处理Post请求-有入参-JSON形式-对象接收", notes = "处理Post请求-有入参-JSON形式-对象接收")
    @PostMapping("/postWithParametersInJSONAndReceivedByBean")
    public CommonResponse postWithParametersInJSONAndReceivedByBean(@RequestBody(required = false) PostWithParametersReq req) {
        String data = "Hello POST request" +
                " | id: " + req.getId() +
                " | name: " + req.getName() +
                " | score: " + req.getScore() +
                " | courses: " + (req.getCourses() != null ? Arrays.asList(req.getCourses()).toString() : null) +
                " | userTeachers: " + req.getTeachers();
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(data))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "处理Post请求-有入参-JSON形式-映射接收", notes = "处理Post请求-有入参-JSON形式-映射接收")
    @PostMapping("/postWithParametersInJSONAndReceivedByMap")
    public CommonResponse postWithParametersInJSONAndReceivedByMap(@RequestBody(required = false) Map<String, Object> parameters) {
        String data = "Hello POST request" +
                " | parameters: " + parameters;
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(data))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "处理Post请求-有入参-Form形式-字段接收", notes = "处理Post请求-有入参-Form形式-字段接收")
    @PostMapping("/postWithParametersInFormAndReceivedByField")
    public CommonResponse postWithParametersInFormAndReceivedByField(@RequestParam(name = "id", required = false) Long userId,
                                                                     @RequestParam(name = "name", required = false) String userName,
                                                                     @RequestParam(name = "score", required = false) Double userScore) {
        String data = "Hello POST request" +
                " | id: " + userId +
                " | name: " + userName +
                " | score: " + userScore
                ;
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(data))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "处理Post请求-有入参-Form形式-对象接收", notes = "处理Post请求-有入参-Form形式-对象接收")
    @PostMapping("/postWithParametersInFormAndReceivedByBean")
    public CommonResponse postWithParametersInFormAndReceivedByBean(PostWithParametersReq req) {
        String data = "Hello POST request" +
                " | id: " + req.getId() +
                " | name: " + req.getName() +
                " | score: " + req.getScore()
                ;
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(data))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "处理Post请求-有入参-Form形式-映射接收", notes = "处理Post请求-有入参-Form形式-映射接收")
    @PostMapping("/postWithParametersInFormAndReceivedByMap")
    public CommonResponse postWithParametersInFormAndReceivedByMap(@RequestParam(required = false) Map<String, Object> parameters) {
        String data = "Hello POST request" +
                " | parameters: " + parameters;
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(data))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }
}
