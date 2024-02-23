package dkw.demo;

import dkw.demo.aggregates.Unanswer_Question;
import dkw.demo.vo.Topics;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringTest {

    @Value("${chat_api.cookie}")
    String cookie;

    @Value("${chat_api.groupId}")
    String groupId;

    @Resource
    private IZsxq_api zsxq_api;
    @Test
    public void test_api() throws IOException {
        Unanswer_Question unansweredTopicId = zsxq_api.getUnansweredTopicId(cookie, groupId);
        for (Topics topic: unansweredTopicId.getResp_data().getTopics()){
            log.info(topic.getQuestion().getText());
        }
    }

}
