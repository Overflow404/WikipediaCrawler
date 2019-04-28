package storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlsStorageTest {

    private UrlQueue storage;

    @Before
    public void setup() {
        storage = new UrlQueue();
    }

    @Test
    public void urlsInsertion() throws MalformedURLException {
        int expectedSize = 2;

        storage.add(new URL("http://localhost/first"));
        storage.add(new URL("http://localhost/second"));

        int actualSize = storage.size();

        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void peekTest() throws MalformedURLException {
        URL url = new URL("http://localhost/first");
        storage.add(url);

        URL result = storage.poll();

        Assert.assertEquals(url, result);
    }
}
