package com.mine.cni.config;

import com.mine.cni.domain.base.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    public JsonResult handler(HttpServletResponse response, Exception e) {
        logger.error("统一异常处理", e);
        response.setStatus(HttpStatus.OK.value());
        return new JsonResult(false, "程序异常", e.toString());
    }
}
