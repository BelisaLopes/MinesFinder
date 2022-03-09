package pt.ipleiria.estg.es1.minesfinder;

import javax.swing.*;

public class MinesFinder extends JFrame {

    private JPanel painelPrincipal;
    private JButton jogoFácilButton;
    private JButton jogoMédioButton;
    private JButton jogoDifícilButton;
    private JButton sairButton;

    public MinesFinder(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();
    }
    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);
    }
}
