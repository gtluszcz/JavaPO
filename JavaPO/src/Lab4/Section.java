package Lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section{
    String title;
    List<Paragraph> paragraphs = new ArrayList<>() ;

    Section setTitle(String title){
        this.title=title;
        return this;
    }
    Section addParagraph(String paragraphText){
        Paragraph tmp = new Paragraph();
        tmp.setContent(paragraphText);
        paragraphs.add(tmp);
        return this;
    }
    Section addParagraph(Paragraph p){
        paragraphs.add(p);
        return this;
    }
    void writeHTML(PrintStream out){
        out.printf("<section title=\"%s\">\n",this.title);
        for (Paragraph i:paragraphs){
            i.writeHTML(out);
        }
        out.printf("</section>\n");
    }
}