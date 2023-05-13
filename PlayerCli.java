import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class PlayerCli extends UnicastRemoteObject implements Player {
    private Connect4 server;
    private Symbol symbol;

    public PlayerCli() throws RemoteException {
        try {
            this.server = (Connect4) Naming.lookup(Connect4.URL_SERVER);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Bem-vindo ao Connect - 4 com RMI ===\n");
        try {
            PlayerCli client = new PlayerCli();
            client.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // main(String[] args)

    @Override
    public void waitForOpponent() throws RemoteException {
        System.out.println("Aguardando oponente...\n");
    } // waitForOpponent

    @Override
    public void startMatch(Board board, Symbol symbol) throws RemoteException {
        this.symbol = symbol;
        System.out.printf("Partida Iniciada. Você será o \"%c\".\n\n", this.symbol.label);

        beginTurn(board);
    } // startMatch(Board, Symbol)

    @Override
    public void beginTurn(Board board) throws RemoteException {
        System.out.println(board + "\n");
        if (this.symbol == board.getTurn()) {
            play();
            endTurn();
        } else {
            System.out.println("Oponente jogando...");
        }
    } // beginTurn(Board)

    @Override
    public void endMatch(Board board) throws RemoteException {
        if (board.getWinner() == this.symbol) {
            System.out.println("Parabéns! Você venceu!\n\n");
        } else {
            System.out.println("Que pena, você perdeu.\n\n");
        }
    } // endMatch(Board)

    private void execute() {
        try {
            this.server.register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // execute

    private void play() {
        try {
            boolean valid = true;
            do {
                int column = getPosition();

                valid = this.server.mark(column);
                if (!valid) {
                    System.out.printf("Coluna %d cheia!\n\n", column);
                }
            } while (!valid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // play

    private int getPosition() {
        int position = -1;

        Scanner scanner = new Scanner(System.in);
        boolean valid = true;

        do {
            System.out.print("Informe a coluna (entre 1 e 7): ");
            position = scanner.nextInt();

            valid = position >= 1 && position <= 7;
            if (!valid) {
                System.out.printf("Posição %d é inválida!\n\n", position);
            }
        } while (!valid);

        return position;
    } // getPosition

    private void endTurn() {
        try {
            this.server.endTurn(this.symbol);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // endTurn()
} // PlayerCli
