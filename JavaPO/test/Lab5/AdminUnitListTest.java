package Lab5;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class AdminUnitListTest {

    @Test
    public void test_throws_exception_for_illegal_arguments() {
        boolean caught= false;

        AdminUnitList list = new AdminUnitList();

        try{
            caught=false;
            list.list(System.out,120000,-10);
        }catch (RuntimeException e){
            caught = true;
        }
        assertTrue(caught);
        try{
            caught=false;
            list.list(System.out,0,-10);
        }catch (RuntimeException e){
            caught = true;
        }
        assertTrue(caught);
        try{
            caught=false;
            list.list(System.out,-100,-10);
        }catch (RuntimeException e){
            caught = true;
        }
        assertTrue(caught);
        try{
            caught=false;
            list.list(System.out,1200,-10);
        }catch (RuntimeException e){
            caught = true;
        }
        assertTrue(caught);
        try{
            caught=false;
            list.list(System.out,120000,-10);
        }catch (RuntimeException e){
            caught = true;
        }
        assertTrue(caught);
    }

}