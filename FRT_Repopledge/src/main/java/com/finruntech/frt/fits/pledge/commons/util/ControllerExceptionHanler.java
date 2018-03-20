package com.finruntech.frt.fits.pledge.commons.util;

import com.finruntech.frt.fits.pledge.commons.util.exception.FitsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ExceptionHandler utils
 * Created by yinan.zhang on 2017/5/31.
 */
@ControllerAdvice
public class ControllerExceptionHanler {
    private static Logger logger = LoggerFactory.getLogger(ControllerExceptionHanler.class);

    @ExceptionHandler(value=FitsException.class)
    public ResponseEntity<String> handleServiceException(FitsException exception, HttpServletRequest request) {
        Map<String,String> map = new HashMap<>();
        map.put(exception.getExceptionType().getExceptionCode(),exception.getMessage());
        logger.error(exception.getMessage());
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @ExceptionHandler(value=Exception.class)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception exception, HttpServletRequest request) throws Exception {
        logger.error("系统异常!", exception);
        if(exception instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException c = (MethodArgumentNotValidException) exception;
            List<ObjectError> errors =c.getBindingResult().getAllErrors();
            StringBuffer errorMsg=new StringBuffer();
            errors.stream().forEach(x -> errorMsg.append(x.getDefaultMessage()).append("</br>"));
            Map<String,String> map = new HashMap<>();
            map.put("errorMsg",errorMsg.toString());
            return new ResponseEntity(map, HttpStatus.OK);
        }
        return new ResponseEntity<String>("操作失败，请联系管理员！", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
