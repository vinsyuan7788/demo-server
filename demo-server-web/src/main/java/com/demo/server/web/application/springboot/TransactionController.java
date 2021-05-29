package com.demo.server.web.application.springboot;

import com.demo.server.biz.application.springboot.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vince Yuan
 * @date 03/01/2021
 */
@Api(value = "应用-SpringBoot-事务处理", tags = "应用-SpringBoot-事务处理")
@Slf4j
@RestController
@RequestMapping("/spring/boot/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @ApiOperation(value = "触发回滚", notes = "通过在事务中抛出异常触发回滚（数据是否回滚需到查看数据库中相应的表）")
    @PostMapping("/triggerRollback")
    public void triggerRollback() {
        transactionService.triggerRollback();
    }
}
