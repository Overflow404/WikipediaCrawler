package crawler;

import downloader.DownloadResult;
import downloader.DownloadResult.Result;
import downloader.Downloader;
import downloader.SimpleDownloader;
import link.LinkParser;
import parser.SimpleParser;
import parser.Page;
import parser.Parser;
import storage.UrlStorage;

import java.net.URL;
import java.util.List;


class PageHarvester implements Runnable {

    private boolean stop = false;

    private UrlStorage storage;
    private Downloader downloader;
    private Parser parser;
    private LinkParser linkParser;

    PageHarvester(UrlStorage storage) {
        this.storage = storage;
        this.downloader = new SimpleDownloader();
        this.parser = new SimpleParser();
        this.linkParser = new LinkParser();
    }

    @Override
    public void run() {
        while (threadsWork()) {
            URL url = storage.poll();
            if (url != null) {
                processUrl(url);
            }
        }
    }

    private void processUrl(URL pageUrl) {
        DownloadResult result = downloader.download(pageUrl);

        if (result.getResult() == Result.FAILURE) {
            System.out.println("Failed downloading url " + pageUrl + " with reason " + result.getFailureReason());
            return;
        }
        String content = result.getContent();
        Page page = parser.parse(content);

        List<String> links = page.getLinks();

        for (String link : links) {
            if (linkParser.isValid(link)) {
                try {
                    URL url = linkParser.parse(pageUrl, link);
                    storage.add(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(pageUrl + ": " + page.getTitle());
    }

    private boolean threadsWork() {
        return !stop;
    }

    void stop() {
        stop = true;
    }
}
