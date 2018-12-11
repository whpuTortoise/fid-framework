package com.tortoise.quake.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


/**
 * 统一的异常处理类。
 * @author wangzhi
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * 错误页
	 */
    public static final String DEFAULT_ERROR_VIEW = "error";

    /**
     * 将Exception对象和请求URL映射到error.html。
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("message", e.getMessage());
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName(DEFAULT_ERROR_VIEW);
//        return mav;
//    }
    
//    /**
//     * 返回JSON格式的异常处理。
//     * @param req
//     * @param e
//     * @return
//     * @throws Exception
//     */
//    @ExceptionHandler(value = MyException.class)
//    @ResponseBody
//    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
//        ErrorInfo<String> r = new ErrorInfo<>();
//        r.setMessage(e.getMessage());
//        r.setCode(ErrorInfo.ERROR);
//        r.setData("Some Data");
//        r.setUrl(req.getRequestURL().toString());
//        return r;
//    }
    
}
