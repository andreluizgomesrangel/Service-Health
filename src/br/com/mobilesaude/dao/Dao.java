package br.com.mobilesaude.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import br.com.mobilesaude.connection.ConnectionFactory;

public class Dao {
	@Resource(mappedName = "java:/jdbc/WsHealthApp")
    private DataSource datasource;
	
	protected Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }
}
