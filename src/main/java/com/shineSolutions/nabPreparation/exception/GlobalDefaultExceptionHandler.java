package com.shineSolutions.nabPreparation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalDefaultExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";


    @ExceptionHandler(value= Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception{
        log.info("in the GlobalDefaultExceptionHandler!");
        log.info(e.getMessage());
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null)
            throw e;
            ModelAndView mav = new ModelAndView();
            mav.addObject("exception",e);
            mav.addObject("url",req.getRequestURL());
            mav.setViewName(DEFAULT_ERROR_VIEW);
            return mav;
        }

}
