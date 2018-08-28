package com.xingdong.util.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * HTTP工具类
 *
 * @author lixiangyang
 *
 */
public class HttpClientUtils {

    private static Log log = LogFactory.getLog(HttpClientUtils.class);

    private static MultiThreadedHttpConnectionManager connectionManager = null;
    // 连接超时
    private static int connectionTimeOut = 25000;
    // Socket超时
    private static int socketTimeOut = 25000;
    // 每个主机最大连接数
    private static int maxConnectionPerHost = 20;
    // 总最大连接数
    private static int maxTotalConnections = 20;

    private static HttpClient client;

    static{
        connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(connectionTimeOut);
        connectionManager.getParams().setSoTimeout(socketTimeOut);
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);
        connectionManager.getParams().setMaxTotalConnections(maxTotalConnections);
        client = new HttpClient(connectionManager);
    }

    /**
     * POST方式提交数据
     * @param url
     *          待请求的URL
     * @param params
     *          要提交的数据
     * @return
     *          响应结果
     */
    public static String URLPost(String url, Map<String, String> params){
        return URLPost(url, params, StringConfig.URL_PARAM_DECODECHARSET_UTF8);
    }

    /**
     * GET方式提交数据
     * @param url
     *          待请求的URL
     * @param params
     *          要提交的数据
     * @return
     *          响应结果
     */
    public static String URLGet(String url, Map<String, String> params){
        return URLGet(url, params, StringConfig.URL_PARAM_DECODECHARSET_UTF8);
    }

    /**
     * POST方式提交数据
     * @param url
     *          待请求的URL
     * @param params
     *          要提交的数据
     * @param enc
     *          编码
     * @return
     *          响应结果
     */
    public static String URLPost(String url, Map<String, String> params, String enc){

        String response = StringConfig.EMPTY;
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(url);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);
            //将表单的值放入postMethod中
            Set<String> keySet = params.keySet();
            for(String key : keySet){
                String value = params.get(key);
                postMethod.addParameter(key, value);
            }
            //执行postMethod
            int statusCode = client.executeMethod(postMethod);
            if(statusCode == HttpStatus.SC_OK) {
                response = postMethod.getResponseBodyAsString();
            }else{
                log.error("响应状态码 = " + postMethod.getStatusCode());
            }
        }catch(HttpException e){
            log.error("发生异常，可能是协议不对或者返回的内容有问题", e);
            e.printStackTrace();
        }catch(IOException e){
            log.error("发生网络异常", e);
            e.printStackTrace();
        }finally{
            if(postMethod != null){
                postMethod.releaseConnection();
            }
        }

        return response;
    }

    /**
     * GET方式提交数据
     * @param url
     *          待请求的URL
     * @param params
     *          要提交的数据
     * @param enc
     *          编码
     * @return
     *          响应结果
     */
    public static String URLGet(String url, Map<String, String> params, String enc){

        String response = StringConfig.EMPTY;
        GetMethod getMethod = null;
        StringBuffer strtTotalURL = new StringBuffer(StringConfig.EMPTY);

        if(strtTotalURL.indexOf("?") == -1) {
            strtTotalURL.append(url).append("?").append(getUrl(params, enc));
        } else {
            strtTotalURL.append(url).append("&").append(getUrl(params, enc));
        }
        log.debug("GET请求URL = \n" + strtTotalURL.toString());

        try {
            getMethod = new GetMethod(strtTotalURL.toString());
            getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);
            //执行getMethod
            int statusCode = client.executeMethod(getMethod);
            if(statusCode == HttpStatus.SC_OK) {
                response = getMethod.getResponseBodyAsString();
            }else{
                log.debug("响应状态码 = " + getMethod.getStatusCode());
            }
        }catch(HttpException e){
            log.error("发生异常，可能是协议不对或者返回的内容有问题", e);
            e.printStackTrace();
        }catch(IOException e){
            log.error("发生网络异常", e);
            e.printStackTrace();
        }finally{
            if(getMethod != null){
                getMethod.releaseConnection();
            }
        }

        return response;
    }

    /**
     * 据Map生成URL字符串
     * @param map
     *          Map
     * @param valueEnc
     *          URL编码
     * @return
     *          URL
     */
    private static String getUrl(Map<String, String> map, String valueEnc) {

        if (null == map || map.keySet().size() == 0) {
            return (StringConfig.EMPTY);
        }
        StringBuffer url = new StringBuffer();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            if (map.containsKey(key)) {
                String val = map.get(key);
                String str = val != null ? val : StringConfig.EMPTY;
                try {
                    str = URLEncoder.encode(str, valueEnc);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                url.append(key).append("=").append(str).append(StringConfig.URL_PARAM_CONNECT_FLAG);
            }
        }
        String strURL;
        strURL = url.toString();
        if (StringConfig.URL_PARAM_CONNECT_FLAG.equals(StringConfig.EMPTY + strURL.charAt(strURL.length() - 1))) {
            strURL = strURL.substring(0, strURL.length() - 1);
        }

        return (strURL);
    }
}