import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Player extends Remote {
    public void waitForOpponent() throws RemoteException;

    public void startMatch(Board board, Symbol symbol) throws RemoteException;

    public void beginTurn(Board board) throws RemoteException;

    public void endMatch(Board board) throws RemoteException;
}