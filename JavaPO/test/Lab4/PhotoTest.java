package Lab4;

import org.junit.Test;

import static org.junit.Assert.*;

public class PhotoTest extends HtmlTagTest {
    @Test
    public void writeHTML() throws Exception {
        Photo photo = new Photo("http://example.com");

        String output = this.output(photo);

        assertTrue(output.contains("<img"));
        assertTrue(output.contains("src=\"http://example.com\""));
    }

}