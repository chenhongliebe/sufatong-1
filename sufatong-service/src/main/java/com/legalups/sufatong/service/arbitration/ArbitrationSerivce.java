package com.legalups.sufatong.service.arbitration;

import com.legalups.sufatong.common.enums.ApiEnum;
import com.legalups.sufatong.common.exception.BusinessException;
import com.legalups.sufatong.common.result.RespCodeEnum;
import com.legalups.sufatong.common.result.WebResult;
import com.legalups.sufatong.common.support.HttpClientDealUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author : wangyz
 * @Description : 仲裁接口调用
 * @Date :  2017/6/5
 */
@Slf4j
@Service
public class ArbitrationSerivce {

    private final String baseUrl = "https://14.23.88.138:7777/api//";


    /**
     * demo请求
     *
     * @param param
     * @return
     */
    public WebResult<String> getDemo(String param) throws BusinessException {
        try {
            String url = baseUrl + ApiEnum.DEMO.getUrl() + "?name=" + param;
            return WebResult.success(HttpClientDealUtil.doSSLGet(url));
        } catch (Exception e) {
            log.error("调用demo接口异常", e);
            throw new BusinessException(RespCodeEnum.APIGET_ERROR.getCode(), "调用demo接口异常");
        }

    }


    /**
     * 获取全局参数
     *
     * @param param
     * @return
     */
    public WebResult<String> getParam(String param) throws BusinessException {
        try {
            String url = baseUrl + ApiEnum.PARAM.getUrl() + "?type=" + param;
            return WebResult.success(HttpClientDealUtil.doSSLGet(url));
        } catch (Exception e) {
            log.error("调用获取全局参数接口异常", e);
            throw new BusinessException(RespCodeEnum.APIGET_ERROR.getCode(), "调用获取全局参数接口异常");
        }

    }


    /**
     * 获取仲裁机构
     *
     * @param param
     * @return
     */
    public WebResult<String> getOrgan(String param) throws BusinessException {
        try {
            String url = baseUrl + ApiEnum.ORGAN.getUrl();
            return WebResult.success(HttpClientDealUtil.doSSLGet(url));
        } catch (Exception e) {
            log.error("调用获取仲裁机构接口异常", e);
            throw new BusinessException(RespCodeEnum.APIGET_ERROR.getCode(), "调用获取仲裁机构接口异常");
        }
    }
}
