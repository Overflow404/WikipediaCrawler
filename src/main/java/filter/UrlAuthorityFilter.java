package filter;

import java.net.URL;

public class UrlAuthorityFilter implements UrlFilter {
    private String authority;

    public UrlAuthorityFilter(URL url) {
        this.authority = url.getAuthority();
    }

    @Override
    public boolean accept(URL url) {
        return this.authority.equals(url.getAuthority());
    }
}