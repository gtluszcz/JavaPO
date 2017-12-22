package Choinka;

import java.awt.*;

public class Light implements XmasShape {
    int x1;
    int y1;
    int x2;
    int y2;
    GradientPaint grad;



    Light(int x1,int y1,int x2,int y2,GradientPaint grad){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.grad = grad;
    }



    @Override
    public void render(Graphics2D g2d) {
        // ustaw kolor wypełnienia
        //GradientPaint grad = new GradientPaint(0,0,new Color(255,0,0),0,100, new Color(0,0,255),true);
        g2d.setPaint(grad);
        int x[]={x1,x2};
        int y[]={y1,y2};
        g2d.drawLine(x1,y1,x2,y2);

    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x1,y1);
        g2d.scale(1,1);
    }
}
