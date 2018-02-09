package io.pax.cryptos.dao;

import io.pax.cryptos.domain.SimpleUser;
import io.pax.cryptos.domain.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 07/02/2018.
 */
public class UserDao{



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
}
