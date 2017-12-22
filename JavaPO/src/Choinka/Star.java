package Choinka;

import java.awt.*;

public class Star implements XmasShape {
    int x;
    int y;
    double scale = 1.0;
    double rad;
    Color fillColor;

    Star(int x, int y){
        this(x,y,1.0,0);
    }
    Star(int x,int y,double scale,double rad){
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.rad = rad;
    }
    Star(int x,int y,double scale,Color fillColor,double rad){
        this(x,y,scale,rad);
        this.setFillColor(fillColor);
    }


    void setFillColor(Color color){
        this.fillColor = color;
    }

    @Override
    public void render(Graphics2D g2d) {
        // ustaw kolor wypełnienia
        g2d.setColor(this.fillColor);
        int x[]={0,308,981,366,660,0,-660,-366,-981,-308};
        int y[]={-1300,-600,-600,-200,350,0,350,-200,-600,-600};
        g2d.rotate(rad);

        g2d.fillPolygon(x,y,x.length);


    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
