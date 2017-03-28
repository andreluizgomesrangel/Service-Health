package br.com.mobilesaude.ws;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.mobilesaude.dao.RequisicaoDao;
import br.com.mobilesaude.dao.ServiceDao;
import br.com.mobilesaude.resources.Requisicao;
import br.com.mobilesaude.resources.Service;

@SuppressWarnings({ "unused" })
@Path("/ws/servico/requisicao")
public class RequisicaoWs {

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
		RequisicaoDao hdao = new RequisicaoDao();
		List<Requisicao> list = hdao.getLista();
		
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
	public List<Requisicao> insert(@FormParam("idService") long idService,
								 @FormParam("response") int response,
								 @FormParam("request") long request
								){
		RequisicaoDao hdao = new RequisicaoDao();
		List<Requisicao> list = hdao.getLista();
		Requisicao h = new Requisicao();
		h.setIdService(idService);
		h.setResponse(response);
		h.setRequisicao(request);
		
		if( list.size()==0){
			h.setId(1);
			hdao.add(h);
			return hdao.getLista();
		}
		
		long lastId = list.get( list.size() - 1 ).getId();
		h.setId( lastId + 1 );
		hdao.add(h);
		return hdao.getLista();
	}
	
}
