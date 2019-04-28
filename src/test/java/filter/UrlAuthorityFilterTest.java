package filter;

import org.junit.Assert;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlAuthorityFilterTest {

    @Test
    public void authorityMatchTest() throws MalformedURLException {
        URL baseUrl = new URL("https://en.wikipedia.org/wiki/Main_Page");
        UrlAuthorityFilter filter = new UrlAuthorityFilter(baseUrl);
        URL url = new URL("https://en.wikipedia.org/wiki/Wikipedia");

        boolean accept = filter.accept(url);

        Assert.assertTrue(accept);
    }

    @Test
    public void authorityDoesNotMatchTest() throws MalformedURLException {
        URL baseUrl = new URL("http://google.com");
        UrlAuthorityFilter filter = new UrlAuthorityFilter(baseUrl);
        URL url = new URL("http://www.italy.com");

        boolean accept = filter.accept(url);

        Assert.assertFalse(accept);
    }
}
