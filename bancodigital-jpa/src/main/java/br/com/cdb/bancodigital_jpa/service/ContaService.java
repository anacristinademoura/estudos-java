package br.com.cdb.bancodigital_jpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cdb.bancodigital_jpa.dto.ContaCriacaoDTO;
import br.com.cdb.bancodigital_jpa.dto.ContaDetalhadaDTO;
import br.com.cdb.bancodigital_jpa.entity.Cliente;
import br.com.cdb.bancodigital_jpa.entity.Conta;
import br.com.cdb.bancodigital_jpa.enums.TipoConta;
import br.com.cdb.bancodigital_jpa.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	@Autowired
	private ClienteService clienteService;

	public Conta buscarClientePorId(Long idConta) {
		Conta conta = contaRepository.findById(idConta)
				.orElseThrow(() -> new RuntimeException("Conta não encontrada."));
		// O lambda traz uma exceção em tempo de execução, caso não encontre a conta
		return conta;
	}

	public void valorNaoValido(Double valor) {
		if (valor == null || valor < 0) {
			throw new IllegalArgumentException("Valor negativo não é permitido.");
		}
	}

	public void criarConta(ContaCriacaoDTO dto) {

		if (dto.getIdCliente() == null) {
			throw new IllegalArgumentException("É necessário informar o ID do cliente.");
		}

		if (dto.getTipo() == null) {
			throw new IllegalArgumentException("Tipo inválido. Use CORRENTE ou POUPANCA.");
		}

		Cliente cliente = clienteService.getCliente(dto.getIdCliente());

		Conta conta = new Conta();
		conta.setCliente(cliente);
		conta.setSaldo(0.0);
		conta.setNumero(gerarNumeroConta());
		conta.setTipoConta(dto.getTipo());

		contaRepository.save(conta);
	}

	public ContaDetalhadaDTO detalhesConta(Long idConta) {
		Conta conta = buscarClientePorId(idConta);

		Cliente cliente = conta.getCliente();

		return new ContaDetalhadaDTO(conta.getNumero(), conta.getSaldo(), conta.getTipoConta().name(),
				cliente.getNome(), cliente.getCpf());
	}

	private String gerarNumeroConta() {
		int numero = (int) ((Math.random() * 900000) + 100000); // conveter numero para inteiro e depois para string
		return String.valueOf(numero);
	}

	public void depositar(Long idConta, Double valor) {

		valorNaoValido(valor);

		Conta conta = buscarClientePorId(idConta);

		conta.setSaldo(conta.getSaldo() + valor);
		contaRepository.save(conta);
	}

	public Double getSaldo(Long idConta) {
		Conta conta = buscarClientePorId(idConta);
		return conta.getSaldo();

	}

	public void saque(Long idConta, Double valor) {

		valorNaoValido(valor);

		Conta conta = buscarClientePorId(idConta);

		if (conta.getSaldo() < valor) {
			throw new IllegalArgumentException("Saldo insuficiente!");
		}

		conta.setSaldo(conta.getSaldo() - valor);
		contaRepository.save(conta);

	}

	public void transferencia(Long idOrigem, Long idDestino, Double valor) {

		valorNaoValido(valor);

		Conta origem = contaRepository.findById(idOrigem)
				.orElseThrow(() -> new RuntimeException("Conta de origem não encontrada."));

		Conta destino = contaRepository.findById(idDestino)
				.orElseThrow(() -> new RuntimeException("Conta de destino não encontrada."));

		if (origem.getSaldo() < valor) {
			throw new IllegalArgumentException("Saldo insuficiente na conta de origem.");
		}

		origem.setSaldo(origem.getSaldo() - valor);
		destino.setSaldo(destino.getSaldo() + valor);

		contaRepository.save(origem);
		contaRepository.save(destino);
	}

	public void deletar(Long idConta) {

		Conta conta = contaRepository.findById(idConta)
				.orElseThrow(() -> new RuntimeException("Conta não encontrada."));

		Cliente cliente = conta.getCliente();

		if (cliente != null) {
			cliente.setConta(null); // desvincula a conta do Cliente
		}

		conta.setCliente(null); // desvincula o cliente da conta
		contaRepository.delete(conta);

	}

	public void realizarPix(Long idOrigem, Long idDestino, Double valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("O valor da transferência deve ser positivo.");
		}

		Conta origem = contaRepository.findById(idOrigem)
				.orElseThrow(() -> new RuntimeException("Conta de origem não encontrada."));

		Conta destino = contaRepository.findById(idDestino)
				.orElseThrow(() -> new RuntimeException("Conta de destino não encontrada."));

		if (origem.getSaldo() < valor) {
			throw new RuntimeException("Saldo insuficiente na conta de origem.");
		}

		origem.setSaldo(origem.getSaldo() - valor);
		destino.setSaldo(destino.getSaldo() + valor);

		contaRepository.save(origem);
		contaRepository.save(destino);
	}

	public void aplicarManutencaoMensal(Long idConta) {
		Conta conta = contaRepository.findById(idConta)
				.orElseThrow(() -> new RuntimeException("Conta não encontrada."));

		if (conta.getTipoConta() != TipoConta.CORRENTE) {
			throw new IllegalArgumentException("Manutenção mensal só pode ser aplicada a contas do tipo CORRENTE.");
		}

		conta.aplicarManutencaoMensal();
		contaRepository.save(conta);
	}

	public void aplicarRendimentoMensal(Long idConta) {
		Conta conta = contaRepository.findById(idConta)
				.orElseThrow(() -> new RuntimeException("Conta não encontrada."));

		if (conta.getTipoConta() != TipoConta.POUPANCA) {
			throw new IllegalArgumentException("Rendimento mensal só pode ser aplicado a contas do tipo POUPANÇA.");
		}

		conta.aplicarRendimentoMensal();
		contaRepository.save(conta);
	}

}
