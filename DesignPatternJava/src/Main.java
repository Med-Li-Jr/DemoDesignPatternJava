import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello and welcome!");
        int w = 800; // Largeur total de l'interface
        int h = 600; // Hauteur total de l'interface
        JFrame f = new JFrame();
        f.setSize(w, h);
        f.setTitle("Design Pattern");
        InterfaceUI interfaceUI = new InterfaceUI(f, w, h);
        f.add(interfaceUI);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }
}