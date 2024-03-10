package dkw.demo;

import dkw.demo.aggregates.Unanswer_Question;
import dkw.demo.vo.Topics;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.List;

@EnableScheduling
@Configuration
@Slf4j
public class chatbot_schedule {

    @Value("${chat_api.cookie}")
    private String cookie;

    @Value("${chat_api.groupId}")
    private String groupId;

    @Value("${chat_api.client_id}")
    String client_id;

    @Value("${chat_api.client_secret}")
    String client_secret;

    @Resource
    private ILlama_api llama_api;

    @Resource
    private IZsxq_api zsxq_api;

    @Scheduled(cron = "0/20 * * * * ? ")
    public void run(){
        try {
            Unanswer_Question unansweredTopicId = zsxq_api.getUnansweredTopicId(cookie, groupId);
            List<Topics> list = unansweredTopicId.getResp_data().getTopics();
            if(list==null || list.isEmpty()){
                log.info("未检索到问题");
                return;
            }
            Topics topic = list.get(0);
            String accessToken = llama_api.getAccessToken(client_id, client_secret);
            String answer = llama_api.question(accessToken, topic.getQuestion().getText());

            boolean stauts = zsxq_api.answer(cookie, groupId, topic.getTopic_id(), answer, true);
            log.info("检索到问题:{}， 给出答案:{}，状态:{}", topic.getQuestion().getText(), answer, stauts);
        }catch (Exception e){
            log.error("回答问题异常，异常情况", e);
        }


    }





}
