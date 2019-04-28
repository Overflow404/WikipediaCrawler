package downloader;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class SimpleDownloaderTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    private SimpleDownloader downloader;

    @Before
    public void setup() {
        downloader = new SimpleDownloader();
    }

    @Test
    public void testSuccessDownload() throws MalformedURLException {
        URL url = new URL("http://localhost:8089/my/page");
        String content = "<response>Some content</response>";

        stubFor(get(urlEqualTo("/my/page"))
                .willReturn(aResponse()
                .withStatus(200)
                .withBody(content)));

        DownloadResult result = downloader.download(url);

        Assert.assertEquals(DownloadResult.Result.SUCCESS, result.getResult());
        Assert.assertEquals(content, result.getContent());
    }

    @Test
    public void testUnsuccessfulDownload() throws MalformedURLException {
        URL url = new URL("http://localhost:8089/my/page");
        String content = "<response>Some content</response>";

        stubFor(get(urlEqualTo("/my/page"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withBody(content)));

        DownloadResult result = downloader.download(url);
        Assert.assertEquals(DownloadResult.Result.FAILURE, result.getResult());
    }
}
