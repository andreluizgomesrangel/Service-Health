package br.com.mobilesaude.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.mobilesaude.dao.RequisicaoDao;
import br.com.mobilesaude.resources.LastRequest;

@ManagedBean
@ViewScoped
public class Bean {

	List<LastRequest> lastRequests = new ArrayList<LastRequest>();
	// CRequisicao cr = new CRequisicao();
	@EJB
	RequisicaoDao rdao;

	public Bean() {
		System.out.println(">>>>>>>>>>>>>>>>>>> Bean");
		
		// lastRequests = cr.getLastOnes();
		lastRequests = rdao.getLastRequests();
	}

	public List<LastRequest> getLastRequests() {
		return lastRequests;
	}

	public void setLastRequests(List<LastRequest> lastRequests) {
		this.lastRequests = lastRequests;
	}

}
