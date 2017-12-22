package Lab5;

import java.util.ArrayList;
import java.util.List;

public class AdminUnit {
    String name = "blank";
    int adminLevel = -1;
    double population = -1.0;
    double area = -1.0;
    double density = -1.0;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();
    List<AdminUnit> children = new ArrayList<AdminUnit>();

    public String toString() {
        String rodzic = "";
        if (parent!=null){
            rodzic = "  Rodzic:" +parent.name;
        }
        return "Nazwa:"+name + "  Poziom:" + adminLevel + "  Populacja:" + population + "  Obszar:" + area + "  Zagęszczenie:" + density+rodzic +" "+bbox.toString();
    }

    void printTree(){
        System.out.println(this.toString());
        if(this.parent!=null){
            parent.printTree();
        }
    }

    protected void fixMissingValues() {
        if (this.parent != null) {

            if (this.density == -1.0) {
                this.parent.fixMissingValues();
                this.density = this.parent.density;
            }

            if (this.population == -1.0) {
                population=area*density;
            }
        }
    }


}
