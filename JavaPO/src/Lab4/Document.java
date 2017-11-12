package Lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();


    Document(String title){
        this.title = title;
    }
    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl){
        Photo tmp = new Photo(photoUrl);
        this.photo = tmp;
        return this;
    }

    Section addSection(String sectionTitle){
        Section tmp = new Section();
        tmp.setTitle(sectionTitle);
        this.sections.add(tmp);
        return tmp;
    }
    Document addSection(Section s){
        this.sections.add(s);
        return this;
    }


    void writeHTML(PrintStream out){
        out.printf("<document>\n");
        out.printf("<title>%s</title>\n",this.title);
        photo.writeHTML(out);
        for (Section i:sections){
            i.writeHTML(out);
        }
        out.printf("</document>\n");
    }
}
