package filter;

import java.net.URL;

public interface UrlFilter {
    default boolean accept(URL url) {
        return true;
    }
}
