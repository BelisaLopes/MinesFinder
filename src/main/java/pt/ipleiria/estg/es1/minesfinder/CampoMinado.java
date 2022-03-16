package pt.ipleiria.estg.es1.minesfinder;

import java.util.Random;

public class CampoMinado {
    //posição se tem mina ou não
    private boolean[][] minas;
    //constantes
    public static final int VAZIO = 0;
    /* de 1 a 8 são o número de minas à volta */
    public static final int TAPADO = 9;
    public static final int DUVIDA = 10;
    public static final int MARCADO = 11;
    public static final int REBENTADO = 12;

    public boolean primeiraJogada;
    public boolean jogoTerminado;
    public boolean jogadorDerrotado;

    //estado da quadricula
    private int[][] estado;

    //largura, altura e número de minas no campo de minas
    private int largura;
    private int altura;
    private int numMinas;

    public CampoMinado(int largura, int altura, int numMinas) {
        this.largura = largura; //this refere-se ao atributo
        this.altura = altura;
        this.numMinas = numMinas;
        primeiraJogada = true;
        jogoTerminado = false;
        jogadorDerrotado = false;

        minas = new boolean[largura][altura]; // Valores começam a false
        estado = new int[largura][altura]; // Valores começam a 0

        //inicializar os valores a estado TAPADO
        for (var x = 0; x < largura; ++x) {
            for (var y = 0; y < altura; ++y) {
                estado[x][y] = TAPADO;
            }
        }
    }

    public int getEstadoQuadricula(int x, int y) {
        return estado[x][y];
    }

    public boolean hasMina(int x, int y) {
        return minas[x][y];
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public boolean isJogoTerminado() {
        return jogoTerminado;
    }

    public boolean isJogadorDerrotado() {
        return jogadorDerrotado;
    }

    //para as minas aparecerem em posições aleatórias
    private void colocarMinas(int exceptoX, int exceptoY) {
        var aleatorio = new Random();
        var x = 0; var y = 0;

        for (var i = 0; i < numMinas; ++i) {
            do {
                x = aleatorio.nextInt(largura);
                y = aleatorio.nextInt(altura);
            } while (minas[x][y] || (x == exceptoX && y == exceptoY));
            minas[x][y] = true;
        }
    }

    public void revelarQuadricula(int x, int y) {
        if (jogoTerminado || estado[x][y] < TAPADO) {
            return;
        }
        if (primeiraJogada) {
            primeiraJogada = false;
            colocarMinas(x, y);
        }
        //TODO
        if(minas[x][y]){
            estado[x][y] = REBENTADO;
            jogoTerminado = true;
            System.out.println("Perdeu o jogo");
        }
    }

}
