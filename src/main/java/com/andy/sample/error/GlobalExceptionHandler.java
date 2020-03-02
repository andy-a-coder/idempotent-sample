package com.andy.sample.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andy.idempotent.error.IdempotentException;
import com.andy.sample.common.ResponseData;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IdempotentException.class)
    @ResponseBody
    public ResponseEntity<Object> idempotentException(IdempotentException idempotentException){
        log.error("idempotentException:", idempotentException);
        return new ResponseEntity<>(ResponseData.badRequest(idempotentException.getErrorCode(), idempotentException.getErrorMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ResponseEntity<Object> bizException(BizException bizException){
        log.error("bizException:",bizException);
        return new ResponseEntity<>(ResponseData.badRequest(bizException.getErrorCode(), bizException.getErrorMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException runtimeException) {
        log.error("handleRuntimeException:", runtimeException);
        ResponseData<?> responseData = ResponseData.badRequest();
        responseData.setMessage(CommonEnum.SYS_ERROR.getMessage());
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }
    
    /**
     * 用来捕获404，400这种无法到达controller的错误及其他不可预知异常
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> defaultErrorHandler(Exception ex) throws Exception {
        log.error("defaultErrorHandler:", ex);
        ResponseData<?> responseData = ResponseData.badRequest();
        HttpStatus status = null;
        if (ex instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            responseData.setCode(CommonEnum.NO_HANDLER_EXCEPTIONS.getCode());
            responseData.setMessage(CommonEnum.NO_HANDLER_EXCEPTIONS.getMessage());
            status = HttpStatus.NOT_FOUND;
        } else {
            responseData.setCode(CommonEnum.SYS_ERROR.getCode());
            responseData.setMessage(CommonEnum.SYS_ERROR.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(responseData, status);
    }

}