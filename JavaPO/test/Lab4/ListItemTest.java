package Lab4;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListItemTest extends HtmlTagTest {
    @Test
    public void writeHTML() throws Exception {
        ListItem item = new ListItem("item 1");

        String output = this.output(item);

        assertTrue(output.contains("<li"));
        assertEquals("item 1", this.getStringBetweenTags(output));
        assertTrue(output.contains("</li>"));
    }

}