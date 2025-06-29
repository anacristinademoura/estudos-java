package br.com.cdb.bancodigital_jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cdb.bancodigital_jpa.dto.ContaCriacaoDTO;
import br.com.cdb.bancodigital_jpa.dto.ContaDetalhadaDTO;
import br.com.cdb.bancodigital_jpa.entity.Cliente;
import br.com.cdb.bancodigital_jpa.entity.Conta;
import br.com.cdb.bancodigital_jpa.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	@Autowired
	private ClienteService clienteService;

	public void criarConta(ContaCriacaoDTO dto) {

		if (dto.getIdCliente() == null) {
			throw new IllegalArgumentException("É necessário informar o ID do cliente.");
		}

		if (dto.getTipo() == null || !dto.getTipo().equalsIgnoreCase("corrente") && !dto.getTipo().equalsIgnoreCase("poupanca")) {
			throw new IllegalArgumentException("Tipo inválido. Use 'corrente' ou 'poupanca'.");
		}

		Cliente cliente = clienteService.getCliente(dto.getIdCliente());
		
		Conta conta = new Conta();
		conta.setCliente(cliente);
		conta.setSaldo(0.0);
		conta.setNumero(gerarNumeroConta());
		conta.setTipo(dto.getTipo().toLowerCase());
		
		contaRepository.save(conta);
	}
	
	public ContaDetalhadaDTO detalhesConta(Long idConta) {
		Conta conta = contaRepository.findById(idConta)
				.orElseThrow(() -> new RuntimeException("Conta não encontrada."));
		
		Cliente cliente = conta.getCliente();
		
		return new ContaDetalhadaDTO(
				conta.getNumero(),
				conta.getSaldo(),
				conta.getTipo(),
				cliente.getNome(),
				cliente.getCpf()
				);
	}

	private String gerarNumeroConta() {
		int numero = (int) ((Math.random() * 900000) + 100000); // conveter numero para inteiro e depois para string
		return String.valueOf(numero);
	}

	public void depositar(Long idConta, Double valor) {

		if (valor == null || valor < 0) {
			throw new IllegalArgumentException("Valor negativo não permitido");
		}

		Conta conta = contaRepository.findById(idConta).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
		// O lambda traz uma exceção em tempo de execução, caso não encontre a conta

		conta.setSaldo(conta.getSaldo() + valor);
		contaRepository.save(conta);
	}

}
