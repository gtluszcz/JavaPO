package Choinka;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class DrawPanel extends JPanel {
    List<XmasShape> shapes = new ArrayList<>();

    DrawPanel(){

        this.addBranches();
        this.addBubbles();
        this.addLights();
        this.addStars();
        setBackground(Color.cyan);
    }

    public void addBranches(){

        this.shapes.add(new Branch(500,400,0.7,true,new Color(35,128,37),Color.black));
        this.shapes.add(new Branch(500,400,0.7,false,new Color(35,128,37),Color.black));

        this.shapes.add(new Branch(500,325,0.6,true,new Color(35,128,37),Color.black));
        this.shapes.add(new Branch(500,325,0.6,false,new Color(35,128,37),Color.black));

        this.shapes.add(new Branch(500,260,0.5,true,new Color(35,128,37),Color.black));
        this.shapes.add(new Branch(500,260,0.5,false,new Color(35,128,37),Color.black));

        this.shapes.add(new Branch(500,200,0.4,true,new Color(35,128,37),Color.black));
        this.shapes.add(new Branch(500,200,0.4,false,new Color(35,128,37),Color.black));

        this.shapes.add(new Branch(500,150,0.3,true,new Color(35,128,37),Color.black));
        this.shapes.add(new Branch(500,150,0.3,false,new Color(35,128,37),Color.black));
    }

    public void addBubbles(){
        this.shapes.add(new Bubble(480,300,0.4,Color.blue,Color.black));
        this.shapes.add(new Bubble(270,250,0.4,Color.red,Color.black));
        this.shapes.add(new Bubble(320,170,0.4,Color.yellow,Color.black));
        this.shapes.add(new Bubble(270,350,0.4,Color.orange,Color.black));
        this.shapes.add(new Bubble(340,280,0.4,Color.red,Color.black));
        this.shapes.add(new Bubble(400,130,0.4,Color.green,Color.black));
        this.shapes.add(new Bubble(440,350,0.4,Color.yellow,Color.black));



    }
    public void addLights(){
        this.shapes.add(new Light(185,195,400,345,new GradientPaint(0,0,new Color(255,0,0),0,100, new Color(0,0,255),true)));
        this.shapes.add(new Light(215,148,420,296,new GradientPaint(0,0,new Color(255,0,255),0,100, new Color(0,255,255),true)));
        this.shapes.add(new Light(230,85,350,193,new GradientPaint(0,0,Color.yellow,0,100, new Color(255,0,255),true)));


    }
    public void addStars(){
        this.shapes.add(new Star(500,150,0.08,Color.yellow,0));
        this.shapes.add(new Star(500,300,0.01,Color.yellow,4));
        this.shapes.add(new Star(580,330,0.01,Color.yellow,19));
        this.shapes.add(new Star(540,460,0.01,Color.yellow,19));
        this.shapes.add(new Star(460,510,0.01,Color.yellow,12));



    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(XmasShape s:shapes){
            s.draw((Graphics2D)g);
        }
    }

}