package com.demo.server.web.application.springboot;

import com.demo.base.common.response.bean.CommonResponse;
import com.demo.base.common.response.enums.ResponseEnum;
import com.demo.server.biz.application.springboot.RedisService;
import com.demo.server.common.utils.SystemUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author Vince Yuan
 * @date 05/15/2021
 */
@Api(value = "应用-SpringBoot-Redis缓存", tags = "应用-SpringBoot-Redis缓存")
@Slf4j
@RestController
@RequestMapping("/spring/boot/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "设置指定键的值", notes = "设置指定键的值")
    @PostMapping("/setKey")
    public CommonResponse setKey(@RequestBody String key, @RequestBody String value) {
        redisService.setKey(key, value);
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(key + " has been set successfully"))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "获取指定键的值", notes = "获取指定键的值")
    @PostMapping("/getKey")
    public CommonResponse getKey(@RequestBody String key) {
        Object value = redisService.getKey(key);
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(value))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "移除指定键", notes = "移除指定键")
    @PostMapping("/removeKey")
    public CommonResponse removeKey(@RequestBody String key) {
        redisService.removeKeys(Collections.singletonList(key));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(key + " has been removed successfully"))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "获取下一个整数", notes = "获取下一个整数（通过Redis支持的AtomicInteger）")
    @PostMapping("/getNextIntegerFromRedisAtomicInteger")
    public CommonResponse getNextIntegerFromRedisAtomicInteger() {
        int nextInteger = redisService.getNextIntegerFromRedisAtomicInteger();
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(nextInteger))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }
}
