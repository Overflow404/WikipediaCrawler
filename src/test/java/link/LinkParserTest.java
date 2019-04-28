package link;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class LinkParserTest {

    private LinkParser linkParser;

    @Before
    public void setup() {
        linkParser = new LinkParser();
    }

    @Test
    public void acceptsValidLink() {
        String link = "http://www.google.it";

        boolean result = linkParser.isValid(link);

        Assert.assertTrue(result);
    }

    @Test
    public void emptyLinkIsNotValidTest() {
        String link = "";

        boolean result = linkParser.isValid(link);

        Assert.assertFalse(result);
    }

    @Test
    public void dashLinkIsNotValidTest() {
        String link = "#cite_ref";

        boolean result = linkParser.isValid(link);

        Assert.assertFalse(result);
    }

    @Test
    public void notHttpLinkIsNotValidTest() {
        String link = "irc://localhost";

        boolean result = linkParser.isValid(link);

        Assert.assertFalse(result);
    }


    @Test
    public void parsePartiallyQualifiedUrlTest() throws MalformedURLException {
        URL base = new URL("http://www.google.it");
        String suffix = "/home.php";

        URL expected = new URL("http://www.google.it/home.php");

        URL url = linkParser.parse(base, suffix);

        Assert.assertEquals(expected, url);
    }

    @Test
    public void parseFullyQualifiedUrlTest() throws MalformedURLException {
        URL base = new URL("http://www.google.it");
        String suffix = "http:///www.google.it/accounts.php";

        URL expected = new URL("http:///www.google.it/accounts.php");

        URL url = linkParser.parse(base, suffix);

        Assert.assertEquals(expected, url);
    }
}
