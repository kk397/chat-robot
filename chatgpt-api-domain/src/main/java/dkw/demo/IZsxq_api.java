package dkw.demo;

import dkw.demo.aggregates.Unanswer_Question;

import java.io.IOException;

public interface IZsxq_api {
    Unanswer_Question getUnansweredTopicId(String cookie, String groupId) throws IOException;

    boolean answer(String cookie, String groupId, String topicId, String text, boolean silenced) throws IOException;
}
