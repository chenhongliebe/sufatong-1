/**
 *
 * Copyright From 2010, 微贷（杭州）金融信息服务有限公司. All rights reserved.
 * 
 * AnnotationControllerExceptionResolver.java
 *
 */
package com.legalups.sufatong.common.support.controller;

import com.alibaba.fastjson.JSON;
import com.legalups.sufatong.common.exception.BusinessException;
import com.legalups.sufatong.common.result.RespCodeEnum;
import com.legalups.sufatong.common.result.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * <p>
 * 此类继承 Spring 自身 {@linkplain ExceptionHandlerExceptionResolver} 类，将统一处理
 * Controller 中抛出的异常（统一返回json格式字串）
 * 
 * @author 	刘世茂 ( 492788802@qq.com )
 * @date 	2017年4月27日
 * @version 1.0.0
 */
@Component
public class ControllerExceptionResolver extends ExceptionHandlerExceptionResolver {
	
	private static final Logger ownLogger = LoggerFactory.getLogger( ControllerExceptionResolver.class );
	
	/**
	 * <p>
	 * 覆盖父类 异常处理 方式
	 * 
	 * @author 	刘世茂 ( 492788802@qq.com )
	 * @date 	2017年4月27日
	 */
	@Override
	protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request,
                                                           HttpServletResponse response, HandlerMethod handlerMethod, Exception exception) {
		
		Object result = null;
		try{
			// handlerMethod 判空并且得到原始方法
			if( handlerMethod != null ){
				Method method = handlerMethod.getMethod();
				
				//获取路径
				String path = request.getServletPath();
				if( StringUtils.isEmpty(path) ) path="";
				if( !path.startsWith( "/" ) ) path = "/"+path;
				
				//获取相应 logger并记录日志 
				Logger logger = LoggerFactory.getLogger( method.getDeclaringClass() );
				logger.error( "==> 异常处理[" + path +"]", exception );
				
				//组装异常响应内容
				result = WebResult.ret( RespCodeEnum.SYS_ERROR );
//				System.out.println( exception.getClass() );
				if( exception.getClass() == BusinessException.class ){
					BusinessException bizEx = (BusinessException) exception;
					result = WebResult.ret( bizEx.getCode(), bizEx.getMessage() );
				}
				
				//回写至 Response
				response.getWriter().write( JSON.toJSONString( result ) );
			}
		}catch( Exception e ){
			//本身的异常需要捕捉掉
			ownLogger.error( "Controller 异常捕获失败", e );
			try{
				result = WebResult.ret( RespCodeEnum.SYS_ERROR );
				response.getWriter().write( JSON.toJSONString( result ) );
			}catch( Exception ex ){
				ownLogger.error( "IO错误", ex );
			}
		}
		
		//异常已经处理，其它 Resolver 不需要再做处理
		//isEmpty==true 不会被渲染
		result = new ModelAndView();
		return (ModelAndView) result;
	}
}
