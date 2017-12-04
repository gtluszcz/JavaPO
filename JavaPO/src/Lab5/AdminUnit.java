package Lab5;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();

    public String toString(){
        return name+" "+adminLevel+" "+population+" "+area+" "+density;
    }
}
