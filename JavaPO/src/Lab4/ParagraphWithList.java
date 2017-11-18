package Lab4;

import javax.xml.bind.annotation.XmlElement;
import java.io.PrintStream;

public class ParagraphWithList extends Paragraph{
    @XmlElement
    UnorderedList list = new UnorderedList();

    ParagraphWithList setContent(String text){
        this.content = text;
        return this;
    }

    ParagraphWithList addListItem(String text){
        ListItem tmp = new ListItem(text);
        list.addItem(tmp);
        return this;
    }

    public void writeHTML(PrintStream out){

        out.printf("        <p>%s</p>\n",this.content);
        list.writeHTML(out);
    }
}
