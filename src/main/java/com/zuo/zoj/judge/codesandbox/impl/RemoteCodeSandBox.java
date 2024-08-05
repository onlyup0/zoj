package com.zuo.zoj.judge.codesandbox.impl;

import cn.hutool.json.JSONUtil;
import com.zuo.zoj.common.ErrorCode;
import com.zuo.zoj.exception.BusinessException;
import com.zuo.zoj.judge.codesandbox.CodeSandbox;
import com.zuo.zoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zuo.zoj.judge.codesandbox.model.ExecuteCodeResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RemoteCodeSandBox implements CodeSandbox {
    private static final String AUTH_HEAD="auth";
    //加密口令
    private static String AUTH_SECRET="zzh";
    @Override
    public ExecuteCodeResponse excuteCode(ExecuteCodeRequest requst) {
        ExecuteCodeResponse executeCodeResponse = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建HttpPost对象，设置URL
            HttpPost post = new HttpPost("http://192.168.239.131:8090/executeCode");
            post.setHeader("Content-Type", "application/json");
            post.setHeader(AUTH_HEAD, DigestUtils.md5Hex(AUTH_SECRET));

            // 设置请求体
            String jsonInputString = JSONUtil.toJsonStr(requst);
            post.setEntity(new StringEntity(jsonInputString));
            // 执行请求并获取响应
            try (CloseableHttpResponse response = httpClient.execute(post)) {
                // 打印响应状态码
                System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
                String responseString = EntityUtils.toString(response.getEntity());
                // 打印响应体
               executeCodeResponse = JSONUtil.toBean(responseString, ExecuteCodeResponse.class);
                System.out.println(executeCodeResponse);
                System.out.println("Response Body : " + executeCodeResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(executeCodeResponse==null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"代码沙箱无返回结果");
        }
        return executeCodeResponse;
    }
}
