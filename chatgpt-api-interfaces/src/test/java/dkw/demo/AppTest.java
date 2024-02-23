package dkw.demo;

import junit.framework.TestCase;
import junit.framework.TestSuite;
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
import org.junit.Test;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
@Slf4j
public class AppTest
{
    @Test
    public void question() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/51111112588884/topics?scope=unanswered_questions&count=20");

        get.addHeader("Cookie", "zsxq_access_token=A6935CA5-C9E8-F3D2-0099-C92A989BD306_BC6E7DFE52A22508; zsxqsessionid=8db3062bd10e6437dc1b3a8654fef1d3; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22581558115152214%22%2C%22first_id%22%3A%2218dcfa5d8dce1e-024edf337b1e694-1f525637-1296000-18dcfa5d8dd11ec%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThkY2ZhNWQ4ZGNlMWUtMDI0ZWRmMzM3YjFlNjk0LTFmNTI1NjM3LTEyOTYwMDAtMThkY2ZhNWQ4ZGQxMWVjIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiNTgxNTU4MTE1MTUyMjE0In0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22581558115152214%22%7D%2C%22%24device_id%22%3A%2218dcfa5d8dce1e-024edf337b1e694-1f525637-1296000-18dcfa5d8dd11ec%22%7D; abtest_env=product");
        get.addHeader("Content-Type", "application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String r = EntityUtils.toString(response.getEntity());
            log.info(r);
        }else {
            log.info(Integer.toString(response.getStatusLine().getStatusCode()));
        }
    }
    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/211151511415421/answer");

        post.addHeader("Cookie", "zsxq_access_token=A6935CA5-C9E8-F3D2-0099-C92A989BD306_BC6E7DFE52A22508; zsxqsessionid=8db3062bd10e6437dc1b3a8654fef1d3; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22581558115152214%22%2C%22first_id%22%3A%2218dcfa5d8dce1e-024edf337b1e694-1f525637-1296000-18dcfa5d8dd11ec%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThkY2ZhNWQ4ZGNlMWUtMDI0ZWRmMzM3YjFlNjk0LTFmNTI1NjM3LTEyOTYwMDAtMThkY2ZhNWQ4ZGQxMWVjIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiNTgxNTU4MTE1MTUyMjE0In0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22581558115152214%22%7D%2C%22%24device_id%22%3A%2218dcfa5d8dce1e-024edf337b1e694-1f525637-1296000-18dcfa5d8dd11ec%22%7D; abtest_env=product");
        post.addHeader("Content-Type", "application/json; charset=UTF-8");

        String payload = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"我也不知道\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": true\n" +
                "  }\n" +
                "}";
        StringEntity stringEntity = new StringEntity(payload, ContentType.create("text/json", "UTF-8"));

        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String string = EntityUtils.toString(response.getEntity());
            log.info(string);
        }else{
            log.info(Integer.toString(response.getStatusLine().getStatusCode()));
        }

    }
}
