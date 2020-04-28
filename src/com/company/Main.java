package com.company;


import java.awt.*;

import java.util.Locale;


public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.ROOT);

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                BorMapFrame fr = new BorMapFrame();
                fr.setSize(700, 500);
                fr.setLocationRelativeTo(null);
                fr.setVisible(true);
            }
        });
    }
}
