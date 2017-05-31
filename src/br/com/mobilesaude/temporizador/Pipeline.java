package br.com.mobilesaude.temporizador;

import javax.ejb.EJB;

import br.com.mobilesaude.dao.RequisicaoDao;
import br.com.mobilesaude.resources.Requisicoes;
import br.com.mobilesaude.resources.Service;

public class Pipeline implements Runnable {
	Service s;
	@EJB
	Requisicoes clienteRequisicao;

	public Pipeline() {
		
		//clienteRequisicao = new Requisicoes();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("executar pipeline service: " + s.getId());
		 clienteRequisicao.newRequest(s);

	}

	public Service getS() {
		return s;
	}

	public void setS(Service s) {
		this.s = s;
	}

	public Requisicoes getClienteRequisicao() {
		return clienteRequisicao;
	}

	public void setClienteRequisicao(Requisicoes clienteRequisicao) {
		this.clienteRequisicao = clienteRequisicao;
	}

}
