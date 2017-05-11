package br.com.mobilesaude.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.mobilesaude.connection.ConnectionFactory;
import br.com.mobilesaude.dao.RequisicaoDao;
import br.com.mobilesaude.dao.ServiceDao;
import br.com.mobilesaude.resources.LastRequest;
import br.com.mobilesaude.resources.Requisicao;
import br.com.mobilesaude.resources.Service;
import br.com.mobilesaude.resources.Status_History;
import sun.util.resources.cldr.CalendarData;

public class Main {
	
	public static void main(String[] args){

		
		ServiceDao sdao = new ServiceDao();
		List<Service> slist = sdao.getLista();
		//System.out.println(slist.size());
		
		RequisicaoDao rdao = new RequisicaoDao();
		
		Requisicao r = new Requisicao();
		r.setIdService(100);
		r.setResponse(200);
		r.setRequisicao(22000);
		rdao.add(r);
		
	}
}
