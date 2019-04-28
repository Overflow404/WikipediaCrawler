package parser;

import java.util.List;

public class Page {

    private String title;
    private List<String> links;

    Page(String title, List<String> links) {
        this.title = title;
        this.links = links;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLinks() {
        return links;
    }

}
