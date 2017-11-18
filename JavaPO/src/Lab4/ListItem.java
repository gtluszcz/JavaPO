package Lab4;


import javax.xml.bind.annotation.XmlValue;
import java.io.PrintStream;

public class ListItem implements HtmlTag{
    @XmlValue
    String content;
    ListItem(){}
    ListItem(String text){
        this.content = text;
    }

    public void writeHTML(PrintStream out){
        out.printf("            <li>%s</li>\n",this.content);
    }
}
