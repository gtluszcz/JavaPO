package Lab4;

import java.io.PrintStream;

public class ListItem {
    String content;

    ListItem(String text){
        this.content = text;
    }

    void writeHTML(PrintStream out){
        out.printf("<li>%s</li>\n",this.content);
    }
}
