package com.demo.server.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.demo.base.common.exception.base.BaseException;
import com.demo.base.common.utils.IPUtils;
import com.demo.base.common.utils.LogUtils;
import com.demo.base.common.utils.bean.ParametersToLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 *  This class is used for rest template utility
 *
 * @author Vince Yuan
 * @date 04/11/2021
 */
@Slf4j
public class RestTemplateUtils {

    /**
     *  HTTP protocol
     */
    public static final String HTTP_PROTOCOL = "http://";

    /**
     *  URL separator
     */
    private static final String URL_SEPARATOR = "/";

    /**
     *  Privatize the constructor
     */
    private RestTemplateUtils() { }

    /**
     *  Get rest template
     *
     * @return
     */
    public static RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .build();
    }

    /**
     *  Get URL
     *
     * @param protocol
     * @param restfulAPI
     * @param environment
     * @return
     */
    public static String getURL(String protocol, String restfulAPI, Environment environment) {
        if (restfulAPI != null) {
            // Get necessary information for URL
            String ip = IPUtils.getLocalHostAddress();
            String port = environment.getProperty("server.port");
            String contextPath = environment.getProperty("server.context-path");
            String servletPath = environment.getProperty("server.servlet-path");
            // Concatenate URL
            StringBuffer urlBuffer = new StringBuffer();
            urlBuffer.append(protocol).append(ip).append(":").append(port);
            if (contextPath != null && !contextPath.equals("")) {
                urlBuffer.append(contextPath);
            }
            if (servletPath != null && !contextPath.equals("")) {
                urlBuffer.append(servletPath);
            }
            if (!restfulAPI.startsWith(URL_SEPARATOR)) {
                urlBuffer.append(URL_SEPARATOR);
            }
            urlBuffer.append(restfulAPI);
            String url = urlBuffer.toString();
            // Return URL
            log.info(LogUtils.getLogMessage("getURL", new ParametersToLog().addParameter("URL", url)));
            return url;
        } else {
            throw new BaseException("Restful API to request is not found");
        }
    }

    /**
     *  Get JSON request
     *
     * @return
     */
    public static HttpEntity<String> getJSONRequest(JSONObject parameters) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString());
        if (parameters == null) {
            parameters = new JSONObject();
        }
        return new HttpEntity<>(parameters.toString(), headers);
    }

    /**
     *  Get form request
     *
     * @return
     */
    public static HttpEntity<MultiValueMap<String, String>> getFormRequest(MultiValueMap<String, String> parameters) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        if (parameters == null) {
            parameters = new LinkedMultiValueMap<>();
        }
        return new HttpEntity<>(parameters, headers);
    }
}
