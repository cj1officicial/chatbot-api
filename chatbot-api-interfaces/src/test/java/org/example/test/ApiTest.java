package org.example.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;

public class ApiTest {

    @Test
    public void query_unanswered_question() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885142184551/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie","zsxq_access_token=CD306F81-1676-C45E-0BC9-918FFF6E0820_71AF3420A3CD4218; zsxqsessionid=8614b995917b81e9432ba772cd1004ed; abtest_env=product; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22585151855528254%22%2C%22first_id%22%3A%2218c82795702e57-029d528625393d8-4c657b58-1821369-18c8279570319c5%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThjODI3OTU3MDJlNTctMDI5ZDUyODYyNTM5M2Q4LTRjNjU3YjU4LTE4MjEzNjktMThjODI3OTU3MDMxOWM1IiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiNTg1MTUxODU1NTI4MjU0In0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22585151855528254%22%7D%2C%22%24device_id%22%3A%2218c82795702e57-029d528625393d8-4c657b58-1821369-18c8279570319c5%22%7D");
        get.addHeader("Content-Type", "application/json;charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/588412114888524/answer");
        post.addHeader("cookie","zsxq_access_token=CD306F81-1676-C45E-0BC9-918FFF6E0820_71AF3420A3CD4218; zsxqsessionid=8614b995917b81e9432ba772cd1004ed; abtest_env=product; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22585151855528254%22%2C%22first_id%22%3A%2218c82795702e57-029d528625393d8-4c657b58-1821369-18c8279570319c5%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThjODI3OTU3MDJlNTctMDI5ZDUyODYyNTM5M2Q4LTRjNjU3YjU4LTE4MjEzNjktMThjODI3OTU3MDMxOWM1IiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiNTg1MTUxODU1NTI4MjU0In0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22585151855528254%22%7D%2C%22%24device_id%22%3A%2218c82795702e57-029d528625393d8-4c657b58-1821369-18c8279570319c5%22%7D");
        post.addHeader("Content-Type", "application/json;charset=UTF-8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"我还是不会\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": true\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

}
