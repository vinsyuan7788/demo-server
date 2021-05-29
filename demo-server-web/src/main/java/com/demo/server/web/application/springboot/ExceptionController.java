package com.demo.server.web.application.springboot;

import com.demo.base.common.exception.base.BaseException;
import com.demo.base.common.exception.bean.BusinessException;
import com.demo.base.common.exception.bean.DAOException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vince Yuan
 * @date 02/02/2021
 */
@Api(value = "应用-SpringBoot-异常处理", tags = "应用-SpringBoot-异常处理")
@Slf4j
@RestController
@RequestMapping("/spring/boot/exception")
public class ExceptionController {

    @ApiOperation(value = "触发异常", notes = "抛出Java异常（供SpringBoot全局异常处理器处理）")
    @PostMapping("/triggerException")
    public void triggerException() throws Exception {
        throw new Exception("This is the exception triggered manually");
    }

    @ApiOperation(value = "触发基础异常", notes = "抛出定制的基础异常（供SpringBoot全局异常处理器处理）")
    @PostMapping("/triggerBaseException")
    public void triggerBaseException() throws BaseException {
        throw new BaseException("This is the base exception triggered manually");
    }

    @ApiOperation(value = "触发业务异常", notes = "抛出定制的业务异常（供SpringBoot全局异常处理器处理）")
    @PostMapping("/triggerBusinessException")
    public void triggerBusinessException() throws BusinessException {
        throw new BusinessException("This is the business exception triggered manually");
    }

    @ApiOperation(value = "触发DAO异常", notes = "抛出定制的DAO异常（供SpringBoot全局异常处理器处理）")
    @PostMapping("/triggerDAOException")
    public void triggerDAOException() throws DAOException {
        throw new DAOException("This is the DAO exception triggered manually");
    }
}
