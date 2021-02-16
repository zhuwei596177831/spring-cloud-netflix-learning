package com.example.zuul.learning;

import com.alibaba.fastjson.JSON;
import com.example.base.api.entity.ResultCode;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author 朱伟伟
 * @date 2021-02-16 15:03:58
 * @description 我们在项目中使用Spring cloud zuul的时候，有一种这样的需求，就是当我们的zuul进行路由分发时，
 * 如果后端服务没有启动，或者调用超时，这时候我们希望Zuul提供一种降级功能，而不是将异常暴露出来。
 */
@Component
public class MyFallbackProvider implements FallbackProvider {
    /**
     * @description: return * 或 null fallback所有下游服务
     **/
    @Override
    public String getRoute() {
//        return "hystrix-goods-9095";
//        return "orders";
//        return "*";
        return null;
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(JSON.toJSONString(ResultCode.COMMON.getResult("zuul route fallback")).getBytes(StandardCharsets.UTF_8));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };

    }
}
