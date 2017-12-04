package Lab5;

import Lab5.Exceptions.EmptyFieldToNumberException;
import Lab5.Exceptions.WrongColumnIndexException;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();

    public void read(String filename) throws IOException{
        CSVReader reader = new CSVReader(filename);

        while(reader.next()){
            AdminUnit unit = new AdminUnit();
            unit.name = "blank";
            unit.adminLevel = -1;
            unit.area = -1.0;
            unit.population = -1.0;
            unit.density = -1.0;

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

}
