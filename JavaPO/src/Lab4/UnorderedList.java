package Lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    List<ListItem> listitems = new ArrayList<>() ;

    UnorderedList addItem(ListItem s){
        listitems.add(s);
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<ul>\n");
        for (ListItem i:listitems){
            i.writeHTML(out);
        }
        out.printf("</ul>\n");
    }
}
