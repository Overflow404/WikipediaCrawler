package downloader;

import java.net.URL;

public interface Downloader {
    DownloadResult download(URL url);
}
