import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Connect4 extends Remote {
    public static final String URL_SERVER = "rmi://127.0.0.1/Connect4";

    void registrar(Player jogador) throws RemoteException;

    boolean marcar(int linha, int coluna) throws RemoteException;

    void encerrarTurno(Symbols simbolo) throws RemoteException;
}
