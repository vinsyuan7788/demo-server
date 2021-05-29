package com.demo.server.web.theory.algorithm.fundamental.idea;

import com.demo.base.common.response.bean.CommonResponse;
import com.demo.server.common.utils.RestTemplateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.demo.server.common.utils.RestTemplateUtils.getJSONRequest;

/**
 * @author Vince Yuan
 * @date 04/11/2021
 */
@Api(value = "原理-基础算法-思想-分治", tags = "原理-基础算法-思想-分治")
@Slf4j
@RestController
@RequestMapping("/algorithm/fundamental/idea/dandc")
public class DivideAndConquerController {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    private Environment environment;

    @ApiOperation(value = "数组-插入排序-二分搜索优化", notes = "数组-二分（或折半）插入排序")
    @PostMapping("/insertSortWithBinarySearchInArray")
    public CommonResponse insertSortWithBinarySearchInArray() {
        RestTemplate restTemplate = RestTemplateUtils.getRestTemplate(restTemplateBuilder);
        return restTemplate.postForObject(RestTemplateUtils.getURL(RestTemplateUtils.HTTP_PROTOCOL, "/algorithm/fundamental/implementation/sort/insertSortWithBinarySearchInArray", environment), getJSONRequest(null), CommonResponse.class);
    }

    @ApiOperation(value = "数组-快速排序", notes = "数组-快速排序")
    @PostMapping("/quickSortInArray")
    public CommonResponse quickSortInArray() {
        RestTemplate restTemplate = RestTemplateUtils.getRestTemplate(restTemplateBuilder);
        return restTemplate.postForObject(RestTemplateUtils.getURL(RestTemplateUtils.HTTP_PROTOCOL, "/algorithm/fundamental/implementation/sort/quickSortInArray", environment), getJSONRequest(null), CommonResponse.class);
    }

    @ApiOperation(value = "数组-归并排序", notes = "数组-归并排序")
    @PostMapping("/mergeSortInArray")
    public CommonResponse mergeSortInArray() {
        RestTemplate restTemplate = RestTemplateUtils.getRestTemplate(restTemplateBuilder);
        return restTemplate.postForObject(RestTemplateUtils.getURL(RestTemplateUtils.HTTP_PROTOCOL, "/algorithm/fundamental/implementation/sort/mergeSortInArray", environment), getJSONRequest(null), CommonResponse.class);
    }
}
