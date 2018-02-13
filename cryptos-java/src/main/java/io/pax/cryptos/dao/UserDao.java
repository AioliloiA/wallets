package io.pax.cryptos.dao;

import io.pax.cryptos.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 07/02/2018.
 */
public class UserDao{

    public User findUserWithWallets(int userId) throws SQLException {
        Connection connection = connector.getConnection();
        String query ="SELECT * FROM wallet w RIGHT JOIN user u ON w.user_id=u.id WHERE u.id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,userId);
        ResultSet resultSet = statement.executeQuery();

        User user = null;
        List<Wallet> wallets = new ArrayList<>();
        while (resultSet.next()){
            String username = resultSet.getString("u.name");

            user = new FullUser(userId,username,wallets);

            int walletId = resultSet.getInt("w.id");
            String walletName = resultSet.getString("w.name");

            if (walletId > 0){
                Wallet wallet = new SimpleWallet(walletId, walletName);
                wallets.add(wallet);
            }

        }

        resultSet.close();
        statement.close();
        connection.close();

        return user;
    }

    JdbcConnector connector = new JdbcConnector();

    public List<User> listUsers() throws SQLException {

        List<User> users = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");

        while(rs.next()) {
            String name = rs.getString("name");
            int id = rs.getInt("id");
            users.add(new SimpleUser(id, name) {
            });
        }

        rs.close();
        stmt.close();
        conn.close();
        return users;

    }


    public int createUser(String name) throws SQLException {
        String query = "INSERT INTO user (name) VALUES(?)";
        System.out.println(query);
        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1,name);
        stmt.executeUpdate();
        /*int rows = stmt.executeUpdate(query);
        if( rows != 1){
            throw new SQLException("Something wrong happened with :" + query);
        }*/
        ResultSet keys = stmt.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);
        stmt.close();
        conn.close();
        return  id;
    }

    public void deleteUser(int userId) throws SQLException{


    }

    public List<User> findByName(String extract){

        return null;
    }

    public void deleteByName(String exactName){

    }

    public void updateUser(int userId, String newName){

    }




    public int getId() {
        return 0;
    }


    public String getName() {
        return null;
    }


    public List<Wallet> getWallets() {
        return null;
    }


    public static void main(String[] args) throws SQLException {

        UserDao dao = new UserDao();

        System.out.println(dao.createUser("Hello"));
        //System.out.println(dao.findUserWithWallets(2));

    }
}
