package Lab4;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParagraphTest extends HtmlTagTest{
    String content = "Content of a paragraph";
    Paragraph paragraph = new Paragraph();
    @Test
    public void setContent() throws Exception {
        paragraph.setContent(content);
        assertEquals(paragraph.content, content);
    }

    @Test
    public void writeHTML() throws Exception {

        paragraph.setContent(content);
        String output = this.output(paragraph);
        assertTrue(output.contains(content));
        assertTrue(output.contains("<p"));
        assertTrue(output.contains("</p>"));
    }

}