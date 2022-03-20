package pt.ipleiria.estg.es1.minesfinder;

import javax.swing.*;
import java.awt.*;

public class BotaoCampoMinado extends JButton {
    private int estado; //atributo

    //pag26
    private int linha;
    private int coluna;

    //construtor
    public BotaoCampoMinado(int linha, int coluna) {
        this.estado = CampoMinado.TAPADO;

        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setEstado(int estado) {
        this.estado = estado;
        switch (estado) {
            case CampoMinado.VAZIO:
                setText("");
                setBackground(Color.LIGHT_GRAY);
                break;
            case CampoMinado.TAPADO:
                setText("");
                setBackground(null);
                break;
            case CampoMinado.DUVIDA:
                setText("?");
                setBackground(Color.YELLOW);
                break;
            case CampoMinado.MARCADO:
                setText("!");
                setBackground(Color.RED);
                break;
            case CampoMinado.REBENTADO:
                setText("*");
                setBackground(Color.ORANGE);
                break;
            // Complete o código que falta …
            default:
                setText(String.valueOf(estado));
                setBackground(Color.LIGHT_GRAY);
        }
    }

}
