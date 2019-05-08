package example;

import crawler.WikipediaCrawler;

import java.net.MalformedURLException;
import java.net.URL;

public class Example {
    public static void main(String[] args) throws MalformedURLException {
        WikipediaCrawler pageCrawler = new WikipediaCrawler();
        URL startPage = new URL("https://en.wikipedia.org/wiki/Main_Page");
        pageCrawler.crawl(startPage);
        pageCrawler.stopAfter(15000);
    }
}
