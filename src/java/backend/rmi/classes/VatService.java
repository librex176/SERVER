
package backend.rmi.classes;



import backend.rmi.interfaces.IVat;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;

public class VatService extends UnicastRemoteObject implements IVat {

    public VatService() throws RemoteException {
        super();
    }

    @Override
    public double calculate(double price, double percent) throws RemoteException {
        return price * (percent / 100);
    }
    
    public static void startRmiServer() {
        try {
            VatService vatService = new VatService();
            Registry registry = LocateRegistry.createRegistry(1101);
            registry.rebind("vat", vatService); // Ensure the name is "vat"
            System.out.println("VatService is ready.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}