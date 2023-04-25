package main;
import java.awt.*;
import javax.swing.*;

public class App {

    public static JFrame frame;

    public static void main(String[] args) {
        // Crear la ventana principal
        frame = new JFrame("Los tilines Pixel Art");

        //configurations
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setBackground(Color.darkGray);

        //system
        Menu menu = new Menu();

        //adding components
        frame.add(menu, BorderLayout.CENTER);


        // Mostrar la ventana
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.requestFocus();
    }
}


