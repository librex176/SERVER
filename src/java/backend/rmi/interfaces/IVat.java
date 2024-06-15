/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package backend.rmi.interfaces;

//Libreries
import java.rmi.Remote;
import java.rmi.RemoteException;



/**
 *Interface: IVat
 * Type: interface
 * use: allow the use of RMI to calculate the IVA of the fruits
 */

public interface IVat extends Remote {
    
        //Method to calculate
   public double calculate(double price, double percent) throws RemoteException;
    
}
