package br.com.cdb.bancodigital_jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import br.com.cdb.bancodigital_jpa.entity.Cliente;
import br.com.cdb.bancodigital_jpa.entity.Conta;
import br.com.cdb.bancodigital_jpa.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente getCliente(Long idCliente) {
		return clienteRepository.findById(idCliente)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
	}
	
	public Cliente salvarCliente(Cliente cliente) {
		
		validarNome(cliente.getNome());
		validarCpf(cliente.getCpf());
		
		return clienteRepository.save(cliente); //Salva os dois por causa do cascade
	}
	
	public List<Cliente> getClientes(){
		return clienteRepository.findAll();
	}
	
	public void validarNome(String nome) {
		if(nome == null || nome.trim().isEmpty()) { 
			throw new IllegalArgumentException("O nome não pode estar vazio");
		}
		//trim remove espaços em branco do início e do fim
		//isEmpty garante que não foi digitado só espaços
	}
	
	private void validarCpf(String cpf) {
		if (cpf == null || cpf.trim().length() != 11) {
			throw new IllegalArgumentException("CPF inválido");
		}
	}
	
	public void deletarCliente(Long idCliente) {
		Cliente cliente = getCliente(idCliente);
		
		clienteRepository.delete(cliente);
	}
	
	public Cliente editarCliente(Long idCliente, Cliente novosDados) {
		Cliente cliente = getCliente(idCliente);
		
		validarNome(novosDados.getNome());
		validarCpf(novosDados.getCpf());
		
		cliente.setNome(novosDados.getNome());
		cliente.setCpf(novosDados.getCpf());
		
		return clienteRepository.save(cliente);
	}
	
	
}
