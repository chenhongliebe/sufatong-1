package com.legalups.sufatong.common.support;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;



/**
 * @Author : wangyz
 * @Description :
 * @Date :  2017/6/5
 */
@Slf4j
public class HttpClientDealUtil {


    public static String doSSLGet(String url){
        return doGetRequest(url, createSSLClientDefault());
    }
    public static String doGet(String url){
        return doGetRequest(url, createClientDefault());
    }

    private static String doGetRequest(String url, CloseableHttpClient client){
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        String token = TokenUtil.getToken();
        try {
            httpGet.setHeader("Authorization","Bearer "+ token);
            response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200){
                return EntityUtils.toString(response.getEntity(),"utf-8");
            }
            log.error("调用httpsget请求失败，结果：{}",JSON.toJSONString(response.getStatusLine()));
        }catch (Exception e){
            log.error("请求异常，请求地址：{}，token：{}",url,token,e);
        }
        return null;
    }


    /**
     * 创建SSL安全连接
     *
     * @return
     */
    private static CloseableHttpClient createSSLClientDefault(){
        try {
            SSLContext sslContext=new SSLContextBuilder().loadTrustMaterial(
                    null,new TrustStrategy() {
                        //信任所有
                        public boolean isTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {
                            return true;
                        }
                    }).build();
            SSLConnectionSocketFactory sslsf=new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    /**
     * 创建普通连接
     * @return
     */
    private static CloseableHttpClient createClientDefault(){
        return HttpClients.createDefault();
    }
}
