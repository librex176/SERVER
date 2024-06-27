package backend.dao;

import backend.model.User;
import backend.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User validateUser(String username, String password) throws SQLException, ClassNotFoundException {
        User user = null;
        String sql = "SELECT * FROM usuarios WHERE nombre = ? AND password = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("nombre"));
                user.setPassword(resultSet.getString("password"));
                user.setUserTypeId(resultSet.getInt("id_tipo_usuario"));
            }
        }
        return user;
    }
}
