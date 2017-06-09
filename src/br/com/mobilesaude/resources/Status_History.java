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

	int[] dia;
	private Calendar today = Calendar.getInstance();
	private String todayString;

	public Status_History() {

	}

	public Status_History(long id, int n) {
		dia = new int[n];
		verifDias(id, n);
	}

	/*
	 * Verificar getOneDay para o servico de id por n dias a partir de ontem
	 */

	public void verifDias(long id, int n) {

		for (int i = 0; i < n; i++) {
			today.add(Calendar.DATE, -1);
			String dayString = dataToString(today);
			int v = getOneDay(id, dayString);
			dia[i] = v;
		}
	}

	public String dataToString(Calendar c) {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String reportDate = df.format(c.getTime());
		return reportDate;
	}

	public String dataToStringBR(Calendar c) {
		DateFormat df = new SimpleDateFormat("dd/MM");
		String reportDate = df.format(c.getTime());
		return reportDate;
	}

	// obter verificacao (-1/1/0) para requisicoes (erro/sem erro/sem
	// requisicao) de um dia
	public int getOneDay(long id, String day) {

		List<Requisicao> reqDia = new ArrayList<Requisicao>();
		RequisicaoDao rdao = new RequisicaoDao();
		reqDia = rdao.getDay(day, id);

		if (reqDia.size() > 0) {
			for (Requisicao r : reqDia) {
				if (r.getResponse() != 200) {
					return -1;
				}
			}
			return 1;
		}

		return 0;
	}

	public int[] getDia() {
		return dia;
	}

	public void setDia(int[] dia) {
		this.dia = dia;
	}

	public String getTodayString() {
		return todayString;
	}

	public void setTodayString(String todayString) {
		this.todayString = todayString;
	}

}
