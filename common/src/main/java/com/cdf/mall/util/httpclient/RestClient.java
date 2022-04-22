package com.cdf.mall.util.httpclient;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * zwg
 */
public class RestClient {

    private static RestTemplate restTemplate;

    private static Logger logger = LoggerFactory.getLogger(RestClient.class);

    static {
        PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(1, TimeUnit.SECONDS);
        // 总连接数
        pollingConnectionManager.setMaxTotal(1000);
        // 同路由的并发数
        pollingConnectionManager.setDefaultMaxPerRoute(1000);

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(pollingConnectionManager);
        // 重试次数，默认是3次，没有开启
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(1, true));
        // 保持长连接配置，需要在头添加Keep-Alive
        httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
        HttpClient httpClient = httpClientBuilder.build();

        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        // 连接超时
        clientHttpRequestFactory.setConnectTimeout(1000);
        // 数据读取超时时间，即SocketTimeout
        clientHttpRequestFactory.setReadTimeout(5000);
        // 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
        clientHttpRequestFactory.setConnectionRequestTimeout(200);
        // 缓冲请求数据，默认值是true。通过POST或者PUT大量发送数据时，建议将此属性更改为false，以免耗尽内存。
        clientHttpRequestFactory.setBufferRequestBody(false);
        restTemplate = new RestTemplate(clientHttpRequestFactory);

    }

    public static <T, V> CallResult post(String url, T condition, ParameterizedTypeReference<CallResult<V>> typeRef) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(condition, headers);

        ResponseEntity<CallResult<V>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, typeRef);
        return responseEntity.getBody();
    }

    public static <V> CallResult get(String url, ParameterizedTypeReference<CallResult<V>> typeRef) {
        ResponseEntity<CallResult<V>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, typeRef);
        return responseEntity.getBody();
    }

    public static <T,V> V postForObject(String url,HttpEntity<T> entity,Class<V> responseType) {
        return restTemplate.postForObject(url, entity, responseType);
    }

    public static <T> ResponseEntity<T> exchange(String url, HttpMethod method,
                                                 @Nullable HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables){
        return restTemplate.exchange(url, method,requestEntity,responseType,uriVariables);
    }


    public static <T> ResponseEntity<T> exchange(String url, HttpMethod method,
                                                 @Nullable HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables)
            throws RestClientException {
        return restTemplate.exchange(url, method,requestEntity,responseType,uriVariables);
    }


    public static <T, V> V postForObject(String url, T object, Class<V> responseType) {
        HttpHeaders headers = new HttpHeaders();

        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity entity = new HttpEntity(object, headers);
        return restTemplate.postForObject(url, entity, responseType, new Object[0]);

    }

    /**
     * @description: get请求入参为实体
     * @param: url   请求地址
     * @param: object 请求参数（实体）
     * @param: formEntity 请求头
     * @param: responseType 返回值类型
     * @return: V
     */
    public static <T, V> V getForObject(String url, T object, HttpEntity formEntity, Class<V> responseType) {

        StringBuilder stringBuffer = new StringBuilder(url);
        if(object != null){
            logger.info("入参 = {}", object);
            stringBuffer.append("?");
            // 遍历实体
            Class cls = object.getClass();
            Field[] fields = cls.getDeclaredFields();
            try {
                for (Field f : fields){
                    f.setAccessible(true);
                    if (f.get(object) != null) {
                        // 拼接get请求
                        stringBuffer.append(f.getName() + "=" + f.get(object)).append("&");
                    }
                }
            } catch (IllegalAccessException e) {
                logger.error("遍历实体报错", e);
            }
            // 处理最后一个&
            url = stringBuffer.substring(0, stringBuffer.length() - 1);
        }
        logger.info("url = {}", url);
        ResponseEntity<V> responseEntity = restTemplate.exchange(url, HttpMethod.GET, formEntity, responseType);
        return responseEntity.getBody();
    }

    /**
     * 会员购 通用调用（加验签）
     * @param url
     * @param
     * @param responseType
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> V postMPForObject(String url, HttpEntity formEntity, Class<V> responseType) {

        logger.info("url请求: " + url);
//        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
//        headers.setContentType(type);
//        HttpEntity entity = new HttpEntity(postParameters, headers);
        return restTemplate.postForObject(url, formEntity, responseType, new Object[0]);

    }

    /**
     * 会员购 通用调用（加验签）
     * @param url
     * @param postParameters
     * @param responseType
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> V postMPForObject(String url, MultiValueMap postParameters, Class<V> responseType) {

        logger.info("url请求: " + url);
        logger.info("参数: " + postParameters);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        headers.setContentType(type);
        HttpEntity entity = new HttpEntity(postParameters, headers);
        return restTemplate.postForObject(url, entity, responseType, new Object[0]);

    }


    /**
     * @description: get请求入参类型为Map
     * @author: zlg
     * @param: url 请求地址
     * @param: map 请求参数（Map）
     * @param: formEntity 请求头
     * @param: responseType 返回值类型
     * @return: V
     */
    public static <V> V getForMap(String url, Map map, HttpEntity formEntity, Class<V> responseType) {
        StringBuffer stringBuffer = new StringBuffer(url);
        Iterator iterator = map.entrySet().iterator();
        if (iterator.hasNext()) {
            stringBuffer.append("?");
            Object element;
            while (iterator.hasNext()) {
                element = iterator.next();
                Map.Entry<String, Object> entry = (Map.Entry) element;
                //过滤value为null，value为null时进行拼接字符串会变成 "null"字符串
                if (entry.getValue() != null) {
                    stringBuffer.append(element).append("&");
                }
                url = stringBuffer.substring(0, stringBuffer.length() - 1);
            }
        }
        logger.info("url请求:" + url);
        ResponseEntity<V> responseEntity = restTemplate.exchange(url, HttpMethod.GET, formEntity, responseType);
        return responseEntity.getBody();
    }

    public static <V> V putForMap(String url, Map map, HttpEntity formEntity, Class<V> responseType) {
        StringBuffer stringBuffer = new StringBuffer(url);
        Iterator iterator = map.entrySet().iterator();
        if (iterator.hasNext()) {
            stringBuffer.append("?");
            Object element;
            while (iterator.hasNext()) {
                element = iterator.next();
                Map.Entry<String, Object> entry = (Map.Entry) element;
                //过滤value为null，value为null时进行拼接字符串会变成 "null"字符串
                if (entry.getValue() != null) {
                    stringBuffer.append(element).append("&");
                }
                url = stringBuffer.substring(0, stringBuffer.length() - 1);
            }
        }
        logger.info("url请求:" + url);
        ResponseEntity<V> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, formEntity, responseType);
        return responseEntity.getBody();
    }

    public static <V> V postForMap(String url, Map map, HttpEntity formEntity, Class<V> responseType) {
        StringBuffer stringBuffer = new StringBuffer(url);
        Iterator iterator = map.entrySet().iterator();
        if (iterator.hasNext()) {
            stringBuffer.append("?");
            Object element;
            while (iterator.hasNext()) {
                element = iterator.next();
                Map.Entry<String, Object> entry = (Map.Entry) element;
                //过滤value为null，value为null时进行拼接字符串会变成 "null"字符串
                if (entry.getValue() != null) {
                    stringBuffer.append(element).append("&");
                }
                url = stringBuffer.substring(0, stringBuffer.length() - 1);
            }
        }
        logger.info("url请求:" + url);
        ResponseEntity<V> responseEntity = restTemplate.exchange(url, HttpMethod.POST, formEntity, responseType);
        return responseEntity.getBody();
    }

    public static <V> V deleteForMap(String url, Map map, HttpEntity formEntity, Class<V> responseType) {
        StringBuffer stringBuffer = new StringBuffer(url);
        Iterator iterator = map.entrySet().iterator();
        if (iterator.hasNext()) {
            stringBuffer.append("?");
            Object element;
            while (iterator.hasNext()) {
                element = iterator.next();
                Map.Entry<String, Object> entry = (Map.Entry) element;
                //过滤value为null，value为null时进行拼接字符串会变成 "null"字符串
                if (entry.getValue() != null) {
                    stringBuffer.append(element).append("&");
                }
                url = stringBuffer.substring(0, stringBuffer.length() - 1);
            }
        }
        System.out.println("url请求:" + url);
        ResponseEntity<V> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, formEntity, responseType);
        return responseEntity.getBody();
    }


    public static <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return restTemplate.getForObject(url,responseType,uriVariables);
    }

    public static void main(String[] args) {
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream("C://opt/logs/chgpw-admin.log.2018-04-26");
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

}
