import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Connect4Server extends UnicastRemoteObject implements Connect4 {
    private ArrayList<Player> players;
    private Board board;

    public Connect4Server() throws RemoteException {
        this.players = new ArrayList<>();
        this.board = new Board();
    }

    public static void main(String[] args) {
        try {
            Connect4Server server = new Connect4Server();
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // main(String[])

    @Override
    public void register(Player player) throws RemoteException {
        System.out.println("[SERVER] Registrando jogador...");

        if (this.players.size() >= 2) {
            System.err.println("[SERVER] ERRO: Limite de jogadores atingido");
        } else {
            this.players.add(player);

            System.out.println("[SERVER] Jogador registrado");

            startMatch();
        }
    } // register(Player)

    @Override
    public boolean mark(int column) throws RemoteException {
        return this.board.mark(column);
    } // mark(int)

    @Override
    public void endTurn(Symbol symbol) throws RemoteException {
        if (this.board.getWinner() != Symbol.EMPTY) {
            for (Player player : this.players) {
                player.endMatch(this.board);
            }
        } else {
            if (symbol == Symbol.Y) {
                this.players.get(Symbol.Y.value).beginTurn(this.board);
                this.players.get(Symbol.B.value).beginTurn(this.board);
            } else {
                this.players.get(Symbol.B.value).beginTurn(this.board);
                this.players.get(Symbol.Y.value).beginTurn(this.board);
            }
        }
    } // endTurn(Symbol)

    private void start() {
        System.out.println("[SERVER] Iniciando servidor do Connect - 4...");
        try {
            Naming.rebind(URL_SERVER, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("[SERVER] Servidor iniciado");
    } // start

    private void startMatch() {
        try {
            if (this.players.size() == 1) {
                this.players.get(Symbol.Y.value).waitForOpponent();
            } else {
                // 2 jogadores; iniciar o jogo
                this.players.get(Symbol.B.value).startMatch(this.board, Symbol.B);
                this.players.get(Symbol.Y.value).startMatch(this.board, Symbol.Y);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // startMatch
} // connect4Server