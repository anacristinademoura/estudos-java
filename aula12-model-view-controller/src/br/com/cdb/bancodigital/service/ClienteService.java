package br.com.cdb.bancodigital.service;
import br.com.cdb.bancodigital.dao.ClienteDAO;
import br.com.cdb.bancodigital.entity.Cliente;

public class ClienteService {
	
	ClienteDAO clienteDAO = new ClienteDAO();
	
	public boolean addCliente (String nome, String cpf) {
		
		if(!ValidarNome(nome)) {
			return false;
		}
		if(!validarCPF(cpf)) {
			return false;
		}
		
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		
		clienteDAO.addCliente(cliente);
		return true;
		
	}

	private boolean ValidarNome(String nome) {
		// Criar regras de negócio para nome
		return true;
	}
	
	private boolean validarCPF(String cpf) {
		// Criar regras de negócio para nome
		return true;
	}
	
}
