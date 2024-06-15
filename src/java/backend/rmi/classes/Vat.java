
package backend.rmi.classes;



import backend.rmi.interfaces.IVat;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Vat extends UnicastRemoteObject implements IVat {

    public Vat() throws RemoteException {
        super();
    }

    @Override
    public double calculate(double price, double percent) throws RemoteException {
        return price * (percent / 100);
    }
}