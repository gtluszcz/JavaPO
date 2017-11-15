package Lab4;

import java.io.PrintStream;

public class Photo implements HtmlTag{
    String url;
    Photo(String url){
        this.url =url;
    }
    public void writeHTML(PrintStream out){
        out.printf("<img src=\"%s\" alt=\"Smiley face\" height=\"42\" width=\"42\"/>\n",url);
    }
}
