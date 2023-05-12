import java.io.Serializable;

public class GameBoard implements Serializable {
    private final int ROWS = 6;
    private final int COLUMNS = 7;

    private Symbols[][] estado;
    private Symbols turno;
    private Symbols vencedor;

    public GameBoard() {
        estado = new Symbols[COLUMNS][ROWS];
        turno = Symbols.Y;
        vencedor = Symbols.VAZIO;

        reset();
    } // fim do construtor

    public Symbols getTurno() {
        return this.turno;
    } // fim do metodo getTurno()

    public Symbols getVencedor() {
        return this.vencedor;
    } // fim do metodo getVencedor()

    public void reset() {
        for (int linha = 0; linha < COLUMNS; linha++) {
            for (int coluna = 0; coluna < ROWS; coluna++) {
                this.estado[COLUMNS][ROWS] = Symbols.VAZIO;
            }
        }
    } // fim do metodo reset()

    public boolean marcar(int linha, int coluna) {
        // diminui 1 dos valores de posição para adequar aos índices da matriz
        int idxLinha = linha - 1;
        int idxColuna = coluna - 1;

        boolean valido = this.estado[idxLinha][idxColuna] == Symbols.VAZIO;

        if (valido) {
            this.estado[idxLinha][idxColuna] = this.turno;
            this.turno = (this.turno == Symbols.Y) ? Symbols.B : Symbols.Y;
            verificarFimDeJogo();
        }

        return valido;
    } // fim do metodo marcar(int, int)

    @Override
    public String toString() {
        final String formatoCasa = " %c |";

        StringBuilder builder = new StringBuilder();

        for (int linha = 0; linha < TAMANHO; linha++) {
            builder.append("|");
            for (int coluna = 0; coluna < TAMANHO; coluna++) {
                builder.append(String.format(formatoCasa, this.estado[linha][coluna].rotulo));
            }
            builder.append("\n");
        }

        return builder.toString();
    } // fim do metodo toString()

    private void verificarFimDeJogo() {
        // verificacao da sequencia horizontal
        for (int linha = 0; linha < TAMANHO; linha++) {
            if ((this.estado[linha][0] != Simbolo.VAZIO) && (this.estado[linha][0] == this.estado[linha][1])
                    && (this.estado[linha][1] == this.estado[linha][2])) {
                this.vencedor = this.estado[linha][0];
            }
        }

        // verificacao de sequencia vertical
        for (int coluna = 0; this.vencedor == Simbolo.VAZIO && coluna < TAMANHO; coluna++) {
            if ((this.estado[0][coluna] != Simbolo.VAZIO) && (this.estado[0][coluna] == this.estado[1][coluna])
                    && (this.estado[1][coluna] == this.estado[2][coluna])) {
                this.vencedor = this.estado[0][coluna];
            }
        }

        // verificacao de sequencia nas diagonais
        if ((this.vencedor == Simbolo.VAZIO) && (this.estado[1][1] != Simbolo.VAZIO)) {
            if ((this.estado[0][0] == this.estado[1][1]) && (this.estado[1][1] == this.estado[2][2])
                    || (this.estado[2][0] == this.estado[1][1]) && (this.estado[1][1] == this.estado[0][2])) {
                this.vencedor = this.estado[1][1];
            }
        }
    } // fim do metodo verificarFimDeJogo()

} // fim da classe Tabuleiro
