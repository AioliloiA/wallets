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

    /*

    public int createUser(String name) throws SQLException {

    }

    public void deleteUser(int userId) throws SQLException{

    }

    public List<User> findByName(String extract){

    }

    public void deleteByName(String exactName){

    }

    public void updateUser(int userId, String newName){

    }

    public void deleteUser(int userId){
      // delete wallets, then delete User
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<Wallet> getWallets() {
        return null;
    }

    */

    public static void main(String[] args) throws SQLException {

        UserDao dao = new UserDao();


        System.out.println(dao.findUserWithWallets(2));

    }
}
