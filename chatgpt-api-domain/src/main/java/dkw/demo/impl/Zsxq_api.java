package dkw.demo.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dkw.demo.IZsxq_api;
import dkw.demo.aggregates.Unanswer_Question;
import dkw.demo.req.AnswerReq;
import dkw.demo.req.ReqData;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
    public boolean answer(String cookie, String groupId, String topicId, String text, boolean silenced) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/"+topicId+"/answer");

        post.addHeader("Cookie", cookie);
        post.addHeader("Content-Type", "application/json; charset=UTF-8");
        post.addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");

//        String payload = "{\n" +
//                "  \"req_data\": {\n" +
//                "    \"text\": \"我也不知道\\n\",\n" +
//                "    \"image_ids\": [],\n" +
//                "    \"silenced\": true\n" +
//                "  }\n" +
//                "}";

        AnswerReq answerReq = new AnswerReq(new ReqData(text, silenced));
        String answer = JSONObject.toJSONString(answerReq);
        log.info(answer);
        StringEntity stringEntity = new StringEntity(answer, ContentType.create("text/json", "UTF-8"));

        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            log.info(EntityUtils.toString(response.getEntity()));
            return true;
        }else{
            log.info(Integer.toString(response.getStatusLine().getStatusCode()));
            return false;
        }

    }
}


