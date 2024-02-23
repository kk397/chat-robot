package dkw.demo.Zsxq_api_impl;

import com.alibaba.fastjson.JSON;
import dkw.demo.IZsxq_api;
import dkw.demo.aggregates.Unanswer_Question;
import dkw.demo.vo.Topics;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpClient;
@Slf4j
@Service
public class Zsxq_api implements IZsxq_api {

    @Override
    public Unanswer_Question getUnansweredTopicId(String cookie, String groupId) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/"+groupId+"/topics?scope=unanswered_questions&count=20");
        get.addHeader("cookie",cookie);
        get.addHeader("Content-Type", "application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String string = EntityUtils.toString(response.getEntity());
            log.info(string);
            Unanswer_Question unanswer_question = JSON.parseObject(string, Unanswer_Question.class);

            return unanswer_question;

        }else{
            throw new RuntimeException("error code is"+response.getStatusLine().getStatusCode());
        }
    }

    @Override
    public boolean answer(String cookie, String groupId, String topicId, String text, boolean silenced) {
        return false;
    }
}

