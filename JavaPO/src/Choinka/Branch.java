package Choinka;

import java.awt.*;


public class Branch implements XmasShape {

    int x;
    int y;
    double scale = 1.0;
    boolean left = true;
    Color lineColor;
    Color fillColor;

    Branch(int x, int y,boolean left){
        this(x,y,1.0,left);
    }
    Branch(int x,int y,double scale,boolean left){
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.left=left;
    }
    Branch(int x,int y,double scale,boolean left,Color fillColor){
        this(x,y,scale,left);
        this.setFillColor(fillColor);
    }
    Branch(int x,int y,double scale,boolean left,Color fillColor,Color lineColor){
        this(x,y,scale,left,fillColor);
        this.setLineColor(lineColor);
    }

    void setLineColor(Color color){
        this.lineColor = color;
    }
    void setFillColor(Color color){
        this.fillColor = color;
    }

    @Override
    public void render(Graphics2D g2d) {
        // ustaw kolor wypełnienia
        g2d.setColor(this.fillColor);
        if (left){
            int x[]={-400,0,0};
            int y[]={200,200,0};
            g2d.fillPolygon(x,y,x.length);
        }else {
            int x[]={0,0,400};
            int y[]={0,200,200};
            g2d.fillPolygon(x,y,x.length);
        }
        // ustaw kolor obramowania
        g2d.setColor(this.lineColor);

    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}


