/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model;

/**
 *  Class: Fruit
 *  Type: Model
 *  Use: Allow the control of fruits.
 */
public class Fruit {
    
    //------------
    //Properties
    private int id;
    private String name;
    private int quantity;
    private double price;
   
    //---------------
    //Constructor
    //----------------
    
    public Fruit() {
    }

    public Fruit(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Fruit(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    
    
    
    //----------------
    //getters and setters

    public int getId() {
        return id;
    }

    
    public void setId(int id)
    {
        this.id = id;
    }

   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    
    
    
}
