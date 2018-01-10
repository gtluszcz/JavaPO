package Lab11;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.LocalTime;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;

public class ClockWithGui extends JPanel {
    LocalTime time = LocalTime.now();

    ClockWithGui(){
        new ClockThread().start();
    }

    class ClockThread extends Thread{
        @Override
        public void run() {
            for(;;){
                time = LocalTime.now();
                System.out.printf("%02d:%02d:%02d\n",time.getHour(),time.getMinute(),time.getSecond());

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        frame.setContentPane(new ClockWithGui());
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(getWidth()/2,getHeight()/2);

        for(int i=1;i<13;i++){
            AffineTransform at = new AffineTransform();
            at.rotate(2*Math.PI/12*i);
            Point2D src = new Point2D.Float(0,-120);
            Point2D trg = new Point2D.Float();
            at.transform(src,trg);
            if(i<10) {
                g2d.drawString(Integer.toString(i), (int) trg.getX()-4, (int) trg.getY()+4);
            }
            else {
                g2d.drawString(Integer.toString(i), (int) trg.getX()-8, (int) trg.getY()+4);
            }
        }

        for(int i=0;i<61;i++){
            AffineTransform saveAT3 = g2d.getTransform();
            g2d.rotate(i%60*2*Math.PI/60);
            if(i%5!=0)
                g2d.drawLine(0,-115,0,-125);
            g2d.setTransform(saveAT3);
        }

        AffineTransform saveAT3 = g2d.getTransform();
        g2d.rotate(time.getSecond()%60*2*Math.PI/60);
        g2d.drawLine(0,0,0,-100);
        g2d.setTransform(saveAT3);

        AffineTransform saveAT = g2d.getTransform();
        g2d.rotate(time.getHour()%12*2*Math.PI/12);
        g2d.setStroke(new BasicStroke(2, CAP_ROUND,JOIN_MITER));
        g2d.drawLine(0,0,0,-100);
        g2d.setTransform(saveAT);

        AffineTransform saveAT2 = g2d.getTransform();
        g2d.rotate(time.getMinute()%60*2*Math.PI/60);
        g2d.drawLine(0,0,0,-100);
        g2d.setTransform(saveAT2);



    }
}
