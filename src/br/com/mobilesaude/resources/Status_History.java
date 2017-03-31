package br.com.mobilesaude.resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.mobilesaude.dao.RequisicaoDao;

@XmlRootElement(name = "status_history")
@XmlAccessorType(XmlAccessType.FIELD)
public class Status_History {

	Service s = new Service(); 
	boolean []dia = new boolean[11];
	
	
	private List<String> dias = new ArrayList();
	private Date today;
	private String todayString;
	
	
	public Status_History(){
		
		today = Calendar.getInstance().getTime();        
		todayString = dataToString(today);
		
		/*for(int i=0; i<11; i++){
			dias.add("2017/03/"+(31-i));
		}*/
		
	}
	
	
	public String dataToString(Date d){
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		//d = Calendar.getInstance().getTime();        
		String reportDate = df.format(d);
		return reportDate;
	}
	
	public boolean getOneDay( String day ){
		
		List<Requisicao> reqDia = new ArrayList<Requisicao>();
		RequisicaoDao rdao = new RequisicaoDao();
		reqDia = rdao.getDay( day, s.getId() );
		
		if(reqDia.size()>0){
			for( Requisicao r : reqDia ){
				if( r.getResponse()!=200 ){
					return false;
				}
			}
		}else{
			System.out.println("LIIIIIIIISSTAAA VAZIAAAAAA");
		}
		
		return true; 
	}

	
	
	
	public Service getS() {
		return s;
	}

	public void setS(Service s) {
		this.s = s;
	}

	public boolean[] getDia() {
		return dia;
	}

	public void setDia(boolean[] dia) {
		this.dia = dia;
	}


	public List<String> getDias() {
		return dias;
	}


	public void setDias(List<String> dias) {
		this.dias = dias;
	}


	public Date getToday() {
		return today;
	}


	public void setToday(Date today) {
		this.today = today;
	}


	public String getTodayString() {
		return todayString;
	}


	public void setTodayString(String todayString) {
		this.todayString = todayString;
	}
	
	
	
}
