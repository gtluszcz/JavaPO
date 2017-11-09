package Lab2;

public class Matrix {
    double[]data;
    int rows;
    int cols;
    //...
    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows*cols];
    }

    Matrix(double[][] d){
        int max=0;
        for(double[] i:d){
            if (i.length>max){
                max=i.length;
            }
        }
        this.rows = d.length;
        this.cols = max;
        this.data = new double[rows*cols];
        for(int i=0;i<this.rows;i++){
            for (int j=0;j<this.cols;j++){
                data[i*this.cols+j] = 0;
            }
        }
        int rowcounter=0;
        for (double[] i:d){
            int pos = rowcounter*this.cols;
            for (double j:i){
                this.data[pos]=j;
                pos+=1;
            }
            rowcounter+=1;
        }
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for(int i=0;i<rows;i++){
            buf.append("[");
            for(int j=i*cols;j<cols*i+cols;j++){
                buf.append(data[j]);
                if(j!=cols*i+cols-1){
                    buf.append(",");
                }
            }
            buf.append("]");
            if(i!=rows-1){
                buf.append(",");
            }
        }
        buf.append("]");
        return buf.toString();
    }

    double[][] asArray(){
        double[][] tab = new double[rows][cols];
        for (int i=0;i<data.length;i++){
            tab[(i-(i%cols))/this.cols][i%cols]=data[i];
        }
        return tab;
    }

    double get(int r,int c){
        return data[(r-1)*this.cols+c - 1];
    }
    void set (int r,int c, double value){
        data[(r-1)*this.cols+c - 1] = value;
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols) {
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d", rows, cols, newRows, newCols));
        }
        else{
            double[][] tab = new double[newRows][newCols];
            for (int i=0;i<data.length;i++){
                tab[(i-(i%newCols))/newCols][i%newCols]=data[i];
            }
            Matrix tmp = new Matrix(tab);
            this.data = tmp.data;
            this.rows = newRows;
            this.cols = newCols;
        }
    }

    int[] shape(){
        return new int[]{rows,cols};
    }

    Matrix add(double w){
        for (int i=0;i<data.length;i++){
            data[i]+=w;
        }
        return new Matrix(this.asArray());
    } // dodaje wartość do każdego elementu

    Matrix sub(double w){
        for (int i=0;i<data.length;i++){
            data[i]-=w;
        }
        return new Matrix(this.asArray());
    } // odejmuje wartośc od kazdego elementu

    Matrix mul(double w){
        for (int i=0;i<data.length;i++){
            data[i]*=w;
        }
        return new Matrix(this.asArray());
    } // mnoży kazdy element przez wartość

    Matrix div(double w){
        if (w!=0) {
            for (int i = 0; i < data.length; i++) {
                data[i] /= w;
            }
        }
        return new Matrix(this.asArray());
    }// dzieli kazdy element przez wartość


    Matrix add(Matrix m){
        int maxrows;
        int maxcols;
        if (m.rows> this.rows)
            maxrows = m.rows;
        else
            maxrows = this.rows;

        if (m.cols> this.cols)
            maxcols = m.cols;
        else
            maxcols = this.cols;



        Matrix matrix = new Matrix(maxrows,maxcols);
        double[][] tab=matrix.asArray();
        for (int i=0;i<this.rows;i++){
            for (int j=0;j<this.cols;j++){
                tab[i][j]+=this.asArray()[i][j];
            }
        }
        for (int i=0;i<m.rows;i++){
            for (int j=0;j<m.cols;j++){
                tab[i][j]+=m.asArray()[i][j];
            }
        }
        return new Matrix(tab);
    }

    Matrix sub(Matrix m){
        int maxrows;
        int maxcols;
        if (m.rows> this.rows)
            maxrows = m.rows;
        else
            maxrows = this.rows;

        if (m.cols> this.cols)
            maxcols = m.cols;
        else
            maxcols = this.cols;



        Matrix matrix = new Matrix(maxrows,maxcols);
        double[][] tab=matrix.asArray();
        for (int i=0;i<this.rows;i++){
            for (int j=0;j<this.cols;j++){
                tab[i][j]+=this.asArray()[i][j];
            }
        }
        for (int i=0;i<m.rows;i++){
            for (int j=0;j<m.cols;j++){
                tab[i][j]-=m.asArray()[i][j];
            }
        }
        return new Matrix(tab);
    }

    Matrix mul(Matrix m){
        int maxrows;
        int minrows;
        int mincols;
        int maxcols;

        if (m.rows> this.rows) {
            maxrows = m.rows;
            minrows = this.rows;
        }
        else{
            maxrows = this.rows;
            minrows = m.rows;
        }

        if (m.cols> this.cols){
            maxcols = m.cols;
            mincols = this.cols;
        }
        else{
            maxcols = this.cols;
            mincols = m.cols;
        }



        Matrix matrix = new Matrix(maxrows,maxcols);
        double[][] tab=matrix.asArray();
        for (int i=0;i<minrows;i++){
            for (int j=0;j<mincols;j++){
                tab[i][j]+=this.asArray()[i][j];
            }
        }
        for (int i=0;i<minrows;i++){
            for (int j=0;j<mincols;j++){
                tab[i][j]*=m.asArray()[i][j];
            }
        }
        return new Matrix(tab);
    }

    Matrix div(Matrix m){
        int maxrows;
        int minrows;
        int mincols;
        int maxcols;
        if (m.rows> this.rows) {
            maxrows = m.rows;
            minrows = this.rows;
        }
        else{
            maxrows = this.rows;
            minrows = m.rows;
        }

        if (m.cols> this.cols){
            maxcols = m.cols;
            mincols = this.cols;
        }
        else{
            maxcols = this.cols;
            mincols = m.cols;
        }



        Matrix matrix = new Matrix(maxrows,maxcols);
        double[][] tab=matrix.asArray();
        for (int i=0;i<this.rows;i++){
            for (int j=0;j<this.cols;j++){
                tab[i][j]+=this.asArray()[i][j];
            }
        }
        for (int i=0;i<minrows;i++){
            for (int j=0;j<mincols;j++){
                if(m.asArray()[i][j]!=0)
                    tab[i][j]/=m.asArray()[i][j];
                else{
                    return null;
                }
            }
        }
        return new Matrix(tab);
    }

    Matrix dot(Matrix m){
        if(this.cols==m.rows){
            double[][] tmp = new double[this.rows][m.cols];
            double[][] first = this.asArray();
            double[][] second = m.asArray();
            for (int e=0;e<this.rows;e++){
                for (int q=0;q<m.cols;q++){
                    int sum = 0;
                    for (int i=0;i<this.cols;i++){
                        sum+=first[e][i]*second[i][q];
                    }
                    tmp[e][q]=sum;
                }
            }
            return new Matrix(tmp);
        }
        return null;
    }

    double frobenius(){
        int sum=0;
        for (double i:data){
            sum+=i*i;
        }
        return sum;
    }

    Matrix getColumn(int i){
        if(this.cols<i+1) {
            throw new RuntimeException(String.format("Zły numer kolumny"));
        }
        else {
        double[][] first = this.asArray();
        double[][] tmp = new double[this.rows][1];
        for (int e=0;e<this.rows;e++){
            for (int q=0;q<this.cols;q++){
                if (q==i){
                    tmp[e][0]=first[e][q];
                }
            }
        }

        return new Matrix(tmp);
        }

    }

}