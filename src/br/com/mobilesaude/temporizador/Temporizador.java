package br.com.mobilesaude.temporizador;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
//import br.com.ythalo.session.AutomaticTimer;
import javax.xml.bind.JAXBException;

import br.com.mobilesaude.dao.ServiceDao;
import br.com.mobilesaude.resources.Service;

@Stateless
public class Temporizador {

	@EJB
	ServiceDao dao;
	List<Service> list = new ArrayList<Service>();
	
	@EJB
	Pipeline pipeline;

	public Temporizador() {

	}

	@Schedule(second = "*/15", minute = "*", hour = "*")
	public void doWork() {

		System.out.println(">>>>>> TIMER <<<<<<<");

		list = dao.getLista();

		// System.out.println(list.size());
		for (Service x : list) {
			pipeline.setS(x);
			System.out.println(x.getName());
			Thread t = new Thread(pipeline);
			t.start();

		}
		//
	}

}
