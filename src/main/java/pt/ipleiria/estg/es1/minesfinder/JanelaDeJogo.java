package pt.ipleiria.estg.es1.minesfinder;

import javax.swing.*;
import java.awt.*;

public class JanelaDeJogo extends JFrame{
    private JPanel painelJogo;
    private BotaoCampoMinado[][] botoes;
    private CampoMinado campoMinado;

    public JanelaDeJogo(CampoMinado campoMinado) {
        this.campoMinado = campoMinado;

        var largura = campoMinado.getLargura();
        var altura = campoMinado.getAltura();

        this.botoes = new BotaoCampoMinado[largura][altura];

        painelJogo.setLayout(new GridLayout(altura, largura));

        // Criar e adicionar os botões à janela
        for (int linha = 0; linha < altura; ++linha) {
            for (int coluna = 0; coluna < largura; ++coluna) {
                botoes[linha][coluna] = new BotaoCampoMinado();
                painelJogo.add(botoes[linha][coluna]);
            }
        }
        setContentPane(painelJogo);

        // Destrói esta janela, removendo-a completamente da memória.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
        pack();

        setVisible(true); // opcional. Pode optar por fazer depois.
    }
}
