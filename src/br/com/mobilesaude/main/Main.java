package br.com.mobilesaude.main;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.mobilesaude.connection.ConnectionFactory;
import br.com.mobilesaude.dao.RequisicaoDao;
import br.com.mobilesaude.dao.ServiceDao;
import br.com.mobilesaude.resources.Requisicao;
import br.com.mobilesaude.resources.Service;
import br.com.mobilesaude.resources.Status_History;
import sun.util.resources.cldr.CalendarData;

public class Main {
	
	public static void main(String[] args){
	
		Status_History s = new Status_History();
		s.getS().setId(8);
		//s.getOneDay("2017/03/31");
		
		System.out.println(s.getTodayString()+" "+ s.getOneDay("2017/03/31") );
		
	}
}
