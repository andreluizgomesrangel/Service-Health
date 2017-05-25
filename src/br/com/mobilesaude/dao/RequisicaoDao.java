package br.com.mobilesaude.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.com.mobilesaude.connection.ConnectionFactory;
import br.com.mobilesaude.resources.LastRequest;
import br.com.mobilesaude.resources.Requisicao;
import br.com.mobilesaude.resources.Service;


public class RequisicaoDao extends Dao{

	@EJB
	ServiceDao sdao;
	
	public RequisicaoDao() {
		//fullGroupMode();
	}

	public List<Requisicao> getLista() {

		try {
			List<Requisicao> historics = new ArrayList<Requisicao>();
			PreparedStatement stmt = this.getConnection().

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
				java.util.Date date = timestamp;

				historic.setTime(date);
				historics.add(historic);

			}
			rs.close();
			stmt.close();
			return historics;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void add(Requisicao req) {
		String sql = "insert into requisicao " + "(idService,details,response,requisicao,time)" + " values (?,?,?,?,?)";
		// System.out.println(">>> insert dao");
		try {
			// prepared statement para inserção
			PreparedStatement stmt = getConnection().prepareStatement(sql);

			// seta os valores
			stmt.setLong(1, req.getIdService());
			stmt.setString(2, req.getDetails());
			stmt.setInt(3, req.getResponse());
			stmt.setLong(4, req.getRequisicao());

			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			stmt.setTimestamp(5, timestamp);

			sdao.updateTime(req.getIdService(), timestamp);

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Requisicao> getDay(String day, long id) {

		try {
			List<Requisicao> historics = new ArrayList<Requisicao>();
			PreparedStatement stmt = this.getConnection().

					prepareStatement("select * from requisicao WHERE idService='" + id
							+ "' AND DATE(requisicao.time) = '" + day + "'");
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

	public void fullGroupMode() {

		try {
			List<LastRequest> req = new ArrayList<LastRequest>();
			PreparedStatement stmt = getConnection().prepareStatement("SET sql_mode = ''");
			ResultSet rs = stmt.executeQuery();

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<LastRequest> getLastRequests() {

		try {
			List<LastRequest> req = new ArrayList<LastRequest>();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT GRP.ID AS ID, GRP.NOME AS NOME, GRP.TIPO AS TIPO , req.details AS RESPOSTA, req.time AS HORA ");
			sql.append("FROM ( ");
			sql.append("SELECT service.id AS ID, service.name AS NOME, service.requestType AS TIPO, MAX(ping.requisicao.id) as idrequisicao FROM ping.service ");
			sql.append("INNER JOIN ping.requisicao ");
			sql.append("WHERE service.id = requisicao.idService  ");
			sql.append("GROUP BY ID, NOME ) GRP, ");
			sql.append("Requisicao req ");
			sql.append("where grp.idrequisicao = req.id ");
			
			PreparedStatement stmt = getConnection().prepareStatement(sql.toString());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				LastRequest r = new LastRequest();
				r.setNome(rs.getString("NOME"));
				r.setResposta(rs.getString("RESPOSTA"));
				r.setResponse(rs.getInt("RCODE"));
				Timestamp timestamp = rs.getTimestamp("HORA");
				java.util.Date date = timestamp;
				r.setHora(date);
				req.add(r);
			}
			rs.close();
			stmt.close();
			return req;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
