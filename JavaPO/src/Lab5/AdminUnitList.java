package Lab5;

import Lab5.Exceptions.EmptyFieldToNumberException;
import Lab5.Exceptions.WrongColumnIndexException;
import javafx.util.Pair;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long,AdminUnit> id2unit = new HashMap<>();
    Map<AdminUnit,Long> unit2parentid = new HashMap<>();

    public void read(String filename) throws IOException{
        CSVReader reader = new CSVReader(filename);

        while(reader.next()){
            AdminUnit unit = new AdminUnit();

            //populate adminUnit fields
            if (!reader.isMissing("name")){
                unit.name = reader.get("name");
            }
            if (!reader.isMissing("admin_level")){
                unit.adminLevel = reader.getInt("admin_level");
            }
            if (!reader.isMissing("area")){
                unit.area = reader.getDouble("area");
            }
            if (!reader.isMissing("population")){
                unit.population = reader.getDouble("population");
            }
            if (!reader.isMissing("density")){
                unit.density = reader.getDouble("density");
            }

            //populate units bounding box
            if (!reader.isMissing("x1") && !reader.isMissing("y1")){
                unit.bbox.addPoint(reader.getDouble("x1"),reader.getDouble("y1"));
            }
            if (!reader.isMissing("x2") && !reader.isMissing("y2")){
                unit.bbox.addPoint(reader.getDouble("x2"),reader.getDouble("y2"));
            }
            if (!reader.isMissing("x3") && !reader.isMissing("y3")){
                unit.bbox.addPoint(reader.getDouble("x3"),reader.getDouble("y3"));
            }
            if (!reader.isMissing("x4") && !reader.isMissing("y4")){
                unit.bbox.addPoint(reader.getDouble("x4"),reader.getDouble("y4"));
            }


            //add AdminUnit to list
            this.units.add(unit);

            //add id and parent_id to hashMaps
            if(!reader.isMissing("id")){
                id2unit.put(reader.getLong("id"),unit);
            }
            if(!reader.isMissing("parent")){
                unit2parentid.put(unit,reader.getLong("parent"));
            }
        }


        // asign parents and children
        for(Map.Entry<AdminUnit,Long> para :unit2parentid.entrySet()){
            AdminUnit child = para.getKey();
            if (id2unit.containsKey(para.getValue())) {
                //dodaje rodzica
                child.parent = id2unit.get(para.getValue());
            }
            if (child.parent!=null){
                //dodaje dziecko do listy dzieci rodzica
                child.parent.children.add(child);
            }
        }
    }

    public void list(PrintStream out){
        for (AdminUnit unit: units) {
            out.append(unit.toString() + "\n");
        }
    }

    void list(PrintStream out,int offset, int limit ){

        if (limit+offset>units.size())
            limit=units.size()-offset;

        if (limit<=0){
            throw new RuntimeException("illegal arguments");
        }

        for (AdminUnit unit: units.subList(offset,offset+limit)) {
            out.append(unit.toString() + "\n");
        }
    }

    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();


        for (AdminUnit unit: units){
            if (regex) {
                if (unit.name.matches(pattern)) {
                    ret.units.add(unit);
                }
            }else {
                if (unit.name.contains(pattern)) {
                    ret.units.add(unit);
                }
            }
        }
        return ret;
    }

    public void fixAll(){
        for (AdminUnit unit:units){
            unit.fixMissingValues();
        }

    }

    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance){
        AdminUnitList ret = new AdminUnitList();

        if (unit.bbox.isEmpty()) return ret;

        for (AdminUnit u: units){

            if (u.adminLevel!=unit.adminLevel) continue;

            if(unit.adminLevel == 8){
                if (!u.bbox.isEmpty() && unit.bbox.distanceTo(u.bbox) <= maxdistance) ret.units.add(u);
            }
            else{
                if (!u.bbox.isEmpty() && unit.bbox.intersects(u.bbox)) ret.units.add(u);
            }
        }


        return ret;
    }

    AdminUnitList sortInplaceByName(){

        class AdminUnitListSorter implements Comparator<AdminUnit>{
            @Override
            public int compare(AdminUnit unit1, AdminUnit unit2) {
                return unit1.name.compareToIgnoreCase(unit2.name);
            }
        }
        AdminUnitListSorter c = new AdminUnitListSorter();
        this.units.sort(c);
        return this;

    }

    AdminUnitList sortInplaceByArea(){

        this.units.sort(new Comparator<AdminUnit>() {
            @Override
            public int compare(AdminUnit unit1, AdminUnit unit2) {
                return Double.compare(unit1.area, unit2.area);
            }
        });
        return this;
    }

    AdminUnitList sortInplaceByPopulation(){
        this.units.sort((unit1, unit2) -> Double.compare(unit1.population, unit2.population));
        return this;
    }

    AdminUnitList sortInplace(Comparator<AdminUnit> cmp){
        this.units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp){
        AdminUnitList ret = new AdminUnitList();
        for (AdminUnit unit:this.units) {
            ret.units.add(unit);
        }
        return ret.sortInplace(cmp);
    }

    AdminUnitList filter(Predicate<AdminUnit> pred){
        AdminUnitList ret = new AdminUnitList();
        for (AdminUnit unit:this.units) {
            if (pred.test(unit)) ret.units.add(unit);
        }

        return ret;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred,int offset, int limit){
        AdminUnitList ret = this.filter(pred);

        if (limit+offset>ret.units.size())
            limit=ret.units.size()-offset;

        if (limit<=0){
            throw new RuntimeException("illegal arguments");
        }

        ret.units = ret.units.subList(offset,offset+limit);
        return ret;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int limit){
        return this.filter(pred,0,limit);
    }
}
