package self.sunng.miscellaneous.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by sunxiaodong on 16/8/29.
 */
public class TestHttp {
    public static void main(String[] args) {

    }

    private static void get() {
        //创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpGet httpGet = new HttpGet("http://localhost:8081/hello");
        httpGet.addHeader("Authorization", "bearer 6295b208-c6a9-44d4-9bf2-0bda3d2be10d");
        System.out.println(httpGet.getRequestLine());

        execute(closeableHttpClient, httpGet);

    }

    private static void post() {
        //创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpPost httpPost = new HttpPost("http://localhost:8080/oauth/token?grant_type=password&username=user@awesome.com&password=cant_hack_this&client_id=sso-auth-client&client_secret=mySecret");
        System.out.println(httpPost.getRequestLine());

        execute(closeableHttpClient, httpPost);
    }

    private static void execute(CloseableHttpClient closeableHttpClient, HttpRequestBase method) {
        try {
            //执行get请求
            HttpResponse httpResponse = closeableHttpClient.execute(method);
            //获取响应消息实体
            HttpEntity entity = httpResponse.getEntity();
            //响应状态
            System.out.println("status:" + httpResponse.getStatusLine());
            //判断响应实体是否为空
            if (entity != null) {
                System.out.println("contentEncoding:" + entity.getContentEncoding());
                System.out.println("response content:" + EntityUtils.toString(entity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流并释放资源
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
