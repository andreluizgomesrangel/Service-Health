package br.com.mobilesaude.resources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.xml.bind.JAXBException;

import br.com.mobilesaude.dao.RequisicaoDao;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Requisicoes {

	@EJB
	RequisicaoDao dao;

	public Requisicoes() {

	}

	public int postRequest(Service s) {

		int code = 0;
		OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(30, TimeUnit.SECONDS)
				.readTimeout(30, TimeUnit.SECONDS).build();

		HashMap<String, String> param = getParam(s.getParam());

		FormBody.Builder builder = new FormBody.Builder();

		for (Map.Entry<String, String> entry : param.entrySet()) {
			builder.add(entry.getKey(), entry.getValue());
		}
		RequestBody formBody = builder.build();
		// System.out.print( " post waiting..." );
		Request request = new Request.Builder().url(s.getUrl()).post(formBody).build();

		try {

			Response response = client.newCall(request).execute();
			code = response.code();
			System.out.println(" >>>>>>>  mensagem: " + response.message());
			response.body().close();
			response.close();
			return code;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return code;
	}

	public int getRequest(Service s) {

		int code = 0;
		OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(3 * 60, TimeUnit.SECONDS)
				.readTimeout(3 * 60, TimeUnit.SECONDS).build();

		FormBody.Builder builder = new FormBody.Builder();

		// System.out.print( " get waiting..." );
		Request request = new Request.Builder().url(s.getUrl()).get().build();
		try {

			Response response = client.newCall(request).execute();
			code = response.code();
			System.out.println(" >>>>>>>  mensagem: " + response.message());
			response.body().close();
			response.close();
			return code;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return code;
	}

	public HashMap<String, String> getParam(String query) {

		HashMap<String, String> p = new HashMap<>();

		String[] param = query.split("&");
		for (String a : param) {
			String[] x = a.split("=");
			if (x.length != 0) {
				p.put(x[0], x[1]);
			}
		}
		return p;
	}

	// Metodo responsavel por fazer uma requisicao
	public int request(Service s) {

		if (s.getRequestType().equals("post")) {
			return postRequest(s);
		}
		if (s.getRequestType().equals("get")) {
			return getRequest(s);
		}

		return 0;

	}

	// faz uma requisicao e insere ela no bd
	public Requisicao newRequest(Service s) {

		int response = request(s);
		Requisicao h = new Requisicao();
		h.setResponse(response);
		h.setIdService(s.getId());

		insert(s.getId(), response);

		return h;
	}

	public void insert(long idService, int resp) {
		Requisicao h = new Requisicao();
		h.setIdService(idService);
		h.setResponse(resp);

		dao.add(h);
	}

}
