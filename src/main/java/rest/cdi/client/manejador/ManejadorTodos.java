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

import rest.cdi.client.consumidor.ClienteTodosHttp;
import rest.cdi.client.entidad.Todos;
import rest.cdi.client.impl.ClienteTodosHttpImpl;

@Named("manejadorTodos")
@ApplicationScoped
public class ManejadorTodos implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Todos> todoList;
	@Inject
	private ClienteTodosHttp cliente;
	@Inject
	private Todos todo;
	private int idTodo;

	public void buscarTodo() {
		todo = cliente.buscarId(idTodo);

	}

	public Todos getTodo() {
		return todo;
	}

	public void setTodo(Todos todo) {
		this.todo = todo;
	}

	public List<Todos> getTodoList() {
		return cliente.listar();
	}

	public void setTodoList(List<Todos> todoList) {
		this.todoList = todoList;
	}

	public int getIdTodo() {
		return idTodo;
	}

	public void setIdTodo(int idTodo) {
		this.idTodo = idTodo;
	}

}
