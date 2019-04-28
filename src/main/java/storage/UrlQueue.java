package storage;

import filter.UrlFilter;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class UrlQueue implements UrlStorage {

    private BlockingQueue<URL> urls;
    private List<UrlFilter> filters;

    public UrlQueue() {
        urls = new LinkedBlockingQueue<>();
        filters = new LinkedList<>();
    }

    @Override
    public void add(URL url) {
        if (accept(url)) {
            urls.add(url);
        }
    }

    private boolean accept(URL url) {
        return filters.stream().allMatch(filter -> filter.accept(url));
    }

    @Override
    public int size() {
        return urls.size();
    }

    @Override
    public URL poll() {
        //TODO review
        try {
            return urls.poll(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addFilter(UrlFilter filter) {
        filters.add(filter);
    }
}
