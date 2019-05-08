package crawler;

import filter.DuplicateFilter;
import filter.UrlAuthorityFilter;
import storage.UrlQueue;
import storage.UrlStorage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WikipediaCrawler {
    private final static int N_THREADS = 8;
    private ExecutorService executor;
    private List<PageHarvester> harvesters;

    public void crawl(URL startPage) {
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

    public void stopAfter(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted during sleep.");
        }
        stopPool();
    }
}
