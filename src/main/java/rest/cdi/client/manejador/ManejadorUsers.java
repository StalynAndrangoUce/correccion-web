package rest.cdi.client.manejador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rest.cdi.client.consumidor.ClienteUsersHttp;
import rest.cdi.client.entidad.Users;

@Named("manejadorUsers")
@ApplicationScoped
public class ManejadorUsers implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Users> userList;
	private Integer idUser;
	@Inject
	private ClienteUsersHttp cliente;
	@Inject
	private Users user;

	public void buscarUser() {
		user = cliente.buscarId(idUser);

	}

	public List<Users> getusersList() {
		return cliente.listar();
	}

	public void setusersList(List<Users> UsersList) {
		this.userList = UsersList;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<Users> getUserList() {
		return cliente.listar();
	}

	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

}
