package br.com.cdb.bancodigital.dao;
import java.util.ArrayList;
import br.com.cdb.bancodigital.entity.Cliente;

public class ClienteDAO {

	ArrayList<Cliente> listaDeClientes = new ArrayList<>();
	
	public void addCliente(Cliente cliente) {
		
		cliente.setId(listaDeClientes.size());
		listaDeClientes.add(cliente);
	}
	
	public Cliente buscarCliente(int id) {
		
		for(Cliente c : listaDeClientes) {
			if(c.getId() == id) {
				return c;
			}
		}
		return null;
	}
}
