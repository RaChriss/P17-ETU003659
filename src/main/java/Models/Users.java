package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import Utils.BaseObject;

public class Users extends BaseObject {

    private String username;
    private String password;
    private String email;

    public Users() {
        super();
    }

    public Users(int id, String username, String password, String email) {
        super(id);
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static boolean isMatch(String givenPassword, String username, Connection conn) throws Exception {
        try {
            Users user = getByUsername(username, conn);
            if (user != null) {
                return user.getPassword().equals(givenPassword);
            } else {
                throw new Exception("User not found with username: " + username);
            }
        } catch (Exception e) {
            throw new Exception("Error checking password: " + e.getMessage());
        }
    }


    public static Users getByUsername(String username, Connection conn) throws Exception {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM web_users WHERE username = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id_user");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                return new Users(id, username, password, email);
            } else {
                throw new Exception("User not found with username: " + username);
            }
        } catch (Exception e) {
            throw new Exception("Error retrieving user: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }

    @Override
    public void save(Connection conn) throws Exception {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO web_users (username, password, email) VALUES (?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, this.username);
            statement.setString(2, this.password);
            statement.setString(3, this.email);
            statement.executeUpdate();
            System.out.println("User saved successfully.");
        } catch (Exception e) {
            throw new Exception("Error saving user: " + e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static Users getById(int id, Connection conn) throws Exception {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM web_users WHERE id_user = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int ID =  resultSet.getInt("id_user");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                return new Users(ID, username, password, email);
            } else {
                throw new Exception("User not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new Exception("Error retrieving user: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }

    @Override
    public void delete(Connection conn) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void update(Connection conn) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public BaseObject[] getAll(Connection conn) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

}