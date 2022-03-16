package pt.ipleiria.estg.es1.minesfinder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
        pack();

        sairButton.addActionListener(this::btnSairActionPerformed);

        jogoFácilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var janela = new JanelaDeJogo(new CampoMinado(9,9,10));
                janela.setVisible(true);
            }
        });

        jogoMédioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var janela = new JanelaDeJogo(new CampoMinado(16,16,40));
                janela.setVisible(true);
            }
        });

        jogoDifícilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var janela = new JanelaDeJogo(new CampoMinado(16,30,90));
                janela.setVisible(true);
            }
        });
    }

    private void btnSairActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);
    }
}
