package com.demo.spring.boot.web.business;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vince Yuan
 * @date 03/02/2021
 */
@Api(value = "XX业务相关", tags = "XX业务相关")
@Slf4j
@RestController
@RequestMapping("/business")
public class BusinessController {
}
