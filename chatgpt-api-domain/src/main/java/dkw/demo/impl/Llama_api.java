package dkw.demo.impl;

import com.alibaba.fastjson2.JSON;
import dkw.demo.ILlama_api;
import dkw.demo.ai.req.ai_reqdata;
import dkw.demo.ai.req.single_Message;
import dkw.demo.ai.resp.resp_data;
import dkw.demo.res.AccessToken_resData;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class Llama_api implements ILlama_api {

    @Override
    public String question(String accessToken, String question) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/qianfan_chinese_llama_2_13b?access_token="+accessToken);
        post.addHeader("Content-Type", "application/json");

        //将实体类处理为json字符串
        List<single_Message> list = new ArrayList<>();
        list.add(new single_Message("user", question));
        ai_reqdata req = new ai_reqdata(list);
        String jsonString = JSON.toJSONString(req);

//        log.info(jsonString);
        //将字符串加入实体
        StringEntity se = new StringEntity(jsonString);
        post.setEntity(se);

        CloseableHttpResponse execute = httpClient.execute(post);

        if (execute.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String string = EntityUtils.toString(execute.getEntity());
            resp_data resp_data = JSON.parseObject(string, dkw.demo.ai.resp.resp_data.class);
            return resp_data.getResult();
        } else {
            throw new RuntimeException("error code is"+execute.getStatusLine().getStatusCode());
        }



    }



    @Override
    public String getAccessToken(String client_id, String client_secret) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();


        String url = "https://aip.baidubce.com/oauth/2.0/token?client_id="+client_id+"&client_secret="+client_secret+"&grant_type=client_credentials";
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-Type","application/json");
        post.addHeader("Accept", "application/json");

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String string = EntityUtils.toString(response.getEntity());
            AccessToken_resData accessTokenResData = JSON.parseObject(string, AccessToken_resData.class);
            return accessTokenResData.getAccess_token();
        }else{
            log.info("未获取到accessToken，状态码:{}", response.getStatusLine().getStatusCode());
            return null;
        }



    }


}
