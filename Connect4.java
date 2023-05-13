import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Connect4 extends Remote {

    public static final String URL_SERVER = "rmi://127.0.0.1/Connect4";

    void register(Player player) throws RemoteException;

    boolean mark(int coluna) throws RemoteException;

    void endTurn(Symbol symbol) throws RemoteException;

}
