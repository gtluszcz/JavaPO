package Choinka;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        DrawPanel panel1 = new DrawPanel();





        JFrame frame = new JFrame("Choinka");
        frame.setContentPane(panel1);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);

    }
}
