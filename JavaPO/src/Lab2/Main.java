package Lab2;

public class Main {
    public static void main(String[] args){

//        Matrix matrix1 = new Matrix(2,3);
//        double[][] d = new double[][]{
//                {5,2,6,1},
//                {0,6,2,0},
//                {3,8,1,4},
//                {1,8,5,6}
//        };
//        double[][] f = new double[][]{
//                {7,5,8,0},
//                {1,8,2,6},
//                {9,4,3,8},
//                {5,3,7,9}
//
//
//        };
//
//        Matrix matrix2 = new Matrix(d);
//        Matrix matrix4 = new Matrix(f);
//        System.out.println(matrix2.toString());
////        Matrix matrix3 = new Matrix(matrix2.asArray());
////        System.out.println(matrix3.toString());
////        matrix3.set(3,2,9);
////        System.out.println(matrix3.toString());
////        System.out.println(matrix3.get(3,2));
////        matrix3.reshape(5,4);
////        System.out.println(matrix3.toString());
////        System.out.println(matrix3.shape()[0]+","+matrix3.shape()[1]);
////        matrix3.add(5.0);
////        System.out.println(matrix3.toString());
//        System.out.println(matrix2.dot(matrix4).toString());
//        System.out.println(matrix2.frobenius());
        Matrix m = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        System.out.println(m.getColumn(1));
    }
}

