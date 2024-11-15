import java.util.Scanner;

public class Jogo {
    private Tabuleiro tabuleiroJogador;
    private Tabuleiro tabuleiroComputador;
    private Scanner scanner;

    public Jogo() {
        tabuleiroJogador = new Tabuleiro();
        tabuleiroComputador = new Tabuleiro();
        scanner = new Scanner(System.in);
    }

    public void configurarNaviosJogador() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Posicione o navio " + (i + 1) + ": ");
            System.out.print("Digite a linha (0 a 4): ");
            int x = scanner.nextInt();
            System.out.print("Digite a coluna (0 a 4): ");
            int y = scanner.nextInt();
            System.out.print("Digite a orientação (0 para vertical, 1 para horizontal): ");
            boolean horizontal = scanner.nextInt() == 1;

            Navio navio = new Navio(x, y, horizontal);
            while (!tabuleiroJogador.posicionarNavio(navio)) {
                System.out.println("Posição inválida. Tente novamente.");
                System.out.print("Digite a linha (0 a 4): ");
                x = scanner.nextInt();
                System.out.print("Digite a coluna (0 a 4): ");
                y = scanner.nextInt();
                System.out.print("Digite a orientação (0 para vertical, 1 para horizontal): ");
                horizontal = scanner.nextInt() == 1;
                navio = new Navio(x, y, horizontal);
            }
            tabuleiroJogador.mostrarTabuleiro(true);
        }
    }

    public void iniciar() {
        configurarNaviosJogador();
        configurarNaviosComputador();

        while (true) {
            System.out.println("\nSeu tabuleiro:");
            tabuleiroJogador.mostrarTabuleiro(true);
            System.out.println("\nTabuleiro do Computador:");
            tabuleiroComputador.mostrarTabuleiro(false);

            executarTurnoJogador();
            if (tabuleiroComputador.todosNaviosDestruidos()) {
                System.out.println("Você venceu!");
                break;
            }

            executarTurnoComputador();
            if (tabuleiroJogador.todosNaviosDestruidos()) {
                System.out.println("Computador venceu!");
                break;
            }
        }
    }

    private void configurarNaviosComputador() {
        for (int i = 0; i < 3; i++) {
            int x, y;
            boolean horizontal;
            Navio navio;
            do {
                x = (int) (Math.random() * 5);
                y = (int) (Math.random() * 5);
                horizontal = Math.random() > 0.5;
                navio = new Navio(x, y, horizontal);
            } while (!tabuleiroComputador.posicionarNavio(navio));
        }
    }

    private void executarTurnoJogador() {
        int x, y;
        do {
            System.out.print("Seu turno! Digite as coordenadas para disparo (x y): ");
            x = scanner.nextInt();
            y = scanner.nextInt();

            // Valida se a coordenada já foi jogada
            if (tabuleiroComputador.coordenadaAtacada(x, y)) {
                System.out.println("Você já jogou nessas coordenadas. Tente novamente.");
            }
        } while (tabuleiroComputador.coordenadaAtacada(x, y));

        boolean acertou = tabuleiroComputador.realizarDisparo(x, y);
        System.out.println(acertou ? "Acertou!" : "Errou!");
    }

    private void executarTurnoComputador() {
        int x, y;
        do {
            x = (int) (Math.random() * 5);
            y = (int) (Math.random() * 5);
        } while (tabuleiroJogador.coordenadaAtacada(x, y));

        boolean acertou = tabuleiroJogador.realizarDisparo(x, y);
        System.out.println(acertou ? "Computador acertou!" : "Computador errou!");
    }
}
