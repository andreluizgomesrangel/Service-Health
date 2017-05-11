package br.com.mobilesaude.dao;

import java.sql.Connection;

import br.com.mobilesaude.connection.ConnectionFactory;

public class Dao {

	private Connection connection;
	 //mudanca no service health
	   public Dao() {
		   this.connection = new ConnectionFactory().getConnection();
	   }
	
}
