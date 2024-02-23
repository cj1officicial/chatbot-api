package org.example.domain.zsxq;

import org.example.domain.zsxq.model.aggregates.UnAnsweredQuestionAggregates;

import java.io.IOException;

public interface IZsxqApi {

    UnAnsweredQuestionAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException;

    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;

}
