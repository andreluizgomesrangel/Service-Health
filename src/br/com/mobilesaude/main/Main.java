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
	
		ServiceDao sdao = new ServiceDao();
		List<Service> services = sdao.getLista();
		
		int qtdServices = services.size();
		int qtdDias = 11;
		
		Calendar d = Calendar.getInstance();
		
		Status_History []status = new Status_History[qtdServices];
		
		for( int i=0; i<qtdServices; i++ ){
			
			status[i] = new Status_History( services.get(i).getId() , qtdDias );
			
		}
		

		for( int i=0; i<qtdDias; i++  ){
			System.out.print( "  | "+status[0].dataToStringBR(d));
			d.add(Calendar.DATE, -1);
		}
		
		for( int j=0; j<qtdServices; j++ ){
			System.out.println();
			int []dia = status[j].getDia();
			for(int i=0; i<dia.length; i++){
				System.out.print( "       "+dia[i]+" " );
			}
		}
		
	}
}
