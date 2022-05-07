package com.cdf.mall.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

/**
 * @Description 跨域请求头重复处理过滤器
 * @Author hanyaguang
 * @Date 2021/12/30 9:24
 * @Version 1.0
 */
public class CorsResponseHeaderFilter implements GlobalFilter, Ordered {

 private Logger logger = LoggerFactory.getLogger(CorsResponseHeaderFilter.class);
    @Override
    @SuppressWarnings("serial")
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.defer(() -> {
            exchange.getResponse().getHeaders().entrySet().stream()
                    .filter(kv -> (kv.getValue() != null && kv.getValue().size() > 1))
                    .filter(kv -> (kv.getKey().equals(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)
                            || kv.getKey().equals(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS)))
                    .forEach(kv ->
                    {
                        kv.setValue(new ArrayList<String>() {{
                            add(kv.getValue().get(0));
                        }});
                    });
            logger.info("-------------请求体：{}",exchange.getRequest().getHeaders().toString());
            logger.info("-------------响应体：{}",exchange.getResponse().getHeaders().toString());
            return chain.filter(exchange);
        }));
    }

    @Override
    public int getOrder() {
        // 指定此过滤器位于NettyWriteResponseFilter之后
        // 即待处理完响应体后接着处理响应头
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER + 1;
    }

}
