package Lab4;

import java.io.PrintStream;

public class ListItem implements HtmlTag{
    String content;

    ListItem(String text){
        this.content = text;
    }

    public void writeHTML(PrintStream out){
        out.printf("<li>%s</li>\n",this.content);
    }
}
