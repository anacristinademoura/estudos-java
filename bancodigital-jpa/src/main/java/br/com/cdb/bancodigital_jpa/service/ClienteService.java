package br.com.cdb.bancodigital_jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import br.com.cdb.bancodigital_jpa.dto.ClienteCriacaoDTO;
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
	
	public Cliente salvarCliente(ClienteCriacaoDTO dto) {
	    Cliente cliente = new Cliente();
	    cliente.setNome(dto.getNome());
	    cliente.setCpf(dto.getCpf());
	    cliente.setTipoCliente(dto.getTipoCliente());

	    return clienteRepository.save(cliente);
	}
	
	public List<Cliente> getClientes(){
		return clienteRepository.findAll();
	}
	
	
	public void deletarCliente(Long idCliente) {
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
		
		if(cliente.getConta() != null) {
			throw new IllegalArgumentException("O cliente possui contas ativas. Exclua antes de apagar o cadastro.");
		}
		
		clienteRepository.delete(cliente);
	}
	
	public Cliente editarCliente(Long idCliente, ClienteCriacaoDTO novosDados) {
	    Cliente cliente = getCliente(idCliente);
	    
	    cliente.setNome(novosDados.getNome());
	    cliente.setCpf(novosDados.getCpf());
	    cliente.setTipoCliente(novosDados.getTipoCliente());

	    return clienteRepository.save(cliente);
	}
	
	
}
