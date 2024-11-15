public class Tabuleiro {
    private int[][] tabuleiro = new int[5][5]; // 0 = vazio, 1 = navio, 2 = atingido, 3 = disparo vazio
    private Navio[] navios = new Navio[3];
    private int navioCount = 0;

    public boolean posicionarNavio(Navio navio) {
        int x = navio.getX();
        int y = navio.getY();
        boolean horizontal = navio.isHorizontal();

        // Verifica se o navio cabe e a posição está livre
        for (int i = 0; i < 3; i++) {
            int posX = x + (horizontal ? 0 : i);
            int posY = y + (horizontal ? i : 0);
            if (posX >= 5 || posY >= 5 || tabuleiro[posX][posY] != 0) {
                return false;
            }
        }

        // Posiciona o navio
        for (int i = 0; i < 3; i++) {
            int posX = x + (horizontal ? 0 : i);
            int posY = y + (horizontal ? i : 0);
            tabuleiro[posX][posY] = 1;
        }
        navios[navioCount++] = navio;
        return true;
    }

    public boolean realizarDisparo(int x, int y) {
        if (tabuleiro[x][y] == 1) {
            tabuleiro[x][y] = 2; // Marca como atingido
            return true;    // Acertou
        } else if (tabuleiro[x][y] == 0) {
            tabuleiro[x][y] = 3; // Marca como tiro vazio
        }
        return false;       // Errou
    }

    public boolean todosNaviosDestruidos() {
        for (Navio navio : navios) {
            if (!navio.estaDestruido()) {
                return false;
            }
        }
        return true;
    }

    // Método para mostrar o tabuleiro no console com números nas linhas e colunas
    public void mostrarTabuleiro(boolean mostrarNavios) {
        System.out.print("   ");
        for (int j = 0; j < 5; j++) {
            System.out.print(j + "  ");
        }
        System.out.println();
        
        for (int i = 0; i < 5; i++) {
            System.out.print(i + "  ");  // Número da linha
            for (int j = 0; j < 5; j++) {
                if (tabuleiro[i][j] == 0) {
                    System.out.print(" ~ ");  // Água
                } else if (tabuleiro[i][j] == 1 && mostrarNavios) {
                    System.out.print(" N ");  // Navio
                } else if (tabuleiro[i][j] == 2) {
                    System.out.print(" X ");  // Navio atingido
                } else if (tabuleiro[i][j] == 3) {
                    System.out.print(" O ");  // Disparo vazio
                } else {
                    System.out.print(" ~ ");
                }
            }
            System.out.println();
        }
    }

    // Método que verifica se a coordenada já foi atacada
    public boolean coordenadaAtacada(int x, int y) {
        return tabuleiro[x][y] == 2 || tabuleiro[x][y] == 3;  // 2 = atingido, 3 = tiro vazio
    }
}
