package dkw.demo;

import dkw.demo.res.AccessToken_resData;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public interface ILlama_api {


    String question(String accessToken, String question) throws IOException;

    String getAccessToken(String client_id, String client_secret) throws IOException;
}
