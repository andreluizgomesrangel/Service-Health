package br.com.mobilesaude.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.sql.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.com.mobilesaude.connection.ConnectionFactory;
import br.com.mobilesaude.resources.Requisicao;
import br.com.mobilesaude.resources.Service;

public class RequisicaoDao {

	private Connection connection;
	 //mudanca no service health
	   public RequisicaoDao() {
		   this.connection = new ConnectionFactory().getConnection();
	   }
	   
	   public void add( Requisicao req ) {
		     String sql = "insert into requisicao " +
		             "(id,idService,details,response,requisicao,time)" +
		             " values (?,?,?,?,?,?)";
		     try {
		         // prepared statement para inserção
		         PreparedStatement stmt = connection.prepareStatement(sql);
		 
		         // seta os valores
		         stmt.setLong(1,req.getId());
		         stmt.setLong(2,req.getIdService());
		         //stmt.setTime(3, new Date( req.getTime() ) );
		         stmt.setString(3,req.getDetails());
		         stmt.setInt(4,req.getResponse());
		         stmt.setLong(5,req.getRequisicao());
		         
		         
		         
		       
		         Date date = new Date();
		         //Calendar cal = new GregorianCalendar();
		         
		         
		         Timestamp timestamp = new Timestamp(date.getTime());
		        // preparedStatement = connection.prepareStatement("SELECT * FROM tbl WHERE ts > ?");
		        // preparedStatement.setTimestamp(1, timestamp);
		         
		         stmt.setTimestamp(6, timestamp );
		         
		         // executa
		         stmt.execute();
		         stmt.close();
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 }
	   
	
	   public List<Requisicao> getLista() {
		   
		     try {
		         List<Requisicao> historics = new ArrayList<Requisicao>();
		         PreparedStatement stmt = this.connection.
		        		 
		         prepareStatement("select * from requisicao");
		         ResultSet rs = stmt.executeQuery();
		 
		         while (rs.next()) {
		             // criando o objeto Contato
		             Requisicao historic = new Requisicao();
		             historic.setId(rs.getLong("id"));
		             historic.setIdService(rs.getLong("idService"));
		             historic.setDetails(rs.getString("details"));
		             historic.setResponse(rs.getInt("response"));
		             historic.setRequisicao(rs.getLong("requisicao"));
		             
		             
		             
		             
		             
		             Timestamp timestamp = rs.getTimestamp("time");
		             java.util.Date date = timestamp; // You can just upcast.
		             
		             
		             historic.setTime(date);
		             // montando a data através do Calendar
		             /*Calendar data = Calendar.getInstance();
		             data.setTime(rs.getDate("time"));
		             historic.setTime(data);*/
		 
		             // adicionando o objeto à lista
		             historics.add(historic);
		         }
		         rs.close();
		         stmt.close();
		         return historics;
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 }
	   
	   
}
