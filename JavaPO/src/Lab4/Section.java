package Lab4;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section{
    @XmlAttribute
    String title;

    @XmlElements(value= {
            @XmlElement(name = "paragraph", type = Paragraph.class),
            @XmlElement(name = "paragraph-with-list", type = ParagraphWithList.class)
    })
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
        out.printf("    <section title=\"%s\">\n",this.title);
        for (Paragraph i:paragraphs){
            i.writeHTML(out);
        }
        out.printf("    </section>\n");
    }
}
