package Lab4;

import javax.xml.bind.annotation.XmlElement;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    @XmlElement(name="item")
    List<ListItem> listitems = new ArrayList<>() ;

    UnorderedList addItem(ListItem s){
        listitems.add(s);
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("        <ul>\n");
        for (ListItem i:listitems){
            i.writeHTML(out);
        }
        out.printf("        </ul>\n");
    }
}
