package rest.cdi.client.consumidor;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rest.cdi.client.entidad.Todos;

public interface ClienteTodosHttp {

	@GET
	@Path("/{id}")
	@Consumes(value = MediaType.APPLICATION_JSON)
	Todos buscarId(@PathParam("id") Integer idTodo);

	@GET
	@Consumes(value = MediaType.APPLICATION_JSON)
	List<Todos> listar();

}
