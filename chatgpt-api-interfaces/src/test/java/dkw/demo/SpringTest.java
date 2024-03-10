package dkw.demo;

import com.alibaba.fastjson2.JSON;
import dkw.demo.aggregates.Unanswer_Question;
import dkw.demo.res.AccessToken_resData;
import dkw.demo.vo.Topics;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringTest {

    @Value("${chat_api.cookie}")
    String cookie;

    @Value("${chat_api.groupId}")
    String groupId;

    @Value("${chat_api.client_id}")
    String client_id;

    @Value("${chat_api.client_secret}")
    String client_secret;

    @Resource
    private IZsxq_api zsxq_api;
    @Resource
    private ILlama_api llama_api;
    @Test
    public void test_api() throws IOException {
        Unanswer_Question unansweredTopic = zsxq_api.getUnansweredTopicId(cookie, groupId);
        List<Topics> list = unansweredTopic.getResp_data().getTopics();
        if(list==null || list.isEmpty()){
            log.info("未检索到问题");
            return;
        }
        Topics topic = list.get(0);
        log.info(topic.getQuestion().getText());
        String accessToken = llama_api.getAccessToken(client_id, client_secret);
        String answer = llama_api.question(accessToken, topic.getQuestion().getText());

        if(zsxq_api.answer(cookie, groupId, topic.getTopic_id(), answer, true)){
            log.info("true");
        }else {
            log.info("false");
        }
    }

    @Test
    public void test_Llama() throws IOException {
        llama_api.getAccessToken(client_id, client_secret);


    }

}
