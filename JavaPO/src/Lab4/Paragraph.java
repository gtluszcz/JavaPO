package Lab4;

import java.io.PrintStream;

public class Paragraph implements HtmlTag{
    String content;
    Paragraph setContent(String content){
        this.content = content;
        return this;
    }
    public void writeHTML(PrintStream out){
        out.printf("<p>%s</p>\n",this.content);
    }
}
