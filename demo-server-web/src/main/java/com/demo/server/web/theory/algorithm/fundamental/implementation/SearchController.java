package com.demo.server.web.theory.algorithm.fundamental.implementation;

import com.demo.server.biz.theory.algorithm.fundamental.SearchService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vince Yuan
 * @date 03/12/2021
 */
@Api(value = "原理-基础算法-实现-搜索", tags = "原理-基础算法-实现-搜索")
@Slf4j
@RestController
@RequestMapping("/algorithm/fundamental/implementation/search")
public class SearchController {

    @Autowired
    private SearchService searchService;
}
