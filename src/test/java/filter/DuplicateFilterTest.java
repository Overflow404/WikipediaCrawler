package filter;

import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class DuplicateFilterTest {

    @Test
    public void notDuplicateElement() throws MalformedURLException {
        DuplicateFilter filter = new DuplicateFilter();
        URL url = new URL("http://localhost/test");

        boolean result = filter.accept(url);

        Assert.assertTrue(result);
    }

    @Test
    public void duplicateElement() throws MalformedURLException {
        DuplicateFilter filter = new DuplicateFilter();
        URL url = new URL("http://localhost/test");

        boolean resultFirstAttempt = filter.accept(url);
        Assert.assertTrue(resultFirstAttempt);

        boolean resultSecondAttempt = filter.accept(url);
        Assert.assertFalse(resultSecondAttempt);
    }
}
