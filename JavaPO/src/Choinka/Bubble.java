package Choinka;

import java.awt.*;

public class Bubble implements XmasShape {
    int x;
    int y;
    double scale = 1.0;
    Color lineColor;
    Color fillColor;

    Bubble(int x, int y){
        this(x,y,1.0);
    }
    Bubble(int x,int y,double scale){
        this.x = x;
        this.y = y;
        this.scale = scale;
    }
    Bubble(int x,int y,double scale,Color fillColor){
        this(x,y,scale);
        this.setFillColor(fillColor);
    }
    Bubble(int x,int y,double scale,Color fillColor,Color lineColor){
        this(x,y,scale,fillColor);
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
        g2d.fillOval(x,y,100,100);
        // ustaw kolor obramowania
        g2d.setColor(this.lineColor);
        g2d.drawOval(x,y,100,100);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
