package br.com.mobilesaude.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.mobilesaude.connection.ConnectionFactory;
import br.com.mobilesaude.resources.Service;

public class ServiceDao {

	private Connection connection;
	 
	   public ServiceDao() {
		   this.connection = new ConnectionFactory().getConnection();
	   }
	   
	   public void add(Service service) {
		     String sql = "insert into service " +
		             "(id,name,url,requestType,param)" +
		             " values (?,?,?,?,?)";
		     try {
		         // prepared statement para inserção
		         PreparedStatement stmt = connection.prepareStatement(sql);
		 
		         // seta os valores
		         stmt.setLong(  1,service.getId());
		         stmt.setString(2,service.getName());
		         stmt.setString(3,service.getUrl());
		         stmt.setString(4,service.getRequestType());
		         stmt.setString(5,service.getParam());
		         
		         
		         // executa
		         stmt.execute();
		         stmt.close();
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 }
	   
	   public List<Service> getLista() {
		   
		     try {
		         List<Service> services = new ArrayList<Service>();
		         PreparedStatement stmt = this.connection.
		        		 
		         prepareStatement("select * from service");
		         ResultSet rs = stmt.executeQuery();
		 
		         while (rs.next()) {
		             // criando o objeto Contato
		             Service service = new Service();
		             service.setId(rs.getLong("id"));
		             service.setName(rs.getString("name"));
		             service.setUrl(rs.getString("url"));
		             service.setRequestType(rs.getString("requestType"));
		             service.setParam(rs.getString("param"));
		             
		             // montando a data através do Calendar
		             /*Calendar data = Calendar.getInstance();
		             data.setTime(rs.getDate("dataNascimento"));
		             contato.setDataNascimento(data);*/
		 
		             // adicionando o objeto à lista
		             services.add(service);
		         }
		         rs.close();
		         stmt.close();
		         return services;
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 }
	
	   
	   public void updateTime(long id,  Timestamp time) {
		     String sql = "update service set lastRequest=? " +
		             "where id=?";
		     try {
		         PreparedStatement stmt = connection.prepareStatement(sql);
		         
		         stmt.setTimestamp(1, time );
		         
		         stmt.setLong(2, id);
		         
		         stmt.execute();
		         stmt.close();
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 }
	   
	   
	   public void update(Service service) {
		     String sql = "update service set name=?, requestType=?, param=?, " +
		             "url=? where id=?";
		     try {
		         PreparedStatement stmt = connection.prepareStatement(sql);
		         stmt.setString(1, service.getName());
		         stmt.setString(2, service.getRequestType());
		         stmt.setString(3, service.getParam());
		         stmt.setString(4, service.getUrl());
		         stmt.setLong(5, service.getId());
		         stmt.execute();
		         stmt.close();
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 }
	   
}
