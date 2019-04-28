package crawler;

import filter.UrlAuthorityFilter;
import filter.DuplicateFilter;
import storage.UrlQueue;
import storage.UrlStorage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WikipediaCrawler {

    private final static int N_THREADS = 8;

    private ExecutorService executor;
    private List<PageHarvester> harvesters;

    private void crawl(URL startPage) {

        if (startPage == null) {
            throw new IllegalArgumentException("Start page cannot be null.");
        }

        executor = Executors.newFixedThreadPool(N_THREADS);
        harvesters = new ArrayList<>();

        UrlStorage storage = new UrlQueue();
        storage.addFilter(new UrlAuthorityFilter(startPage));
        storage.addFilter(new DuplicateFilter());

        for (int i = 0; i < N_THREADS; i++) {
            PageHarvester harvester = new PageHarvester(storage);
            harvesters.add(harvester);
            executor.execute(harvester);
        }

        storage.add(startPage);
    }

    private void stopPool() {
        harvesters.forEach(PageHarvester::stop);
        executor.shutdown();
    }

    private void stopAfter(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted during sleep.");
        }

        stopPool();
    }

    public static void main(String[] args) throws MalformedURLException {
        WikipediaCrawler pageCrawler = new WikipediaCrawler();
        URL startPage = new URL("https://en.wikipedia.org/wiki/Main_Page");
        pageCrawler.crawl(startPage);

        pageCrawler.stopAfter(15000);
    }
}
