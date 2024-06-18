
package backend.dao;

import backend.model.Fruit;
import java.sql.SQLException;
import backend.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
/**
 * Class: FruitDao
 * Type: DAO
 * Use: CRUD of fruit table
 */
public class FruitDAO {
    
    
    //AddFruit: Store the fruits in the database
    
    public void addFruit(Fruit fruit) throws SQLException {
        
        // SQL statement
        String sql = "INSERT INTO fruit (name, quantity, price) VALUES (?, ?, ?)";
        
        // Execute SQL statement
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, fruit.getName());
            stmt.setInt(2, fruit.getQuantity());
            stmt.setDouble(3, fruit.getPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error inserting fruit: " + e.getMessage(), e);
        }
    }
   // Retrieve all fruits from the database
    public List<Fruit> getAllFruits() throws SQLException {
        List<Fruit> fruits = new ArrayList<>();
        String sql = "SELECT * FROM fruit";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Fruit fruit = new Fruit();
                fruit.setId(rs.getInt("id"));
                fruit.setName(rs.getString("name"));
                fruit.setQuantity(rs.getInt("quantity"));
                fruit.setPrice(rs.getDouble("price"));
                fruits.add(fruit);
            }
        }
        return fruits;
    }
    
    
    //Retrieve a single fruit informatioon
public Fruit getAFruitById(int id) throws SQLException {
    Fruit fruit = null;
    
    String sql = "SELECT * FROM fruit WHERE id = ?";
    
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
         
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                fruit = new Fruit();
                fruit.setName(rs.getString("name"));
                fruit.setQuantity(rs.getInt("quantity"));
                fruit.setPrice(rs.getDouble("price"));
            }
        }
    }
    
    return fruit;
}
    

    // Update an existing fruit in the database
    public void updateFruit(Fruit fruit) throws SQLException {
        // SQL statement
        String sql = "UPDATE fruit SET name = ?, quantity = ?, price = ? WHERE id = ?";
        
        // Execute SQL statement
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, fruit.getName());
            stmt.setInt(2, fruit.getQuantity());
            stmt.setDouble(3, fruit.getPrice());
            stmt.setInt(4, fruit.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error updating fruit: " + e.getMessage(), e);
        }
    }

    // Delete a fruit from the database
    public void deleteFruit(int id) throws SQLException {
        // SQL statement
        String sql = "DELETE FROM fruit WHERE id = ?";
        
        // Execute SQL statement
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error deleting fruit: " + e.getMessage(), e);
        }
    }
    
    
     
}
