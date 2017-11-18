package Lab4;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.PrintStream;

public class Photo implements HtmlTag{
    @XmlAttribute
    String url;
    Photo(){}
    Photo(String url){
        this.url =url;
    }
    public void writeHTML(PrintStream out){
        out.printf("    <img src=\"%s\" alt=\"Smiley face\" height=\"42\" width=\"42\"/>\n",url);
    }
}
