package br.com.cdb.bancodigital_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdb.bancodigital_jpa.dto.ContaCriacaoDTO;
import br.com.cdb.bancodigital_jpa.dto.ContaDetalhadaDTO;
import br.com.cdb.bancodigital_jpa.dto.PixDTO;
import br.com.cdb.bancodigital_jpa.entity.Conta;
import br.com.cdb.bancodigital_jpa.service.ClienteService;
import br.com.cdb.bancodigital_jpa.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaService contaService;

	@PostMapping("/criar")
	public ResponseEntity<String> criarConta(@RequestBody ContaCriacaoDTO dto) {
		try {
			contaService.criarConta(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body("Conta criada com sucesso!");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}

	@GetMapping("/detalhes/{idConta}")
	public ResponseEntity<?> detalhesConta(@PathVariable Long idConta) {
		try {
			ContaDetalhadaDTO detalhes = contaService.detalhesConta(idConta);
			return ResponseEntity.ok(detalhes);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}

	@PostMapping("/depositar")
	public ResponseEntity<String> depositar(@RequestParam Long idConta, @RequestParam Double valor) {
		try {
			contaService.depositar(idConta, valor);
			return ResponseEntity.ok("Deposito realizado com sucesso!");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
		// Envia 200 OK se der certo
		// Envia 400 e uma mensagem se der errado
	}

	@GetMapping("/saldo")
	public ResponseEntity<String> consultarSaldo(@RequestParam Long idConta) {
		try {
			Double saldo = contaService.getSaldo(idConta);
			return ResponseEntity.ok("Saldo: " + saldo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}

	@PostMapping("/sacar")
	public ResponseEntity<String> sacar(@RequestParam Long idConta, @RequestParam Double valor) {
		try {
			contaService.saque(idConta, valor);
			return ResponseEntity.ok("Saque realizado com sucesso!");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}

	@PostMapping("/transferir")
	public ResponseEntity<String> transferir(@RequestParam Long idOrigem, @RequestParam Long idDestino,
			@RequestParam Double valor) {
		try {
			contaService.transferencia(idOrigem, idDestino, valor);
			return ResponseEntity.ok("Tranferência realizada com sucesso!");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deletarConta(@RequestParam Long idConta) {
		try {
			contaService.deletar(idConta);
			return ResponseEntity.ok("Conta deletada com sucesso.");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}

	@PostMapping("/{id}/pix")
	public ResponseEntity<String> realizarPix(@PathVariable Long id, @RequestBody PixDTO dto) {
		contaService.realizarPix(id, dto.getIdContaDestino(), dto.getValor());
		return ResponseEntity.ok("PIX realizado com sucesso.");
	}

	@PutMapping("/{id}/manutencao")
	public ResponseEntity<String> aplicarManutencao(@PathVariable Long id) {
		try {
			contaService.aplicarManutencaoMensal(id);
			return ResponseEntity.ok("Manutenção mensal aplicada com sucesso.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}

	@PutMapping("/{id}/rendimento")
	public ResponseEntity<String> aplicarRendimento(@PathVariable Long id) {
		try {
			contaService.aplicarRendimentoMensal(id);
			return ResponseEntity.ok("Rendimento mensal aplicado com sucesso.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
		}
	}
}
