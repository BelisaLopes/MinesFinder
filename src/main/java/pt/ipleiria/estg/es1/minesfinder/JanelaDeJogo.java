package pt.ipleiria.estg.es1.minesfinder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
                botoes[coluna][linha] = new BotaoCampoMinado(linha, coluna);
                //pag 25
                botoes[coluna][linha].addActionListener(this::btnCampoMinadoActionPerformed);
                //
                botoes[coluna][linha].addMouseListener(mouseListener);

                botoes[coluna][linha].addKeyListener(keyListener);

                painelJogo.add(botoes[coluna][linha]);

            }
        }
        setContentPane(painelJogo);

        // Destrói esta janela, removendo-a completamente da memória.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
        pack();

        setVisible(true); // opcional. Pode optar por fazer depois.
    }
    public void btnCampoMinadoActionPerformed(ActionEvent e) {
        var botao = (BotaoCampoMinado) e.getSource();
        int x = botao.getLinha();
        int y = botao.getColuna();
        campoMinado.revelarQuadricula(x, y);
        actualizarEstadoBotoes();

        if (campoMinado.isJogoTerminado()) {
            if (campoMinado.isJogadorDerrotado()){
                JOptionPane.showMessageDialog(null, "Oh, rebentou uma mina", "Perdeu!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Parabéns. Conseguiu descobrir todas as minas em "+ (campoMinado.getDuracaoJogo()/1000)+" segundos", "Vitória", JOptionPane.INFORMATION_MESSAGE);
            }
            setVisible(false);
        }
    }

    private void actualizarEstadoBotoes() {
        for (int x = 0; x < campoMinado.getLargura(); x++) {
            for (int y = 0; y < campoMinado.getAltura(); y++) {
                botoes[x][y].setEstado(campoMinado.getEstadoQuadricula(x, y));
            }
        }
    }

    MouseListener mouseListener= new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {

        }
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() != MouseEvent.BUTTON3) {
                return;
            }
            var botao = (BotaoCampoMinado) e.getSource();
            var x = botao.getColuna();
            var y = botao.getLinha();
            var estadoQuadricula = campoMinado.getEstadoQuadricula(x, y);
            if (estadoQuadricula == CampoMinado.TAPADO) {
                campoMinado.marcarComoTendoMina(x, y);
            } else if (estadoQuadricula == CampoMinado.MARCADO) {
                campoMinado.marcarComoSuspeita(x, y);
            } else if (estadoQuadricula == CampoMinado.DUVIDA) {
                campoMinado.desmarcarQuadricula(x, y);
            }
            actualizarEstadoBotoes();
        }
        @Override
        public void mouseReleased(MouseEvent e) {

        }
        @Override public void mouseEntered(MouseEvent e) {

        }
        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

    KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }
        @Override
        public void keyPressed(KeyEvent e) {
            var botao = (BotaoCampoMinado) e.getSource();

            var linha = botao.getLinha();
            var coluna = botao.getColuna();

            var largura = campoMinado.getLargura();
            var altura = campoMinado.getAltura();

            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> botoes[coluna][--linha < 0 ? largura - 1 : linha].requestFocus();
                case KeyEvent.VK_DOWN -> botoes[coluna][(linha + 1) % largura].requestFocus();
                case KeyEvent.VK_LEFT -> botoes[--coluna < 0 ? altura - 1 : coluna][linha].requestFocus();
                case KeyEvent.VK_RIGHT -> botoes[(coluna + 1) % altura][linha].requestFocus();
                case KeyEvent.VK_M -> {
                    switch (campoMinado.getEstadoQuadricula(coluna, linha)) {
                        case CampoMinado.TAPADO -> campoMinado.marcarComoTendoMina(coluna, linha);
                        case CampoMinado.MARCADO -> campoMinado.marcarComoSuspeita(coluna, linha);
                        case CampoMinado.DUVIDA -> campoMinado.desmarcarQuadricula(coluna, linha);
                    }
                    actualizarEstadoBotoes();
                }
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {

        }
    };


}
