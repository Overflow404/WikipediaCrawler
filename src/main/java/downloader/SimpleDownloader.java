package downloader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;

import java.net.URL;

public class SimpleDownloader implements Downloader {

    private HttpClient client;
    private ResponseHandler<String> handler;

    public SimpleDownloader() {
        client = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();

        handler = new BasicResponseHandler();

    }

    @Override
    public DownloadResult download(URL url) {
        try {
            HttpGet httpGet = new HttpGet(url.toURI());
            HttpResponse response = client.execute(httpGet);
            String body = handler.handleResponse(response);
            return DownloadResult.success(body);
        } catch (Throwable e) {
            return DownloadResult.failure("Download failed for url " + url);
        }
    }
}
