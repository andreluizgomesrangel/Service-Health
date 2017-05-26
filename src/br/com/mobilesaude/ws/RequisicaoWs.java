package br.com.mobilesaude.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.mobilesaude.dao.RequisicaoDao;
import br.com.mobilesaude.dao.ServiceDao;
import br.com.mobilesaude.resources.LastRequest;
import br.com.mobilesaude.resources.Requisicao;
import br.com.mobilesaude.resources.Service;

@SuppressWarnings({ "unused" })
@Path("/ws/servico/requisicao")
public class RequisicaoWs {

	@EJB
	RequisicaoDao hdao;
	
	@GET
	@Path("/teste")
	@Produces( MediaType.APPLICATION_XML)
	public Requisicao teste( ){
		Requisicao h = new Requisicao();
		return h;
	}
	
	@GET
	@Path("/getList")
	@Produces( MediaType.APPLICATION_XML)
	public List<Requisicao> getList( ){
		//RequisicaoDao hdao = new RequisicaoDao();
		List<Requisicao> list = hdao.getLista();
		
		if( list==null ){
			return null;
		}
		if(list.get(0) == null){
			return null;
		}
		
		return list;
	}
	
	@GET
	@Path("/getLastOnes")
	@Produces( MediaType.APPLICATION_XML)
	public List<LastRequest> getLast( ){
		//RequisicaoDao hdao = new RequisicaoDao();
		List<LastRequest> list = hdao.getLastRequests();
		
		if( list==null ){
			return null;
		}
		if(list.get(0) == null){
			return null;
		}
		
		return list;
	}
	
	
	@POST
	@Path("/insert")
	@Produces( MediaType.APPLICATION_XML)
	public Requisicao insert(@FormParam("idService") long idService,
							 @FormParam("response") int response ){
		//System.out.println(">>> insert ws");
		//RequisicaoDao hdao = new RequisicaoDao();
		Requisicao h = new Requisicao();
		h.setIdService(idService);
		h.setResponse(response);
		hdao.add(h);
		return h;
	}
	
	@POST
	@Path("/getDay")
	@Produces( MediaType.APPLICATION_XML)
	public List<Requisicao> getDay( @FormParam("day") String day,
			 						@FormParam("id") long id ){
		//RequisicaoDao hdao = new RequisicaoDao();
		List<Requisicao> list = hdao.getDay( day, id );
		
		if( list==null ){
			return new ArrayList<Requisicao>();
		}
		if( list.size() == 0){
			return  new ArrayList<Requisicao>();
		}
		else return list;
	}
	
}
