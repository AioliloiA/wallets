package io.pax.cryptos.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by AELION on 09/02/2018.
 */
public class JdbcConnector {

    DataSource dataSource = this.createDatasSource();

    public JdbcConnector() {


    }

    private DataSource createDatasSource() {
        Object dataSource;
        try {
            InitialContext e = new InitialContext();
            dataSource = (DataSource)e.lookup("java:/cryptos");
        } catch (NamingException var4) {
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setUser("root");
            mysqlDataSource.setPassword("");
            mysqlDataSource.setServerName("localhost");
            mysqlDataSource.setDatabaseName("cryptos");
            mysqlDataSource.setPort(3306);
            dataSource = mysqlDataSource;
        }

        return (DataSource)dataSource;
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

}
