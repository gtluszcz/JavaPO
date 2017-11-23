package Lab2;

import org.junit.Test;
import org.junit.rules.ExpectedException;


import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static sun.misc.Version.println;

public class MatrixTest {
    @Test
    public void getColumn() throws Exception {
        Random random = new Random();
        int rows = random.nextInt(100)+1;
        int cols = random.nextInt(100)+1;
        double[][] tab = new double[rows][cols];
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                tab[i][j]=random.nextInt(100)+1;
            }
        }

        Matrix mat = new Matrix(tab);
        System.out.println(mat.toString());
        ExpectedException thrown = ExpectedException.none();
        for (int c=-1;c<=rows+1;c++) {
            try {
                Matrix colc = mat.getColumn(c);

                for (int j=0;j<colc.cols;j++){
                    assertEquals(mat.get(j,c),colc.get(j,0),0.001);
                }

            } catch (RuntimeException e) {

            }


        }


    }

    private Matrix matrix1 = new Matrix(new double[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
    });

    private Matrix matrix2 = new Matrix(new double[][]{
            {13, 14, 15, 16},
            {17, 18, 19, 20},
            {21, 22, 23, 24},
    });

    @org.junit.Test
    public void testToString() throws Exception {
        double[][] d = new double[][]{
                {5,2,6,1},
                {0,6,2,0},
                {3,8,1,4},
                {1,8,5,6}
        };
        Matrix matrix2 = new Matrix(d);
        assertEquals("[[5.0,2.0,6.0,1.0],[0.0,6.0,2.0,0.0],[3.0,8.0,1.0,4.0],[1.0,8.0,5.0,6.0]]",matrix2.toString());

    }

    @org.junit.Test
    public void asArray() throws Exception {
        assertArrayEquals(
                new double[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}},
                matrix1.asArray()
        );
    }

    @org.junit.Test
    public void get() throws Exception {
        assertEquals(2.0, matrix1.get(1, 2),0.1);
    }

    @org.junit.Test
    public void set() throws Exception {
        assertEquals(2.0, matrix1.get(1, 2),0.1);
        matrix1.set(1, 2, 5.0);
        assertEquals(5.0, matrix1.get(1, 2),0.1);
    }

    @org.junit.Test
    public void reshape() throws Exception {
        assertEquals(3, matrix1.asArray().length);
        matrix1.reshape(4, 3);
        assertEquals(4, matrix1.asArray().length);
    }

    @org.junit.Test
    public void shape() throws Exception {
        assertArrayEquals(new int[]{1, 1}, new Matrix(1, 1).shape());
        assertArrayEquals(new int[]{3, 4}, matrix1.shape());
    }

    @org.junit.Test
    public void add() throws Exception {
        assertArrayEquals(
                matrix1.add(matrix2).asArray(),
                matrix2.add(matrix1).asArray()
        );
        assertArrayEquals(
                new double[][]{{14, 16, 18, 20}, {22, 24, 26, 28}, {30, 32, 34, 36}},
                matrix1.add(matrix2).asArray()
        );
    }

    @org.junit.Test
    public void sub() throws Exception {
        assertArrayEquals(
                new double[][]{{-12, -12, -12, -12}, {-12, -12, -12, -12}, {-12, -12, -12, -12}},
                matrix1.sub(matrix2).asArray()
        );
        assertArrayEquals(
                new double[][]{{12, 12, 12, 12}, {12, 12, 12, 12}, {12, 12, 12, 12}},
                matrix2.sub(matrix1).asArray()
        );
    }

    @org.junit.Test
    public void mul() throws Exception {
        assertArrayEquals(
                matrix1.mul(matrix2).asArray(),
                matrix2.mul(matrix1).asArray()
        );
        assertArrayEquals(
                new double[][]{{13.0, 28.0, 45.0, 64.0}, {85.0, 108.0, 133.0, 160.0}, {189.0, 220.0, 253.0, 288.0}},
                matrix1.mul(matrix2).asArray()
        );
    }

    @org.junit.Test
    public void div() throws Exception {
        Matrix matrix1 = new Matrix(new double[][]{
                {16, 8, 4},
                {12, 9, 6},
        });

        Matrix matrix2 = new Matrix(new double[][]{
                {2, 2, 2},
                {3, 3, 3},
        });

        assertArrayEquals(
                new double[][]{{8, 4, 2}, {4, 3, 2}},
                matrix1.div(matrix2).asArray()
        );
    }

    @org.junit.Test
    public void add1() throws Exception {
        assertArrayEquals(
                new double[][]{{3, 4, 5, 6}, {7, 8, 9, 10}, {11, 12, 13, 14}},
                matrix1.add(2).asArray()
        );
    }

    @org.junit.Test
    public void sub1() throws Exception {
        assertArrayEquals(
                new double[][]{{-1, 0, 1, 2}, {3, 4, 5, 6}, {7, 8, 9, 10}},
                matrix1.sub(2).asArray()
        );
    }

    @org.junit.Test
    public void mul1() throws Exception {
        assertArrayEquals(
                new double[][]{{2, 4, 6, 8}, {10, 12, 14, 16}, {18, 20, 22, 24}},
                matrix1.mul(2).asArray()
        );
    }

    @org.junit.Test
    public void div1() throws Exception {
        assertArrayEquals(
                new double[][]{{2, 4, 8}},
                new Matrix(new double[][]{{4, 8, 16}}).div(2).asArray()
        );
    }

    @org.junit.Test
    public void dot() throws Exception {
        double[][] d = new double[][]{
                {5,2,6,1},
                {0,6,2,0},
                {3,8,1,4},
                {1,8,5,6}
        };
        double[][] f = new double[][]{
                {7,5,8,0},
                {1,8,2,6},
                {9,4,3,8},
                {5,3,7,9}


        };

        Matrix matrix2 = new Matrix(d);
        Matrix matrix4 = new Matrix(f);
        assertEquals("[[96.0,68.0,69.0,69.0],[24.0,56.0,18.0,52.0],[58.0,95.0,71.0,92.0],[90.0,107.0,81.0,142.0]]",matrix2.dot(matrix4).toString());
    }

    @org.junit.Test
    public void frobenius() throws Exception {
        assertEquals(
                336.0,
                new Matrix(new double[][]{{4, 8, 16}}).frobenius(),
                0.01
        );
    }

}