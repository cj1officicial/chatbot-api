package org.example.test;

import com.alibaba.fastjson.JSON;
import org.example.domain.ai.IOpenAI;
import org.example.domain.zsxq.IZsxqApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.example.domain.zsxq.model.aggregates.UnAnsweredQuestionAggregates;
import org.example.domain.zsxq.model.vo.Topics;
import org.example.domain.zsxq.service.ZsxqApi;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    private Logger logger = LoggerFactory.getLogger(SpringBootTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;
    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IOpenAI openAI;

    @Test
    public void test_zsxqApi() throws IOException {
        UnAnsweredQuestionAggregates unAnsweredQuestionAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionAggregates));

        List<Topics> topics = unAnsweredQuestionAggregates.getResp_data().getTopics();
        for (Topics topic : topics) {
            String topicId = topic.getTopic_id();
            String text = topic.getQuestion().getText();

            logger.info("topicId:{} text:{}",topicId, text);

            zsxqApi.answer(groupId, cookie, topicId, text,false);
        }
    }

    @Test
    public void test_openAIApi() throws IOException {
        String response = openAI.doChatGPT("帮我写个冒泡排序");
        logger.info("测试结果：{}"+response);
    }
}
