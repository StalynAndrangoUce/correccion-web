
package rest.cdi.client.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.agent.model.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import rest.cdi.client.consumidor.ClienteTodosHttp;
import rest.cdi.client.entidad.Todos;

@ApplicationScoped
public class ClienteTodosHttpImpl implements ClienteTodosHttp {

	final static String TODO_URL = "http://localhost:7008/Servicio-todos/todos";

	CloseableHttpClient httpClient = HttpClients.createDefault();

	@Override
	public List<Todos> listar() {

		String url = TODO_URL;
		System.out.println("URL: " + url);
		HttpGet get = new HttpGet(url);
		get.addHeader("Accept", "application/json");
		List<Todos> dto = new ArrayList();
		String d = "";
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			dto = httpClient.execute(get, response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status == 200) {
					return new ObjectMapper().readValue(response.getEntity().getContent(),
							new TypeReference<List<Todos>>() {
							});
				}
				return null;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("lista: " + dto);
		return dto;
	}

	@Override
	public Todos buscarId(Integer idTodo) {
		// String url = urlConexion;
		String url = TODO_URL;
		HttpGet get = new HttpGet(url + "/" + idTodo.toString());
		get.addHeader("Accept", "application/json");
		Todos dto = new Todos();
		String d = "";
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			dto = httpClient.execute(get, response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status == 200) {
					return new ObjectMapper().readValue(response.getEntity().getContent(), new TypeReference<Todos>() {
					});
				}
				return null;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("lista: " + dto);
		return dto;
	}

}
