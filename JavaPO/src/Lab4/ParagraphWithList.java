package Lab4;

import java.io.PrintStream;

public class ParagraphWithList extends Paragraph{
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

    void writeHTML(PrintStream out){

        out.printf("<p>%s</p>\n",this.content);
        list.writeHTML(out);
    }
}
