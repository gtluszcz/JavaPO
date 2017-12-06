package Lab5;

import Lab5.Exceptions.EmptyFieldToNumberException;
import Lab5.Exceptions.WrongColumnIndexException;
import javafx.util.Pair;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long,AdminUnit> id2unit = new HashMap<>();
    Map<AdminUnit,Long> unit2parentid = new HashMap<>();

    public void read(String filename) throws IOException{
        CSVReader reader = new CSVReader(filename);

        while(reader.next()){
            AdminUnit unit = new AdminUnit();
            unit.name = "blank";
            unit.adminLevel = -1;
            unit.area = -1.0;
            unit.population = -1.0;
            unit.density = -1.0;
            unit.parent = null;

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

            this.units.add(unit);

            if(!reader.isMissing("id")){
                id2unit.put(reader.getLong("id"),unit);
            }
            if(!reader.isMissing("parent")){
                unit2parentid.put(unit,reader.getLong("parent"));
            }
        }


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

}
