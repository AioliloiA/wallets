package io.pax.cryptos.dao;

import io.pax.cryptos.domain.jdbc.SimpleWallet;
import io.pax.cryptos.domain.Wallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 06/02/2018.
 */
public class WalletDao {

  JdbcConnector connector = new JdbcConnector();


  public List<Wallet> listWallets() throws SQLException {

    List<Wallet> wallets = new ArrayList<>();
    Connection conn = this.connector.getConnection();
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM wallet");

    while (rs.next()) {
      String name = rs.getString("name");
      int id = rs.getInt("id");
      wallets.add(new SimpleWallet(id, name));
    }

    rs.close();
    stmt.close();
    conn.close();

    return wallets;
  }

  public int createWallet(int userId, String name) throws SQLException {

    String query = "INSERT INTO wallet (name, user_id) VALUES (?,?)";

    System.out.println(query);

    Connection conn = this.connector.getConnection();
    PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    stmt.setString(1, name);
    stmt.setInt(2, userId);


    stmt.executeUpdate();

    ResultSet keys = stmt.getGeneratedKeys();
    keys.next();
    int id = keys.getInt(1);

    stmt.close();
    conn.close();


    return id;


  }

  public int deleteWallet(int walletId) throws SQLException {

    String query = "DELETE FROM wallet WHERE id=?";

    System.out.println(query);

    Connection conn = this.connector.getConnection();
    PreparedStatement stmt = conn.prepareStatement(query);
    stmt.setInt(1, walletId);
    stmt.executeUpdate();

    stmt.close();
    conn.close();


    return walletId;
  }

  // A FAIRE
  public List<Wallet> findByName(String extract) throws SQLException {

    List<Wallet> wallets = new ArrayList<>();

    String query = "SELECT * FROM wallet WHERE name LIKE ?";

    Connection conn = this.connector.getConnection();
    PreparedStatement stmt = conn.prepareStatement(query);
    stmt.setString(1, "%"+extract+"%");
    ResultSet rs = stmt.executeQuery();

    while (rs.next()) {
      Wallet wallet = new SimpleWallet(rs.getInt("id"),rs.getString("name"));
      System.out.println("wallet créé à ajouter : "+wallet);
      wallets.add(wallet);
      System.out.println("liste de wallets : "+wallets);
    }

    stmt.close();
    conn.close();

    return wallets;
  }


  public void deleteByName(String name) throws SQLException {

  String query = "DELETE  FROM wallet WHERE name LIKE ?";

  Connection conn = this.connector.getConnection();
  PreparedStatement stmt = conn.prepareStatement(query);
  stmt.setString(1,name);

  stmt.executeUpdate();

  stmt.close();
    conn.close();

  }


  /**
   * @param walletId the id of the wallet
   * @param newName  the new name
   */
  public void updateWallet(int walletId, String newName) throws SQLException {

    String query = "UPDATE wallet SET name = ? WHERE id = ?";

    Connection conn = this.connector.getConnection();
    PreparedStatement stmt = conn.prepareStatement(query);
    stmt.setString(1,newName);
    stmt.setInt(2,walletId);

    stmt.executeUpdate();

    stmt.close();
    conn.close();


  }


  public void deleteAll(int userId) throws SQLException{

  String query = "DELETE  FROM wallet WHERE userd_id = ? ";
  Connection conn = this.connector.getConnection();
  PreparedStatement statement = conn.prepareStatement(query);
  statement.setInt(1,userId);

  statement.executeUpdate();

  statement.close();
  conn.close();


  }


  public static void main(String[] args) throws SQLException {

    WalletDao dao = new WalletDao();


    // dao.deleteWallet(30);

    //int id = dao.createWallet(2, "Hey erase me again");
    System.out.println(dao.listWallets());
    //System.out.println(dao.findByName("erase"));
    //dao.deleteByName("Nice");
    //dao.updateWallet(15,"Marron");

    System.out.println(dao.listWallets());
/*
    System.out.println(id);
    System.out.println(dao.findByName("Hey erase me"));
    */
  }

}
