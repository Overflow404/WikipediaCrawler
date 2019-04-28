package parser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SimpleParserTest {

    private SimpleParser parser;

    @Before
    public void setup() {
        parser = new SimpleParser();
    }

    @Test
    public void successfulParsing() {
        String content = "<title>Wikipedia</title><a href=\"http://example.com\">text</a>";
        Page page = parser.parse(content);

        Assert.assertEquals("Wikipedia", page.getTitle());

        List<String> links = page.getLinks();
        Assert.assertEquals(1, links.size());
        String link = links.get(0);
        Assert.assertEquals("http://example.com", link);
    }

    @Test
    public void successfulParsingNoTitleAndTitle() {
        String content = "";
        Page page = parser.parse(content);

        String title = page.getTitle();
        Assert.assertTrue(title.isEmpty());
        List<String> links = page.getLinks();
        Assert.assertTrue(links.isEmpty());
    }
}
