package Lab5;

import java.util.ArrayList;
import java.util.List;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();
    List<AdminUnit> children = new ArrayList<AdminUnit>();
    ;

    public String toString() {
        return name + " " + adminLevel + " " + population + " " + area + " " + density;
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
