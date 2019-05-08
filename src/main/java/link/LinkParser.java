package link;

import java.net.MalformedURLException;
import java.net.URL;

public class LinkParser {
    public boolean isValid(String link) {
        if (link.isBlank()) {
            return false;
        }
        if (link.startsWith("#")) {
            return false;
        }
        if (isRelative(link)) {
            return true;
        } else {
            return link.startsWith("http");
        }
    }

    public URL parse(URL pageUrl, String link) {
        try {
            if (isRelative(link)) {
                return new URL(pageUrl, link);
            }
            return new URL(link);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid link " + link, e);
        }
    }

    private boolean isRelative(String link) {
        return link.startsWith("/");
    }
}
