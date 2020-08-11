package com.base.frame.filter;

import com.alibaba.fastjson.JSON;
import com.base.frame.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 *  请求过滤器
 *
 * */
@Component
public class RequestFilter implements Filter {

    private final static Logger logger = LoggerFactory.getLogger(RequestFilter.class);


    @Autowired
    BaseConfig baseConfig;

    /**
     * 配置文件在resources下的根节点路径
     * */
    private static final String configRootPath = "/config/filter";

    /**
     *  不进行过滤的路由列表
     *
     *  <p>
     *      如: /test/main ,   /policy/query
     *      这种全路径的路由
     *  </p>
     *
     * */
    private static List<String> ignoreList = new ArrayList<>();

    /**
     *  不需要过滤的根路由列表
     *
     *  <p>
     *      如: /test/main ,/test/index  , /policy/query , /policy/add
     *      只需要配置: /test , /policy就可以对其下面的所有路由进行过滤
     *
     *      如: 访问路由为:  /test/main
     *      我们配置的根路由为: /test2 , 则不会通过.
     *      但如果访问的路由为/test2/**，则会通过
     *
     *  </p>
     *
     * */
    private static List<String> ignoreRootList = new ArrayList<>();

    static{
        initializeConfig(ignoreList,configRootPath + "/ignoreRoutes");
        initializeConfig(ignoreRootList,configRootPath + "/ignoreRootRoutes");
    }

    private static void initializeConfig(List<String> list, String path){
        String content = FileUtil.readerResourcesDirectory(path);
        if(VerifyUtil.stringTrimIsEmpty(content)){
            return;
        }
        list.addAll(Arrays.asList(content.split(",")));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            doFilter((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, filterChain);
        } catch (Exception e) {
            logger.error("error: " + e.getMessage());
            requestFailed((HttpServletResponse) servletResponse, e.getMessage());
        }
    }

    /**
     * 过滤失败时，做对应的处理
     *
     * <p>
     *     目前给出两种基础方案
     *     1. 直接报404，
     *     2. 给出错误提示
     * </p>
     * */
    private void requestFailed(HttpServletResponse servletResponse, String errorMessage) {
        servletResponse.setStatus(404);
        /*try (ServletOutputStream out = servletResponse.getOutputStream()){
            servletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
            out.write(MyResult.error(errorMessage).toString().getBytes("UTF-8"));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws Exception {
        if(request.getMethod().equals(RequestMethod.OPTIONS.toString())){
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("token");
        String requestPath = request.getServletPath();

        // 如果当前请求路径是在配置列表中存在的, 则不做过滤处理
        if(ignoreList.contains(requestPath) || ignoreRootList.stream().anyMatch(x-> requestPath.contains(x))){
            filterChain.doFilter(request, response);
            return;
        }

        // token过滤
        tokenFilter(token);

        // 其他过滤业务.........

        // 过滤业务通过后执行请求
        filterChain.doFilter(request, response);
    }

    /**
     * token过滤
     *
     * */
    private void tokenFilter(String token) throws Exception{
        if(VerifyUtil.stringTrimIsEmpty(token)){
            throw new Exception("token不能为空");
        }

        String data = AESUtil.decrypt(token, baseConfig.getAesKey(), 0);
        if(VerifyUtil.stringTrimIsEmpty(data)){
            throw new Exception("token不正确, token:" + token);
        }

        if(!baseConfig.isCheckTokenTime()){
            return;
        }

        Long time = (Long) JSON.parseObject(data).get("time");

        if((System.currentTimeMillis() - time) >= DateUtil.DAY_TIMESTAMP * baseConfig.getTokenTimeLimit()){
            throw new Exception("token已过期, 解析后的数据:" + data);
        }

    }



}
