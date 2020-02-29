
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

import rest.cdi.client.consumidor.ClienteUsersHttp;
import rest.cdi.client.entidad.Users;

@ApplicationScoped
public class ClienteUsersHttpImpl implements ClienteUsersHttp {

	final static String USER_URL = "http://localhost:7008/Servicio-users/users";

	CloseableHttpClient httpClient = HttpClients.createDefault();

	@Override
	public List<Users> listar() {

		HttpGet get = new HttpGet(USER_URL);
		get.addHeader("Accept", "application/json");
		List<Users> dto = new ArrayList();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			dto = httpClient.execute(get, response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status == 200) {
					return new ObjectMapper().readValue(response.getEntity().getContent(),
							new TypeReference<List<Users>>() {
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

	@Override
	public Users buscarId(Integer idUser) {
		// String url = urlConexion;
		String url = USER_URL;
		HttpGet get = new HttpGet(url + "/" + idUser.toString());
		get.addHeader("Accept", "application/json");
		Users dto = new Users();
		String d = "";
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			dto = httpClient.execute(get, response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status == 200) {
					return new ObjectMapper().readValue(response.getEntity().getContent(), new TypeReference<Users>() {
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
