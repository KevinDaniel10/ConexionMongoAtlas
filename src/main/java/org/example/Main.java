package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Registro");
        frame.setContentPane(new Registro().registro);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}