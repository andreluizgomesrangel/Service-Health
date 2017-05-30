package br.com.mobilesaude.temporizador;

import javax.ejb.EJB;

import br.com.mobilesaude.dao.RequisicaoDao;
import br.com.mobilesaude.resources.Requisicoes;
import br.com.mobilesaude.resources.Service;

public class Pipeline implements Runnable {
	Service s;
	Requisicoes clienteRequisicao;

	public Pipeline(Service s) {
		this.s = s;
		clienteRequisicao = new Requisicoes();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("executar pipeline service: " + s.getId());
		 clienteRequisicao.newRequest(s);

	}

}
