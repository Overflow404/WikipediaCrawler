package storage;

import filter.UrlFilter;

import java.net.URL;

public interface UrlStorage {
    void add(URL url);
    int size();
    URL poll();
    void addFilter(UrlFilter filter);
}
