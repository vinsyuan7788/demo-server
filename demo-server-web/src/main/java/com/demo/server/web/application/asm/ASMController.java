package com.demo.server.web.application.asm;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vince Yuan
 * @date 08/26/2021
 */
@Api(value = "应用-ASM (Assembly)", tags = "应用-ASM (Assembly)")
@Slf4j
@RestController
@RequestMapping("/asm")
public class ASMController {
}
