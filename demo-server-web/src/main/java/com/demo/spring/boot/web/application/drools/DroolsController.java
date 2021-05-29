package com.demo.spring.boot.web.application.drools;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vince Yuan
 * @date 03/27/2021
 */
@Api(value = "应用-Drools", tags = "应用-Drools")
@Slf4j
@RestController
@RequestMapping("/drools")
public class DroolsController {
}
