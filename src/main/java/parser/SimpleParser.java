package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;

public class SimpleParser implements Parser {

    private final static String CSS_QUERY_SELECTOR = "a";
    private final static String ATTRIBUTE_KEY = "href";

    @Override
    public Page parse(String content) {
        Document document = Jsoup.parse(content);
        String title = document.title();
        List<String> links = extractLinks(document);
        return new Page(title, links);
    }

    private List<String> extractLinks(Document document) {
        Elements anchors = document.select(CSS_QUERY_SELECTOR);
        List<String> links = new ArrayList<>();
        for (Element anchor : anchors) {
            String href = anchor.attr(ATTRIBUTE_KEY);
            links.add(href);
        }
        return links;
    }
}
