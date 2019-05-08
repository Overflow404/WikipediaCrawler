package filter;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class DuplicateFilter implements UrlFilter {
    private Set<URL> visitedUrls;

    public DuplicateFilter() {
        visitedUrls = new HashSet<>();
    }

    @Override
    public boolean accept(URL url) {
        if (visitedUrls.contains(url)) {
            return false;
        }
        visitedUrls.add(url);
        return true;
    }
}
