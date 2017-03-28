package br.com.mobilesaude.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {
        try {
            return DriverManager.getConnection(
            		"jdbc:mysql://localhost/ping", "root", "123456"); //jdbc:mysql://ip/nome_do_banco
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
}

