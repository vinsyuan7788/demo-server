package com.demo.server.web.application.excel;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vince Yuan
 * @date 06/08/2021
 */
@Api(value = "应用-Excel-POI", tags = "应用-Excel-POI")
@Slf4j
@RestController
@RequestMapping("/excel/poi")
public class EasyPoiController {
}
